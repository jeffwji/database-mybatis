package net.tinybrick.database.mybatis.unit;

import net.tinybrick.database.mybatis.unit.dao.TestDao;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

//import com.htche.database.tx.configuration.TransactionManagerConfigure;
import net.tinybrick.database.mybatis.configuration.MyBatisConfiguration;
import net.tinybrick.database.mybatis.unit.configuration.TestDatabaseConfigure.MultipleDataSourceConfig;
import net.tinybrick.database.mybatis.unit.configuration.TestDatabaseConfigure.SingleDataSourceConfig;

public class MyBatisConfigTestCase {

	@RunWith(SpringJUnit4ClassRunner.class)
	@IntegrationTest({
			"mybatis.mapper.locations:classpath:com/wang/database/mybatis/mapper/*Mapper.xml",
			"mybatis.config.location:classpath:com/wang/database/mybatis/mapper/Configura.xml",
			"mybatis.basePackage:com.wang.database.mybatis.unit.dao",
			"mybatis.datasource.name:dataSource1",
			"database.ds1.driverClassName:com.mysql.jdbc.Driver",
			"database.ds1.url:jdbc:mysql://db01.dev.wang.com/test01?autoReconnect=true&amp;useUnicode=true&amp;characterEncoding=utf8",
			"database.ds1.username:test01", "database.ds1.password:dDcuH1WOOKzdr49xYaoL" })
	@EnableTransactionManagement
	@SpringApplicationConfiguration(classes = { SingleDataSourceConfig.class, MyBatisConfiguration.class/*,
			TransactionManagerConfigure.class*/ })
	public static class SingleDataSourceTestCase {
		Logger logger = Logger.getLogger(this.getClass());
		@Autowired
		TestDao testDao;

		@Transactional
		@Test
		public void testMyBatisConfig() {
			Assert.assertNotNull(testDao);
		}
	}

	@RunWith(SpringJUnit4ClassRunner.class)
	@IntegrationTest({
			"mybatis.mapper.locations:classpath:com/wang/database/mybatis/mapper/*Mapper.xml",
			"mybatis.config.location:classpath:com/wang/database/mybatis/mapper/Configura.xml",
			"mybatis.basePackage:com.wang.database.mybatis.unit.dao",
			"mybatis.datasource.name:dataSource2",
			"database.ds1.driverClassName:com.mysql.jdbc.jdbc2.optional.MysqlXADataSource",
			"database.ds1.url:jdbc:mysql://db01.dev.wang.com/test01?autoReconnect=true&amp;useUnicode=true&amp;characterEncoding=utf8",
			"database.ds1.username:test01",
			"database.ds1.password:dDcuH1WOOKzdr49xYaoL",
			"database.ds2.driverClassName:com.mysql.jdbc.jdbc2.optional.MysqlXADataSource",
			"database.ds2.url:jdbc:mysql://db01.dev.wang.com/test02?autoReconnect=true&amp;useUnicode=true&amp;characterEncoding=utf8",
			"database.ds2.username:test02", "database.ds2.password:UsAbOuncMovDYRynPgO9" })
	@EnableTransactionManagement
	@SpringApplicationConfiguration(classes = { MultipleDataSourceConfig.class, MyBatisConfiguration.class /*,
			TransactionManagerConfigure.class*/ })
	public static class MultipleDataSourceTestCase {
		Logger logger = Logger.getLogger(this.getClass());

		@Autowired TestDao testDao;

		@Transactional
		@Test
		public void testMyBatisConfig() {
			Assert.assertNotNull(testDao);
		}
	}
}
