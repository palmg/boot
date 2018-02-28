package com.palmg.boot.webcore;

import org.springframework.boot.autoconfigure.AutoConfigurationExcludeFilter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.TypeExcludeFilter;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableAutoConfiguration
@ComponentScan(excludeFilters = {
		@Filter(type = FilterType.CUSTOM, classes = TypeExcludeFilter.class),
		@Filter(type = FilterType.CUSTOM, classes = AutoConfigurationExcludeFilter.class)})
@Configuration
@EntityScan
@EnableJpaRepositories
@ImportResource
@EnableTransactionManagement
/**
 * Application——全局配置入口
 * 该类没有任何实体，基本上是通过注解引导spring boot工作。
 * 注解上没有任何数值。这些数值会在{@link com.palmg.boot.webcore.Aaron} 中根据入口位置和配置参数被修改。<br>
 * {@link @EnableAutoConfiguration} 用于告知spring boot启用自动推导配置。
 * {@link @ComponentScan} 用于指定spring core容器扫描bean的路径。
 * {@link @Configuration} 告知IOC这是一个用于配置的bean。
 * {@link @EntityScan} 指定Jpa实体的扫描。
 * {@link @EnableJpaRepositories} 指定Jpa Dao的扫描位置。
 * {@link @ImportResource} 引入外部配置文件的配置。
 * {@link @EnableTransactionManagement} 启用事物管理。
 * @author chenkui
 *
 */
public class Application {}