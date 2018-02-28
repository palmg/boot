package com.palmg.boot.webcore.scan;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
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
import com.palmg.boot.webcore.anonymous.Refer;
import com.palmg.boot.webcore.anonymous.Single;

public class PackageScan {
	static Logger LOG = LoggerFactory.getLogger(PackageScan.class);
	static final String NAME_CONFIG_LOCATION_FIle = "palmg.config.location.file"; // 文件加载路径的配置名称

	static final String[] VALUE_DEFAULT_LOCATION = { "classpath:palmg-boot.properties",
			"classpath:/palmg/config/palmg-boot/properties" }; // 文件加载路径
	static final String VALUE_DEFAULT_NAME = "palmg-boot"; // 加载文件名称

	static private final String[] VALUE_BASE_SCAN_PACKAGE = { "com.palmg.boot.webcore" };
	static private final String[] VALUE_BASE_RESUYRCE_PACKAGE = { "classpath:/palmg/config/application-context.xml" };
	private Class<?> launchClass;// 启动类
	private String launchPackage;// 启动路径

	@ConfigBind(propName = "palmg.scan.all", annot = Scan.class, excute = BaseScanBind.class, first = true)
	private String[] baseScanPackage; // 基础扫描路径
	@ConfigBind(propName = "palmg.scan.bean", annot = BeanScan.class, excute = BeanAndJpaBind.class)
	private String[] beanScanPackage; // spring bean 扫描
	@ConfigBind(propName = "palmg.scan.entity", annot = JpaEntityScan.class, excute = BeanAndJpaBind.class)
	private String[] entityScanPackage; // Jpa entity 扫描
	@ConfigBind(propName = "palmg.scan.repository", annot = JpaDaoScan.class, excute = BeanAndJpaBind.class)
	private String[] jpaScanPackage; // Jpa dao扫描
	@ConfigBind(propName = "palmg.scan.resource", annot = ResourceScan.class, excute = ResourceBind.class)
	private String[] resourceScanPackage; // 配置文件加载路径

	public PackageScan() {
	}

	public PackageScan(String launchPackage) {
		this.launchPackage = launchPackage;
	}

	public PackageScan(Class<?> launchClass) {
		this.launchClass = launchClass;
		this.launchPackage = launchClass.getPackage().getName();
	}

