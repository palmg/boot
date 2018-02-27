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

public class Aaron {
	static Logger LOG = LoggerFactory.getLogger(Aaron.class);
	
	private PackageScan packageScan;

	public static void main(String[] args) {
		new Aaron(false).launch(args);
	}

	private Aaron(boolean unUseStackTrace){
		packageScan = new PackageScan();
	}
	
	public Aaron() {
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
			packageScan.build();
		} catch (PackageScanSupportError e) {
			LOG.error("Init package config error!  exit(0)", e);
			System.exit(0);
		}

		Properties properties = null;
		try {
			properties = PropertyCrypto.getNew().decryptFile();
		} catch (FileLoadException e) {
			LOG.error("Load crypto properties file error! exit(0)", e);
			System.exit(0);
		}
		properties.put("spring.config.name", "palmg-boot");
		try {
			new AnnotationConver(this.packageScan).conver();
		} catch (AnnotConverExceprion e) {
			LOG.error("Conversion Annotation Configu Error! exit(0)", e);
			System.exit(0);
		}
		SpringApplication app = new SpringApplication(Application.class);
		app.setDefaultProperties(properties);
		app.setBannerMode(Banner.Mode.OFF);
		return app.run(args);
	}
}
