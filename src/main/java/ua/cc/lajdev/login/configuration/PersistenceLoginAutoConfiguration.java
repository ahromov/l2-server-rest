package ua.cc.lajdev.login.configuration;

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
@EnableJpaRepositories(basePackages = "ua.cc.lajdev.login.repo", entityManagerFactoryRef = "loginEntityManager", transactionManagerRef = "loginTransactionManager")
public class PersistenceLoginAutoConfiguration {

	@Primary
	@Bean(name = "loginDataSource")
	@ConfigurationProperties(prefix = "spring.datasource-login")
	public DataSource loginDataSource() {
		return DataSourceBuilder.create().build();
	}

	@Primary
	@Bean(name = "loginEntityManager")
	public LocalContainerEntityManagerFactoryBean loginEntityManagerFactory(EntityManagerFactoryBuilder builder,
			@Qualifier("loginDataSource") DataSource dataSource) {
		return builder.dataSource(dataSource).packages("ua.cc.lajdev.login.model").persistenceUnit("l2jls").build();
	}

	@Primary
	@Bean(name = "loginTransactionManager")
	public PlatformTransactionManager loginTransactionManager(
			@Qualifier("loginEntityManager") EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}

}