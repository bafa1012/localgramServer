package com.hska.localgram.config;

import com.hska.localgram.util.Constants;
import com.hska.localgram.model.AppUser;
import com.hska.localgram.model.Image;
import com.hska.localgram.model.Tag;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.sql.DataSource;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Initializes and configures the application.
 * 
 * @author Fabian Bäuerlein <bafa1012@hs-karlsruhe.de>
 */
@Configuration
@EnableAutoConfiguration
@EnableTransactionManagement
@ComponentScan("com.hska.localgram")
@PropertySource("classpath:database.properties")
@EnableWebMvc
public class AppConfig implements WebApplicationInitializer {

    public AppConfig() {
        super();
    }

    @Resource
    private Environment env;

    // Bean definitions

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName(env.getRequiredProperty(
                Constants.PROPERTY_NAME_DATABASE_DRIVER));
        dataSource.setUrl(env.getRequiredProperty(
                Constants.PROPERTY_NAME_DATABASE_URL));
        dataSource.setUsername(env.getRequiredProperty(
                Constants.PROPERTY_NAME_DATABASE_USERNAME));
        dataSource.setPassword(env.getRequiredProperty(
                Constants.PROPERTY_NAME_DATABASE_PASSWORD));

        return dataSource;
    }

    @Bean
    public MultipartResolver multipartResolver() {
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        resolver.setMaxUploadSize(Constants.MAX_UPLOAD_SIZE);
        return resolver;
    }

    @Bean
    public HibernateTransactionManager transactionManager() {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory()
                .getObject());
        return transactionManager;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource());
        sessionFactoryBean.setPackagesToScan(env.getRequiredProperty(
                Constants.PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN));
        sessionFactoryBean.setAnnotatedClasses(new Class[]{AppUser.class});
        sessionFactoryBean.setAnnotatedClasses(new Class[]{Image.class});
        sessionFactoryBean.setAnnotatedClasses(new Class[]{Tag.class});
        sessionFactoryBean.setHibernateProperties(hibProperties());
        return sessionFactoryBean;
    }

    // Hibernate configuration

    private Properties hibProperties() {
        Properties properties = new Properties();
        properties.put(Constants.PROPERTY_NAME_HIBERNATE_DIALECT, env
                       .getRequiredProperty(
                               Constants.PROPERTY_NAME_HIBERNATE_DIALECT));
        properties.put(Constants.PROPERTY_NAME_HIBERNATE_SHOW_SQL, env
                       .getRequiredProperty(
                               Constants.PROPERTY_NAME_HIBERNATE_SHOW_SQL));
        return properties;
    }

    // Web Configuration

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        WebApplicationContext context = getContext();
        servletContext.addListener(new ContextLoaderListener(context));
        ServletRegistration.Dynamic dispatcher = servletContext.addServlet(
                "localgran", new DispatcherServlet(context));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");
    }

    private AnnotationConfigWebApplicationContext getContext() {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.setConfigLocation("com.hska.localgram.config");
        return context;
    }
}
