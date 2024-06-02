package com.thereadingroom.integration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.jdbc.JdbcTestUtils;
import org.testcontainers.containers.Network;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.images.PullPolicy;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import javax.sql.DataSource;

@Testcontainers
public abstract class IntegrationTestInit {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    DataSource dataSource;

    @Autowired
    static Network network;

//    @Container
//    protected static final PostgreSQLContainer<?> POSTGRES_CONTAINER =
//            new PostgreSQLContainer<>("postgres:latest")
//                    .withDatabaseName("booking-test")
//                    .withUsername("testUser")
//                    .withPassword("testPassword");

    @Container
    protected static final PostgreSQLContainer<?> POSTGRES_CONTAINER = new PostgreSQLContainer<>(
            DockerImageName.parse("gebozhinov/postgres-testcontainer:latest").asCompatibleSubstituteFor("postgres"))
            .withImagePullPolicy(PullPolicy.alwaysPull())
            .withCreateContainerCmdModifier(cmd -> cmd.withName("postgres-testcontainer"))
            .withNetwork(network)
            .withDatabaseName("booking-test")
            .withUsername("testUser")
            .withPassword("testPassword")
            .withCommand("-c log_statement=all")
            .withExposedPorts(5432);


    static {
        POSTGRES_CONTAINER.start();
    }

    @DynamicPropertySource
    private static void registerPgProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", POSTGRES_CONTAINER::getJdbcUrl);
        registry.add("spring.datasource.username", POSTGRES_CONTAINER::getUsername);
        registry.add("spring.datasource.password", POSTGRES_CONTAINER::getPassword);
    }

    protected void insertDatabase(String filePath) {
        ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator();
        resourceDatabasePopulator.addScript(new ClassPathResource(filePath));
        resourceDatabasePopulator.execute(dataSource);
    }

    protected void cleanDatabase() {
        JdbcTestUtils.deleteFromTables(jdbcTemplate, "public.books");
    }
}
