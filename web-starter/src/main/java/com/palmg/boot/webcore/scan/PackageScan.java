package com.palmg.boot.webcore.scan;

import java.io.IOException;
import java.util.Optional;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import com.palmg.boot.webcore.annotation.BeanScan;
import com.palmg.boot.webcore.annotation.JpaDaoScan;
import com.palmg.boot.webcore.annotation.JpaEntityScan;
import com.palmg.boot.webcore.annotation.ResourceScan;
import com.palmg.boot.webcore.annotation.Scan;

public class PackageScan {
	static Logger LOG = LoggerFactory.getLogger(PackageScan.class);
	static final String[] Propertiesloadpath = { "classpath:palmg-boot.properties",
			"classpath:/palmg/config/palmg-boot.properties" };
	static private final String ScanName = "palmg.scan.all"; // 全局扫描配置，仅仅配置这一个就够
	static private final String BeanScanName = "palmg.scan.bean";// spring bean扫描配置
	static private final String EntityScanName = "palmg.scan.entity";// Jpa entity扫描配置
	static private final String JpaScanName = "palmg.scan.entity";// Jpa dao扫描配置
	static private final String ResourceScanName = "palmg.scan.resource";// Jpa dao扫描配置
	static private final String BasePackage = "com.palmg.boot.webcore";
	static private final String BaseResource = "classpath:/palmg/config/application-context.xml";
	static private final String NAN = "NaN"; // 错误字符串标记
	private Class<?> launchClass;// 启动类
	private String[] baseScanPackage; // 基础扫描路径
	private String[] beanScanPackage; // spring bean 扫描
	private String[] entityScanPackage; // Jpa entity 扫描
	private String[] jpaScanPackage; // Jpa dao扫描
	private String[] resourceScanPackage; // 配置文件加载路径

	public PackageScan() {
	}

	public PackageScan(Class<?> launchClass) {
		this.launchClass = launchClass;
	}

	public String[] getBaseScanPackage() {
		return baseScanPackage;
	}

	public String[] getBeanScanPackage() {
		return beanScanPackage;
	}

	public String[] getEntityScanPackage() {
		return entityScanPackage;
	}

	public String[] getJpaScanPackage() {
		return jpaScanPackage;
	}

	public String[] getResourceScanPackage() {
		return resourceScanPackage;
	}

	public PackageScan setBaseScanPackage(String[] baseScanPackage) {
		this.baseScanPackage = baseScanPackage;
		return this;
	}

	public PackageScan setBeanScanPackage(String[] beanScanPackage) {
		this.beanScanPackage = beanScanPackage;
		return this;
	}

	public PackageScan setEntityScanPackage(String[] entityScanPackage) {
		this.entityScanPackage = entityScanPackage;
		return this;
	}

	public PackageScan setJpaScanPackage(String[] jpaScanPackage) {
		this.jpaScanPackage = jpaScanPackage;
		return this;
	}

	public PackageScan setResourceScanPackage(String[] resourceScanPackage) {
		this.resourceScanPackage = resourceScanPackage;
		return this;
	}

	public void build() throws PackageScanSupportError {
		buildProperties();
		buildClass();
		meger();
	}

	private void buildProperties() throws PackageScanSupportError {
		ResourcePatternResolver resourceLoader = new PathMatchingResourcePatternResolver();
		for (String path : Propertiesloadpath) {
			try {
				Resource[] resources = resourceLoader.getResources(path);
				for (Resource resource : resources) {
					if (resource.exists()) {
						Properties p = new Properties();
						p.load(resource.getInputStream());
						baseScanPackage = Optional.ofNullable(p.getProperty(ScanName)).orElse(NAN).split(",");
						beanScanPackage = Optional.ofNullable(p.getProperty(BeanScanName)).orElse(NAN).split(",");
						entityScanPackage = Optional.ofNullable(p.getProperty(EntityScanName)).orElse(NAN).split(",");
						jpaScanPackage = Optional.ofNullable(p.getProperty(JpaScanName)).orElse(NAN).split(",");
						resourceScanPackage = Optional.ofNullable(p.getProperty(ResourceScanName)).orElse(NAN)
								.split(",");
					}
				}
			} catch (IOException e) {
				throw new PackageScanSupportError(e);
			}
		}
	}

	private void buildClass() {
		if (null != launchClass) {
			Scan scanAnnot = launchClass.getDeclaredAnnotation(Scan.class);
			BeanScan beanScanAnnot = launchClass.getDeclaredAnnotation(BeanScan.class);
			JpaEntityScan jpaEntityScanAnnot = launchClass.getDeclaredAnnotation(JpaEntityScan.class);
			JpaDaoScan jpaDaoScanAnnot = launchClass.getDeclaredAnnotation(JpaDaoScan.class);
			ResourceScan resourceScanAnnot = launchClass.getDeclaredAnnotation(ResourceScan.class);
			if (null != scanAnnot && 0 < scanAnnot.valuse().length) {
				baseScanPackage = scanAnnot.valuse();
			}
			if (checkIsNaNList(baseScanPackage)) {
				baseScanPackage = new String[1];
				baseScanPackage[0] = launchClass.getPackage().getName();
			}

			if (null != beanScanAnnot && 0 < beanScanAnnot.valuse().length) {
				beanScanPackage = beanScanAnnot.valuse();
			}

			if (null != jpaEntityScanAnnot && 0 < jpaEntityScanAnnot.valuse().length) {
				entityScanPackage = jpaEntityScanAnnot.valuse();
			}

			if (null != jpaDaoScanAnnot && 0 < jpaDaoScanAnnot.valuse().length) {
				jpaScanPackage = jpaDaoScanAnnot.valuse();
			}

			if (null != resourceScanAnnot && 0 < resourceScanAnnot.valuse().length) {
				resourceScanPackage = jpaDaoScanAnnot.valuse();
			}
		}
	}

	private void meger() {
		boolean isHasBasePackage = false;
		if (checkIsNaNList(baseScanPackage)) {
			LOG.warn("Default Scan package undefined!");
		} else {
			isHasBasePackage = true;
		}
		if (checkIsNaNList(beanScanPackage)) {
			beanScanPackage = isHasBasePackage ? baseScanPackage : null;
		}
		if (checkIsNaNList(entityScanPackage)) {
			entityScanPackage = isHasBasePackage ? baseScanPackage : null;
		}
		if (checkIsNaNList(jpaScanPackage)) {
			jpaScanPackage = isHasBasePackage ? baseScanPackage : null;
		}
		if (checkIsNaNList(resourceScanPackage)) {
			resourceScanPackage = null;
		}
		
		if (null == beanScanPackage) {
			beanScanPackage = new String[1];
			beanScanPackage[0] = BasePackage;
		} else {
			String[] temp = beanScanPackage;
			beanScanPackage = new String[1 + temp.length];
			beanScanPackage[0] = BasePackage;
			System.arraycopy(temp, 0, beanScanPackage, 1, temp.length);
		}

		if (null == resourceScanPackage) {
			resourceScanPackage = new String[1];
			resourceScanPackage[0] = BaseResource;
		} else {
			String[] temp = resourceScanPackage;
			resourceScanPackage = new String[1 + temp.length];
			resourceScanPackage[0] = BaseResource;
			System.arraycopy(temp, 0, resourceScanPackage, 1, temp.length);
		}
	}

	/**
	 * 检查字符串数组是否无效
	 * 
	 * @param list
	 * @return
	 */
	private boolean checkIsNaNList(String[] list) {
		return null == list || null == list[0] || NAN == list[0];
	}
}
