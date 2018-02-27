package com.palmg.boot.webcore.scan.conversion.impl;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import com.palmg.boot.webcore.Application;
import com.palmg.boot.webcore.scan.PackageScan;
import com.palmg.boot.webcore.scan.conversion.AnnotConver;
import com.palmg.boot.webcore.scan.conversion.AnnotConverExceprion;

public class JpaEntiryScanConver extends AnnotConver {

	@Override
	public void conver(Class<?> converClass, PackageScan scan) throws AnnotConverExceprion {
		modify(Application.class, EntityScan.class, "basePackages", scan.getEntityScanPackage());
	}
}
