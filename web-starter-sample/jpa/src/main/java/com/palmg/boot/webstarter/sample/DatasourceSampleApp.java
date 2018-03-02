package com.palmg.boot.webstarter.sample;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import com.palmg.boot.webcore.Aaron;
import com.palmg.boot.webcore.annotation.BeanScan;
import com.palmg.boot.webcore.annotation.JpaDaoScan;
import com.palmg.boot.webcore.annotation.JpaEntityScan;
import com.palmg.boot.webcore.annotation.Scan;
import com.palmg.boot.webstarter.sample.entity.Address;
import com.palmg.boot.webstarter.sample.entity.User;

/**
 * 数据库链接串：mysql -uwebcore -pWebcore123456 -hrds5xt46235h36f1cm0t.mysql.rds.aliyuncs.com
 * 使用JDBC的数据源直接操作数据库，由于直接使用底层Statement接口，所以存在数据库兼容性问题。目前仅仅能在MySql运行。
 * @author chenkui
 */
@Scan("com.palmg.boot.webstarter.sample.nan")
@BeanScan("com.palmg.boot.webstarter.sample.nan")
@JpaEntityScan("com.palmg.boot.webstarter.sample.entity.nan")
@JpaDaoScan("com.palmg.boot.webstarter.sample.dao.nan")
public class DatasourceSampleApp {
	static Logger LOG = LoggerFactory.getLogger(DatasourceSampleApp.class);
	static String[] BussTable = { "user", "address" };

	public static void main(String[] args) {
		ApplicationContext context = new Aaron(DatasourceSampleApp.class).launch(args);
		run(context);
		System.exit(0);
	}
	
	public static void run(ApplicationContext context) {
		DataSource datasource = context.getBean(DataSource.class);
		try (Connection con = datasource.getConnection();) {
			LOG.info("Is Auto Commit :" + con.getAutoCommit());
			showTable(con);//显示当前表信息
			deleteTable(con);//删除对应的表
			createTable(con, showTable(con));//添加表
		} catch (SQLException e) {
			LOG.error("Error", e);
		}
	}

	//删除表
	static void deleteTable(Connection connect) throws SQLException {
		Statement stm = connect.createStatement();
		for(String name : BussTable) {
			stm.addBatch("DROP TABLE " + name + ";");
		}
		int[] exeCount = stm.executeBatch();
		LOG.info("Execute command count:" + exeCount);
	}

	// 查看当前链接中的表
	static Map<String, Boolean> showTable(Connection connect) throws SQLException {
		final Map<String, Boolean> checkTableName = new HashMap<>();
		for(String name : BussTable) {
			checkTableName.put(name, false);
		}
		DatabaseMetaData dbMeta = connect.getMetaData();
		String[] types = { "TABLE" };
		ResultSet resultSet = dbMeta.getTables(null, null, "", types);
		ResultSetMetaData meta = resultSet.getMetaData();
		final int len = meta.getColumnCount() + 1;
		for (int i = 1; i < len; i++) {
			LOG.info("Column info: index=" + i + " Name=" + meta.getColumnName(i) + "; Label=" + meta.getColumnLabel(i)
					+ "; Type:" + meta.getColumnTypeName(i));
		}
		while (resultSet.next()) {
			final String tableName = resultSet.getString(3);
			if (checkTableName.containsKey(tableName)) {
				checkTableName.put(tableName, true);
			}
			LOG.info("Current Table Name: " + tableName);
		}
		return checkTableName;
	}

	// 动态创建表
	static void createTable(Connection connect, Map<String, Boolean> map) {
		map.forEach((k, v) -> {
			if (!v) {
				String sql = null;
				switch (k.toLowerCase()) {
				case "user":
					sql = User.statement();
					break;
				case "address":
					sql = Address.statement();
					break;
				default:
					break;
				}
				if (null != sql) {
					try {
						Statement stm = connect.createStatement();
						stm.executeUpdate(sql);
						LOG.info("Excute :" + sql + " Success!");
					} catch (SQLException e) {
						LOG.error("Error", e);
					}
				}
			}
		});
	}
}
