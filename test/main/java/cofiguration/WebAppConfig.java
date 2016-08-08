package cofiguration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by Vitalii on 8/6/2016.
 */
@Configuration
@ComponentScan(basePackages = { "controllers", "dao", "models", "service"})
@EnableWebMvc
@EnableTransactionManagement
@PropertySource("application.properties")

public class WebAppConfig extends WebMvcConfigurerAdapter {

    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    private static final String URL = "jdbc:mysql://localhost:3306/test";
    private static final String PROPERTY_NAME_DIALECT = "hibernate.dialect";
    private static final String PROPERTY_NAME_SHOW_SQL = "hibernate.show_sql";
    private static final String PACKAGES_TO_SCAN = "models";


    @Resource
    private Environment environment;

    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName(DRIVER);
        dataSource.setUrl(URL);
        dataSource.setUsername(USERNAME);
        dataSource.setPassword(PASSWORD);

        return dataSource;
    }

    private Properties hibernateProperties() {
        Properties properties = new Properties();

        properties.put(PROPERTY_NAME_DIALECT, environment.getRequiredProperty(PROPERTY_NAME_DIALECT));
        properties.put(PROPERTY_NAME_SHOW_SQL, environment.getRequiredProperty(PROPERTY_NAME_SHOW_SQL));

        return properties;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();

        sessionFactoryBean.setDataSource(dataSource());
        sessionFactoryBean.setPackagesToScan(PACKAGES_TO_SCAN);
        sessionFactoryBean.setHibernateProperties(hibernateProperties());

        return sessionFactoryBean;
    }

    @Bean
    public HibernateTransactionManager transactionManager() {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();

        transactionManager.setSessionFactory(sessionFactory().getObject());

        return transactionManager;
    }

    @Bean
    public UrlBasedViewResolver setupViewResolver() {
        UrlBasedViewResolver resolver = new UrlBasedViewResolver();

        resolver.setPrefix("/WEB-INF/pages/");
        resolver.setSuffix(".jsp");
        resolver.setViewClass(JstlView.class);

        return resolver;
    }
}
