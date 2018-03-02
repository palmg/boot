package com.palmg.boot.webstarter.sample;

import com.palmg.boot.webcore.Aaron;

/**
 * 使用配置文件来指定扫描路径。详见my-root-config1.properties和classpath:my-root-config2.properties。切记文件转换为UTF-8
 * @author chenkui
 */
public class WithProperties {
	public static void main(String[] args) {
		//palmg.config.location.file 参数用于指定要加载的配置文件。
		//如果不进行任何配置，仅仅加载：
		//classpath:palmg-boot.properties,classpath:/palmg/config/palmg-boot/properties
		System.setProperty("palmg.config.location.file", 
				"classpath:my-root-config1.properties,classpath:my-root-config2.properties");
		//这里配置之后，加载文件的位置变成：
		//1.classpath:my-root-config1.properties
		//2.classpath:my-root-config2.properties
		//3.classpath:palmg-boot.properties
		//4.classpath:/palmg/config/palmg-boot/properties
		new Aaron().launch(args);
	}
}
