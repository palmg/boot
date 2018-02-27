package com.palmg.boot.webcore.scan.conversion.impl;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.palmg.boot.webcore.Application;
import com.palmg.boot.webcore.scan.PackageScan;
import com.palmg.boot.webcore.scan.conversion.AnnotConver;
import com.palmg.boot.webcore.scan.conversion.AnnotConverExceprion;

public class JpaDaoScanConver extends AnnotConver  {

	@Override
	public void conver(Class<?> converClass, PackageScan scan) throws AnnotConverExceprion {
		modify(Application.class, EnableJpaRepositories.class, "basePackages", scan.getJpaScanPackage());
	}
}