	public String getLaunchPackage() {
		return launchPackage;
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

	public String getPropertiesName() {
		return VALUE_DEFAULT_NAME;
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
		Field[] fields = PackageScan.class.getDeclaredFields();
		List<Params> paramsList = new LinkedList<>();
		List<Properties> propertiesList = buildProperties();
		for (Field field : fields) {
			ConfigBind annot = field.getAnnotation(ConfigBind.class);
			if (null != annot) {
				Params params = new Params(field, annot, this.launchClass, this, propertiesList);
				if (annot.first()) {
					paramsList.add(0, params);
				} else {
					paramsList.add(params);
				}
			}
		}
		try {
			for (Params params : paramsList) {
				Bind bind = params.configBind.excute().newInstance();
				bind.execute(params);
			}
		} catch (InstantiationException | IllegalAccessException e) {
			throw new PackageScanSupportError(e);
		}

		// 返回数据之前的特殊处理
		String[] temp = this.beanScanPackage;
		this.beanScanPackage = new String[VALUE_BASE_SCAN_PACKAGE.length + temp.length];
		System.arraycopy(VALUE_BASE_SCAN_PACKAGE, 0, this.beanScanPackage, 0, VALUE_BASE_SCAN_PACKAGE.length);
		System.arraycopy(temp, 0, this.beanScanPackage, VALUE_BASE_SCAN_PACKAGE.length, temp.length);

		this.resourceScanPackage = null == this.resourceScanPackage ? VALUE_BASE_RESUYRCE_PACKAGE
				: (new Single<String[], String[]>() {
					@Override
					public String[] of(String[] params) {
						String[] result = new String[VALUE_BASE_RESUYRCE_PACKAGE.length + params.length];
						System.arraycopy(VALUE_BASE_RESUYRCE_PACKAGE, 0, result, 0, VALUE_BASE_RESUYRCE_PACKAGE.length);
						System.arraycopy(params, 0, result, VALUE_BASE_RESUYRCE_PACKAGE.length, params.length);
						return result;
					}
				}).of(this.resourceScanPackage);
	}

	private List<Properties> buildProperties() throws PackageScanSupportError {
		ResourcePatternResolver resourceLoader = new PathMatchingResourcePatternResolver();
		String locationPath = System.getProperty(NAME_CONFIG_LOCATION_FIle);
		String[] propertiesloadpath = null == locationPath ? VALUE_DEFAULT_LOCATION : (new Refer<String[], String>() {
			@Override
			public String[] of(String... params) {
				return Optional.ofNullable(params).map(f -> {
					return f[0];
				}).map(f -> {
					return f.split(",");
				}).map(f -> {
					String[] ret = new String[f.length + VALUE_DEFAULT_LOCATION.length];
					System.arraycopy(f, 0, ret, 0, f.length);
					System.arraycopy(VALUE_DEFAULT_LOCATION, 0, ret, f.length, VALUE_DEFAULT_LOCATION.length);
					return ret;
				}).orElse(VALUE_DEFAULT_LOCATION);
			}
		}).of(locationPath);
		List<Properties> list = new ArrayList<>(propertiesloadpath.length);
		for (int index = propertiesloadpath.length - 1; -1 < index; index--) {
			String path = propertiesloadpath[index];
			try {
				Resource[] resources = resourceLoader.getResources(path);
				for (Resource resource : resources) {
					if (resource.exists()) {
						Properties p = new Properties();
						p.load(resource.getInputStream());
						list.add(p);
					}
				}
			} catch (IOException e) {
				throw new PackageScanSupportError(e);
			}
		}
		return list;
	}
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
/**
 * 用于标记自身绑定关系
 * 
 * @author chenkui
 *
 */
@interface ConfigBind {
	boolean first() default false;

	String propName(); // properties 绑定参数

	Class<? extends Annotation> annot(); // 注解绑定参数

	Class<? extends Bind> excute();
}

class Params {
	Field field;
	ConfigBind configBind;
	Class<?> klass;
	PackageScan parent;
	List<Properties> properties;

	public Params(Field field, ConfigBind configBind, Class<?> klass, PackageScan parent, List<Properties> properties) {
		super();
		this.field = field;
		this.configBind = configBind;
		this.klass = klass;
		this.parent = parent;
		this.properties = properties;
	}
}

abstract class Bind {
	static Logger LOG = LoggerFactory.getLogger(Bind.class);

	abstract String[] getDefaultValue(Params params);

	public String[] execute(Params params) throws IllegalArgumentException, IllegalAccessException {
		String[] result = getDefaultValue(params);
		for (Properties p : params.properties) {
			result = Optional.ofNullable(p.getProperty(params.configBind.propName())).map(f -> {
				return f.split(",");
			}).orElse(result);
		}
		result = Optional.ofNullable(params.klass).map(f -> {
			String[] ret = null;
			try {
				ret = valuse(params);
			} catch (InternalException e) {
				LOG.error("Init Config from lacunch class Error, User default config! Launch Class: "
						+ params.klass.getName(), e);
			}
			return ret;
		}).orElse(result);
		params.field.setAccessible(true);
		params.field.set(params.parent, result);
		return result;
	}

	private String[] valuse(Params params) throws InternalException {
		Annotation annot = params.klass.getDeclaredAnnotation(params.configBind.annot());
		String[] result = null;
		if (null != annot) {
			try {
				Method method = annot.getClass().getMethod("value");
				result = (String[]) method.invoke(annot);
			} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException e) {
				throw new InternalException(e);
			}
		}
		return result;
	}
}

// 基础路径配置
class BaseScanBind extends Bind {
	@Override
	String[] getDefaultValue(Params params) {
		final String[] result = { params.parent.getLaunchPackage() };
		return result;
	}
}

// Bean与Jpa相关配置
class BeanAndJpaBind extends Bind {
	@Override
	String[] getDefaultValue(Params params) {
		return params.parent.getBaseScanPackage();
	}
}

// Bean与Jpa相关配置
class ResourceBind extends Bind {
	@Override
	String[] getDefaultValue(Params params) {
		final String[] result = null;
		return result;
	}
}

class InternalException extends Exception {
	private static final long serialVersionUID = 1L;

	public InternalException(Exception e) {
		super(e);
	}
}