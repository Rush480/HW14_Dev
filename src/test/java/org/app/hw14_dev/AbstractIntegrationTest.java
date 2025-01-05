package org.app.hw14_dev;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;


@SpringBootTest(
        classes = Hw14DevApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)

@Testcontainers
public class AbstractIntegrationTest {

    @LocalServerPort
    int port;

    public static final String SERVER_BASE_URL = "http://localhost:";
    public static final String API_BASE_URL = "/api/v1";

    TestRestTemplate restTemplate = new TestRestTemplate();


    @Container
    static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:15.0-alpine")
            .withDatabaseName("mydb")
            .withUsername("Rush")
            .withPassword("rush");


    @DynamicPropertySource
    static void configureTestProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
        registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
        registry.add("spring.datasource.password", postgreSQLContainer::getPassword);
        registry.add("spring.jpa.hibernate.ddl-auto", () -> "validate");

    }

    @BeforeAll
    public static void startContainers() {
        postgreSQLContainer.start();
    }
    @AfterAll
    public static void stopContainers() {
        postgreSQLContainer.stop();
    }
//    @Bean
//    public PostgreSQLContainer<?> postgreSQLContainer() {
//        return postgreSQLContainer;
//    }
}
