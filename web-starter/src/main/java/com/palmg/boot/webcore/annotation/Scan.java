package com.palmg.boot.webcore.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
/**
 * 用于入口类，指定全局扫描加载路径。如果
 * {@link @BeanScan}
 * {@link @JpaEntityScan}
 * {@link @JpaDaoScan}
 * 及其相关配置不存在，则会用这个路径覆盖。
 * @author chenkui
 *
 */
public @interface Scan {
	/**
	 * 全局扫描路径
	 * @return
	 */
	String[] value() default {};
}
