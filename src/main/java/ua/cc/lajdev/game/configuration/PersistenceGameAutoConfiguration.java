package hromov.game.configuration;

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
@EnableJpaRepositories(basePackages = "hromov.game.repo", entityManagerFactoryRef = "gameEntityManager", transactionManagerRef = "gameTransactionManager")
public class PersistenceGameAutoConfiguration {

    @Bean(name = "gameDataSource")
    @ConfigurationProperties(prefix = "spring.datasource-game")
    public DataSource gameDataSource() {
	return DataSourceBuilder.create().build();
    }

    @Bean(name = "gameEntityManager")
    public LocalContainerEntityManagerFactoryBean gameEntityManagerFactory(EntityManagerFactoryBuilder builder,
	    @Qualifier("gameDataSource") DataSource dataSource) {
	return builder.dataSource(dataSource).packages("hromov.game.model").persistenceUnit("l2jgs").build();
    }

    @Bean(name = "gameTransactionManager")
    public PlatformTransactionManager gameTransactionManager(
	    @Qualifier("gameEntityManager") EntityManagerFactory entityManagerFactory) {
	return new JpaTransactionManager(entityManagerFactory);
    }

}
