package com.palmg.boot.webstarter.sample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import com.palmg.boot.webcore.Aaron;
import com.palmg.boot.webcore.annotation.BeanScan;
import com.palmg.boot.webcore.annotation.JpaDaoScan;
import com.palmg.boot.webcore.annotation.JpaEntityScan;
import com.palmg.boot.webcore.anonymous.Anony;
import com.palmg.boot.webstarter.sample.entity.User;
import com.palmg.boot.webstarter.sample.service.AutoCommitService;

@BeanScan("com.palmg.boot.webstarter.sample")
@JpaEntityScan("com.palmg.boot.webstarter.sample.entity")
@JpaDaoScan("com.palmg.boot.webstarter.sample.dao")
/**
 * 使用Jpa进行增删改查的案例
 * 
 * @author chenkui
 *
 */
public class JapSampleApp {
	static Logger LOG = LoggerFactory.getLogger(JapSampleApp.class);

	public static void main(String[] args) {
		ApplicationContext context = new Aaron(JapSampleApp.class).launch(args);
		AutoCommitService service = context.getBean(AutoCommitService.class);
		long id = service.addOne();// 添加一条数据。
		LOG.info("Insert one row. The id is: " + id);

		// 一次性添加多条数据
		long[] ids = service.addMulit();
		LOG.info("Insert mulitrow. The id is: " + Anony.of(() -> {
			StringBuilder sb = new StringBuilder();
			for (long i : ids) {
				sb.append(i);
				sb.append(";");
			}
			return sb.toString();
		}));

		// 查询一条数据
		LOG.info("Query one row by id=" + id + ". Result:" + service.queryById(id));
		LOG.info("Query all row. Result:" + service.queryAll());

		// 修改数据
		LOG.info("Modify id=" + id);
		service.modify(new User(id, "ModifyName", "ModifyAddress"));
		LOG.info("Now result is " + service.queryAll());

		// 删除一条数据
		LOG.info("Delete one row that id=" + id);
		service.deleteById(id);
		LOG.info("Then the result is " + service.queryAll());

		// 删除所有数据
		LOG.info("Now delete all row! Current thread will be blocked!!");
		service.deleteAll();
		LOG.info("Then the result is " + service.queryAll());
		System.exit(0);
	}
}
