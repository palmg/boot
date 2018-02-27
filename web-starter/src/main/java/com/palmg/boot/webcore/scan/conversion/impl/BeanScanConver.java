package com.palmg.boot.webcore.scan.conversion.impl;

import org.springframework.context.annotation.ComponentScan;

import com.palmg.boot.webcore.Application;
import com.palmg.boot.webcore.scan.PackageScan;
import com.palmg.boot.webcore.scan.conversion.AnnotConver;
import com.palmg.boot.webcore.scan.conversion.AnnotConverExceprion;

public class BeanScanConver extends AnnotConver {
	@Override
	public void conver(Class<?> converClass, PackageScan scan) throws AnnotConverExceprion {
		modify(Application.class, ComponentScan.class, "basePackages", scan.getBeanScanPackage());
	}
}
