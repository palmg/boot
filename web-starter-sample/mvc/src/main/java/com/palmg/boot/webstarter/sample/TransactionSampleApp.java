package com.palmg.boot.webstarter.sample;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import com.palmg.boot.webcore.Aaron;
import com.palmg.boot.webcore.annotation.BeanScan;
import com.palmg.boot.webcore.annotation.JpaDaoScan;
import com.palmg.boot.webcore.annotation.JpaEntityScan;
import com.palmg.boot.webstarter.sample.service.TransactionService;

/**
 * 使用指定的类启动
 * 
 * @author chenkui
 *
 */
@BeanScan("com.palmg.boot.webstarter.sample")
@JpaEntityScan("com.palmg.boot.webstarter.sample.entity")
@JpaDaoScan("com.palmg.boot.webstarter.sample.dao")
/**
 * 事物相关的案例。
 * 切记，Transactional异常只处理RuntimeException 和 Error以及子类, Exception是不会触发回滚的
 * @author chenkui
 */
public class TransactionSampleApp {
	static Logger LOG = LoggerFactory.getLogger(TransactionSampleApp.class);

	public static void main(String[] args) {
		ApplicationContext context = new Aaron(TransactionSampleApp.class).launch(args);
		annotationTranscation(context);
		statementTranscation(context);
		System.exit(0);
	}
	
	/**
	 * 演示spring的上层封装的注解事务
	 * @param context
	 */
	static void annotationTranscation(ApplicationContext context) {
		TransactionService service = context.getBean(TransactionService.class);
		try {
			service.updateMultiTable(true);//抛出异常，数据无法添加。
		}catch(Exception e) {
			LOG.error("Catch Throw Error!");
		}
		try {
			service.transactionPropagation();//事物绑定
		}catch(Exception e) {
			LOG.error("Catch Throw Error!");
		}
		service.updateMultiTable(false); //不抛出异常，数据添加成功。
	}
	
	/**
	 * 演示JDBC低层事务
	 * @param context
	 */
	static void statementTranscation(ApplicationContext context) {
		DataSource datasource = context.getBean(DataSource.class);
		try (Connection con = datasource.getConnection();) {
			con.setAutoCommit(false);//关闭自动提交
			LOG.info("Is Auto Commit :" + con.getAutoCommit());
			Statement stm = con.createStatement();
			try {
				stm.executeUpdate("INSERT INTO USER (NAME,ADDRESS) value(\"React\",\"GuiYang\");");
				stm.executeUpdate("INSERT INTO USER (NAME,ADDRESS) value(\"MyBaties\",\"ShangHai\");");
				con.commit(); //提交
			}catch(SQLException e) {
				con.rollback(); //回滚
				LOG.error("Execute Statement Error", e);
			}
		} catch (SQLException e) {
			LOG.error("Error", e);
		}
	}
}



