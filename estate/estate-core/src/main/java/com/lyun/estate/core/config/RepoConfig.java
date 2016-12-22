package com.lyun.estate.core.config;

import com.github.miemiedev.mybatis.paginator.dialect.PostgreSQLDialect;
import com.lyun.estate.core.repo.OffsetLimitInterceptor;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@PropertySource(value = "classpath:estate/core/database.properties", ignoreResourceNotFound = true)
@PropertySource(value = "classpath:file://${LVJINSUO_HOME}/conf/estate/core/database.properties", ignoreResourceNotFound = true)
public class RepoConfig {

    @Resource
    private Environment env;

    @Bean(destroyMethod = "close")
    public DataSource dataSource() {
        return DataSourceBuilder.create()
                .type(HikariDataSource.class)
                .driverClassName(env.getProperty("db.driver.classname"))
                .url(env.getProperty("db.url"))
                .username(env.getProperty("db.username"))
                .password(env.getProperty("db.password"))
                .build();
    }

    @Bean
    public SqlSessionFactoryBean sqlSessionFactory(DataSource dataSource) {
        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(dataSource);

        OffsetLimitInterceptor offsetLimitInterceptor = new OffsetLimitInterceptor();
        offsetLimitInterceptor.setDialectClass(PostgreSQLDialect.class.getName());
        sqlSessionFactory.setPlugins(new Interceptor[]{offsetLimitInterceptor});

        return sqlSessionFactory;
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        sqlSessionFactory.getConfiguration().setMapUnderscoreToCamelCase(true);
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean
    public PlatformTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

}
