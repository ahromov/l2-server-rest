package hromov.forum.configuration;

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
 * @author Андрій
 *
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "hromov.forum.repo", entityManagerFactoryRef = "forumEntityManager", transactionManagerRef = "forumTransactionManager")
public class PersistenceForumAutoConfiguration {

    @Bean(name = "forumDataSource")
    @ConfigurationProperties(prefix = "spring.datasource-forum")
    public DataSource forumDataSource() {
	return DataSourceBuilder.create().build();
    }

    @Bean(name = "forumEntityManager")
    public LocalContainerEntityManagerFactoryBean forumEntityManagerFactory(EntityManagerFactoryBuilder builder,
	    @Qualifier("forumDataSource") DataSource dataSource) {
	return builder.dataSource(dataSource).packages("hromov.forum.model").persistenceUnit("l2jforum").build();
    }

    @Bean(name = "forumTransactionManager")
    public PlatformTransactionManager gameTransactionManager(
	    @Qualifier("forumEntityManager") EntityManagerFactory entityManagerFactory) {
	return new JpaTransactionManager(entityManagerFactory);
    }

}
