package com.jhspring.data.config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement  // 트랜잭션 어노테이션 활성화
@EnableJpaRepositories( // 리포지터리 자동으로 Bean으로 등록해줌.
        basePackages = "com.jhspring.data.repository" // 리포지토리 넣을 패키지
)
public class JpaConfig {
    @Autowired
    private DataSource dataSource; // properties 에 있는걸로 자동주입된 dataSource 을 가져옴.

    @Bean // EntityManagerFactory 핵심. EntityManager 생성.
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(dataSource)  // 여기서 dataSource를 연결.
                .packages("com.jhspring.data.entity") // 엔티티 넣을 패키지
                .build();
    }

    @Bean // 트랜잭션 관리 매니저.
    public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
