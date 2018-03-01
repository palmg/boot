package com.palmg.boot.webstarter.sample;

import com.palmg.boot.webcore.Aaron;
import com.palmg.boot.webcore.annotation.BeanScan;
import com.palmg.boot.webcore.annotation.JpaDaoScan;
import com.palmg.boot.webcore.annotation.JpaEntityScan;
import com.palmg.boot.webcore.annotation.ResourceScan;
import com.palmg.boot.webcore.annotation.Scan;

/**
 * 使用指定的类启动
 * {@link Scan} 默认全局扫描路径。如果没有配置以下任何路径，会用这个基础配置代替。
 * {@link BeanScan} 指定spring ioc 容器的扫描路径。扫描路径下使用了@Component及其子注解的类都会添加到容器中。
 * {@link JpaEntityScan} 指定用于JPA中的实体类的扫描路径。
 * {@link JpaDaoScan} 指定用于JPA操作Dao（Repository）接口的扫描路径。
 * {@link ResourceScan} 指定spring xml资源文件的加载路径。路径指定的资源需满足spring的资源加载器规则，见{@link ResourcePatternResolver}、{@link ResourceLoader}。
 * 
 * 如果不指定这些扫描路径，默认使用入口类 WithClassMain 所在的包作为根路径向下扫描。见{@link WithoutClassMain}
 * @author chenkui
 */
@Scan("com.palmg.boot.webstarter.sample") 
@BeanScan("com.palmg.boot.webstarter.sample") 
@JpaEntityScan("com.palmg.boot.webstarter.sample.entity")
@JpaDaoScan("com.palmg.boot.webstarter.sample.dao")
@ResourceScan("classpath:/palmg/config/myapp-*.xml") 
public class WithClassMain {
	public static void main(String[] args) {
		new Aaron(WithClassMain.class).launch(args);
	}
}
