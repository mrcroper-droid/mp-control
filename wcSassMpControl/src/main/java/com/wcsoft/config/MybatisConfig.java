package com.wcsoft.config;



import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.github.pagehelper.PageInterceptor;


@Configuration // 注册到springboot容器中  
@MapperScan(basePackages = "./mapper", sqlSessionTemplateRef = "sqlSessionTemplate")
public class MybatisConfig {
	// 创建 DataSource
		@Bean(name = "dataSource") // 表示注入到Spring 容器中去
		@ConfigurationProperties(prefix = "spring.datasource") // properties中读取
		public DataSource dataSource() {
			return DataSourceBuilder.create().build();
		}
	  
		// 创建 SqlSessionFactory 
		@Bean(name = "sqlSessionFactory")
		@Primary // 表示这个数据源是默认数据源 
	    // 读取application-dev.properties中的配置参数映射成为一个对象
		public SqlSessionFactory sqlSessionFactory(@Qualifier("dataSource") DataSource dataSource) throws Exception {
			SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
			
			//分页插件
	        Interceptor interceptor = new PageInterceptor();
	        Properties properties = new Properties();
	        //数据库
	        properties.setProperty("helperDialect", "mysql");
	        //是否将参数offset作为PageNum使用
	        properties.setProperty("offsetAsPageNum", "true");
	        //是否进行count查询
	        properties.setProperty("rowBoundsWithCount", "true");
	        //是否分页合理化
	        properties.setProperty("reasonable", "true");
	        interceptor.setProperties(properties);
	        bean.setPlugins(new Interceptor[] {interceptor});
			bean.setDataSource(dataSource);
			bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:./mapper/*.xml"));
			return bean.getObject();
		}
	 
	 
		// 包装到 SqlSessionTemplate 中
		@Bean(name = "sqlSessionTemplate")
		@Primary
		public SqlSessionTemplate sqlSessionTemplate(@Qualifier("sqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
			return new SqlSessionTemplate(sqlSessionFactory);
		}

}
