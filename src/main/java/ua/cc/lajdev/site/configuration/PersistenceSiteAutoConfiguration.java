package ua.cc.lajdev.site.configuration;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Andrii Hromov
 *
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "ua.cc.lajdev.site.repo", entityManagerFactoryRef = "siteEntityManager", transactionManagerRef = "siteTransactionManager")
public class PersistenceSiteAutoConfiguration {

	@Bean(name = "siteDataSource")
	@ConfigurationProperties(prefix = "spring.datasource-site")
	public DataSource gameDataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "siteEntityManager")
	public LocalContainerEntityManagerFactoryBean gameEntityManagerFactory(EntityManagerFactoryBuilder builder,
			@Qualifier("siteDataSource") DataSource dataSource) {
		return builder.dataSource(dataSource).packages("ua.cc.lajdev.site.model").persistenceUnit("l2site").build();
	}

	@Bean(name = "siteTransactionManager")
	public PlatformTransactionManager gameTransactionManager(
			@Qualifier("siteEntityManager") EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}

}
