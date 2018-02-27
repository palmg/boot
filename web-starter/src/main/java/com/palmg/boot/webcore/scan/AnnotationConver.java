package com.palmg.boot.webcore.scan;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.List;

import com.palmg.boot.webcore.Application;
import com.palmg.boot.webcore.annotation.BeanScan;
import com.palmg.boot.webcore.annotation.JpaDaoScan;
import com.palmg.boot.webcore.annotation.JpaEntityScan;
import com.palmg.boot.webcore.annotation.ResourceScan;
import com.palmg.boot.webcore.scan.conversion.AnnotConver;
import com.palmg.boot.webcore.scan.conversion.AnnotConverExceprion;
import com.palmg.boot.webcore.scan.conversion.AnnotConverFactory;

public class AnnotationConver {
	private PackageScan packageScan;
	private AnnotConverFactory factory;
	private List<Class<? extends Annotation>> annots;
	public AnnotationConver(PackageScan packageScan) {
		this.packageScan = packageScan;
		factory = new AnnotConverFactory();
		annots = Arrays.asList(BeanScan.class, JpaDaoScan.class, JpaEntityScan.class, ResourceScan.class);
	}
	public void conver() throws AnnotConverExceprion {
		for(Class<? extends Annotation> annotClass: annots) {
			AnnotConver conver = factory.build(annotClass);
			conver.conver(Application.class, packageScan);
		}
	}
}
