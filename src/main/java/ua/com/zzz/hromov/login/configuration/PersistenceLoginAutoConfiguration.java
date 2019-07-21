package ua.com.zzz.hromov.login.configuration;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "ua.com.zzz.hromov.login.repo", entityManagerFactoryRef = "loginEntityManager", transactionManagerRef = "loginTransactionManager")
public class PersistenceLoginAutoConfiguration {

    @Primary
    @Bean(name = "loginDataSource")
    @ConfigurationProperties(prefix = "spring.datasource-login")
    public DataSource loginDataSource() {
	return DataSourceBuilder.create().build();
    }

    /**
     * Entity manager definition.
     * 
     * @param builder an EntityManagerFactoryBuilder.
     * @return LocalContainerEntityManagerFactoryBean.
     */
    @Primary
    @Bean(name = "loginEntityManager")
    public LocalContainerEntityManagerFactoryBean loginEntityManagerFactory(EntityManagerFactoryBuilder builder,
	    @Qualifier("loginDataSource") DataSource dataSource) {
	return builder.dataSource(dataSource).packages("ua.com.zzz.hromov.login.model").persistenceUnit("l2jls")
		.build();
    }

    /**
     * @param entityManagerFactory
     * @return
     */
    @Primary
    @Bean(name = "loginTransactionManager")
    public PlatformTransactionManager loginTransactionManager(
	    @Qualifier("loginEntityManager") EntityManagerFactory entityManagerFactory) {
	return new JpaTransactionManager(entityManagerFactory);
    }

//    @Primary
//    @Bean(name = "accountDataSource")
//    @ConfigurationProperties(prefix = "spring.datasource-login")
//    public DataSource userDataSource() {
//	return DataSourceBuilder.create().build();
//    }
//
//    @Primary
//    @Bean(name = "accountEntityManagerFactory")
//    public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder,
//	    @Qualifier("accountDataSource") DataSource dataSource) {
//	return builder.dataSource(dataSource).packages("ua.com.zzz.hromov.login.model").persistenceUnit("l2jls")
//		.build();
//    }
//
//    @Primary
//    @Bean(name = "accountTransactionManager")
//    public PlatformTransactionManager customerTransactionManager(
//	    @Qualifier("customerEntityManagerFactory") EntityManagerFactory accountEntityManagerFactory) {
//	return new JpaTransactionManager(accountEntityManagerFactory);
//    }

}