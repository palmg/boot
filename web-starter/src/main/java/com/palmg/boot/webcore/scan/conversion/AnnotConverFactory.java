package com.palmg.boot.webcore.scan.conversion;

import java.lang.annotation.Annotation;

import com.palmg.boot.webcore.scan.conversion.impl.BeanScanConver;
import com.palmg.boot.webcore.scan.conversion.impl.JpaDaoScanConver;
import com.palmg.boot.webcore.scan.conversion.impl.JpaEntiryScanConver;
import com.palmg.boot.webcore.scan.conversion.impl.ResourceScanConver;

public class AnnotConverFactory {
	public AnnotConver build(Class<? extends Annotation> annotClass) {
		switch (annotClass.getSimpleName()) {
		case "BeanScan":
			return new BeanScanConver();
		case "JpaEntityScan":
			return new JpaEntiryScanConver();
		case "JpaDaoScan":
			return new JpaDaoScanConver();
		case "ResourceScan":
			return new ResourceScanConver();
		default:
			return null; 
		}
	}
}
