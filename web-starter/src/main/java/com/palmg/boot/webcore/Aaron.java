package com.palmg.boot.webcore;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;

import com.palmg.boot.webcore.scan.AnnotationConver;
import com.palmg.boot.webcore.scan.PackageScan;
import com.palmg.boot.webcore.scan.PackageScanSupportError;
import com.palmg.boot.webcore.scan.conversion.AnnotConverExceprion;
import com.palmg.security.properties.PropertyCrypto;
import com.palmg.security.properties.exception.FileLoadException;

/**
 * <h3>web-starter框架启动入口。</h3>
 * <h4>启动</h4>
 * 在业务代码的任何位置都可以直接启动web-starter。
 * <pre class="code">
 * public static void main(String[] args) {
 *		new Aaron().launch(args);
 *  }
 * </pre>
 * Aaron提供一个接口接收入口类来加强配置功能：
 * <pre class="code">
 * &#064;Scan
 * &#064;BeanScan
 * &#064;JpaDaoScan
 * &#064;JpaEntityScan
 * &#064;ResourceScan
 * class MyClass {}
 * public static void main(String[] args) {
 *		new Aaron(MyClass.class).launch(args);
 * }
 * </pre>
 * <br>
 * 除了注解，还可以使用properties配置。配置默认加载路径为classpath:palmg-boot.properties、classpath:/palmg/config/palmg-boot.properties。
 * 可以通过palmg.config.location参数指定更多的加载位置()。路径必须是ResourcePatternResolver规则。
 * @author chenkui
 */
public class Aaron {
	static Logger LOG = LoggerFactory.getLogger(Aaron.class);
	
	private PackageScan packageScan;

	public static void main(String[] args) {
		new Aaron().launch(args);
	}
	
	/**
	 * 基础构造函数，如果在业务工程中使用该构造函数，并且在无配置文件的情况下，整个包的入口位置使用线程栈的栈顶元素作为入口。
	 */
	public Aaron() {
		//当使用线程运行栈推导包入口位置时，需要全局保存第一次启动的位置，否则会被devTool覆盖
		String lunchPackage = System.getProperty("palmg.boot.lunchPackage");
		if(null == lunchPackage) {
			StackTraceElement[] elements = Thread.currentThread().getStackTrace();
			StackTraceElement element = elements[elements.length - 1];
			String className = element.getClassName();
			int pos = className.lastIndexOf(".");
			lunchPackage = className.substring(0, pos);
			System.setProperty("palmg.boot.lunchPackage", lunchPackage);
		}
		packageScan = new PackageScan(lunchPackage);
	}

	/**
	 * 构造函数
	 * @param launchClass 入口类，如果没有任何注解配置，会使用这个入口类作为默认跟目录进行注解扫描。
	 */
	public Aaron(Class<?> launchClass) {
		packageScan = new PackageScan(launchClass);
	}

	public Aaron setBaseScanPackage(String[] baseScanPackages) {
		packageScan.setBaseScanPackage(baseScanPackages);
		return this;
	}

	public Aaron setBeanScanPackage(String[] beanScanPackage) {
		packageScan.setBeanScanPackage(beanScanPackage);
		return this;
	}

	public Aaron setEntityScanPackages(String[] entityScanPackage) {
		packageScan.setEntityScanPackage(entityScanPackage);
		return this;
	}

	public Aaron setJpaScanPackage(String[] jpaScanPackage) {
		packageScan.setJpaScanPackage(jpaScanPackage);
		return this;
	}

	public Aaron setresourceScanPackage(String[] resourceScanPackage) {
		packageScan.setResourceScanPackage(resourceScanPackage);
		return this;
	}

	public ApplicationContext launch(String[] args) {
		try {
			packageScan.build();//构建扫描路径
		} catch (PackageScanSupportError e) {
			LOG.error("Init package config error!  exit(0)", e);
			System.exit(0);
		}

		Properties properties = null;
		try {
			properties = PropertyCrypto.getNew().decryptFile();//解密加密参数
		} catch (FileLoadException e) {
			LOG.error("Load crypto properties file error! exit(0)", e);
			System.exit(0);
		}
		properties.put("spring.config.name", "palmg-boot");
		try {
			//属性转换，将相关配置写入到Application的Class对象上。
			new AnnotationConver(this.packageScan).conver(); 
		} catch (AnnotConverExceprion e) {
			LOG.error("Conversion Annotation Configu Error! exit(0)", e);
			System.exit(0);
		}
		//启动spring-boot
		SpringApplication app = new SpringApplication(Application.class);
		app.setDefaultProperties(properties);
		app.setBannerMode(Banner.Mode.OFF);
		return app.run(args);
	}
}
