package com.senpure.base.configuration;


import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.alibaba.druid.support.http.StatViewServlet;
import com.senpure.base.util.DatabaseUtil;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

/**
 * copy from DruidDataSourceAutoConfigure
 *
 * @author 罗中正
 * @date 2017/12/7 0007
 */


@Configuration
@MapperScan(basePackages = "com.senpure", sqlSessionFactoryRef = "sqlSessionFactory")
@EnableTransactionManagement
@EnableJpaRepositories
@ConditionalOnMissingBean(name= "dataSource")
public class DataSourceAutoConfiguration extends BaseConfiguration {

    @Bean(name = "dataSourceProperties")
    @ConfigurationProperties(prefix = "spring.datasource")
    @Primary
    public DataSourceProperties dataSourceProperties() {
        return new DataSourceProperties();
    }


    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    @Primary
    public DataSource dataSource() {
        DataSourceProperties prop = dataSourceProperties();
        DatabaseUtil.checkAndCreateDatabase(prop);
        return  DruidDataSourceBuilder.create().build();
    }


    @Bean(name = "sqlSessionFactory")
    @Primary
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        //PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        //配置mapper文件位置

        // sqlSessionFactoryBean.setMapperLocations(resolver.getResources(mapperLocations));
       // logger.info("sqlsessionFactory {}", mapperLocations);
        return sqlSessionFactoryBean.getObject();
    }

    @Bean(name = "sqlSessionTemplate")
    @Primary
    public SqlSessionTemplate testSqlSessionTemplate(@Qualifier("sqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
    /**
     * 配置事物管理器
     *
     * @return
     */
    @Bean(name = "transactionManager")
    @Primary
    public DataSourceTransactionManager transactionManager(
            @Qualifier("dataSource") DataSource dataSource) {
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(dataSource);
        return dataSourceTransactionManager;
    }


    @Bean(name = "entityManager")
    @Primary
    public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
        return entityManagerFactorySecondary(builder).getObject().createEntityManager();
    }


    @Bean
    @ConfigurationProperties(prefix = "spring.jpa")
    @Primary
    public JpaProperties jpaProperties() {
        return new JpaProperties();
    }

    @Bean(name = "entityManagerFactory")
    @Primary
    public LocalContainerEntityManagerFactoryBean entityManagerFactorySecondary(EntityManagerFactoryBuilder builder) {

        return builder
                .dataSource(dataSource())
                .properties(jpaProperties().getProperties())
                .packages("com.senpure") //设置实体类所在位置
                .persistenceUnit("persistenceUnit")
                .build();
    }




   // @Bean
    public ServletRegistrationBean DruidStatViewServlet(){
        //org.springframework.boot.context.embedded.ServletRegistrationBean提供类的进行注册.
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(),"/druid/*");
        servletRegistrationBean.setLoadOnStartup(-2);
        //添加初始化参数：initParams

        //白名单：
        //servletRegistrationBean.addInitParameter("allow","127.0.0.1");
        //IP黑名单 (存在共同时，deny优先于allow) : 如果满足deny的话提示:Sorry, you are not permitted to view this page.
        //servletRegistrationBean.addInitParameter("deny","192.168.1.129");
        //登录查看信息的账号密码.
       // servletRegistrationBean.addInitParameter("loginUsername",account);
       // servletRegistrationBean.addInitParameter("loginPassword",passwrod);
        //是否能够重置数据.
        logger.debug("self statViewServlet");
        servletRegistrationBean.addInitParameter("resetEnable","false");
        return servletRegistrationBean;
    }




}
