package net.azurewebsites.amazin_online_bookstore;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Testcontainers
class BookRepositoryIT {

    // Start a real Postgres DB in Docker for this test class
    @Container
    static PostgreSQLContainer<?> postgres =
            new PostgreSQLContainer<>("postgres:16-alpine")
                    .withDatabaseName("amazin_test_db")
                    .withUsername("testuser")
                    .withPassword("testpass");

    // Tell Spring Boot to use the container's JDBC URL/credentials
    @DynamicPropertySource
    static void registerDataSourceProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
        registry.add("spring.jpa.hibernate.ddl-auto", () -> "update");
    }

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private PurchaseRepository purchaseRepository;

    @BeforeEach
    void setUp() {
        purchaseRepository.deleteAll();
        bookRepository.deleteAll();

        Book hobbit = new Book();
        hobbit.setTitle("The Hobbit");
        hobbit.setAuthor("J.R.R. Tolkien");
        hobbit.setGenre("Fantasy");
        hobbit.setInventory(3);
        hobbit.setPrice(25.00);
        bookRepository.save(hobbit);

        Book dune = new Book();
        dune.setTitle("A Tale of Two Cities");
        dune.setAuthor("Charles Dickens");
        dune.setGenre("Historical");
        dune.setInventory(5);
        dune.setPrice(30.00);
        bookRepository.save(dune);
    }

    @Test
    void testcontainersShouldStartAndPersistBooks() {
        List<Book> all = bookRepository.findAll();

        // Proves: container started, schema created, JPA + Postgres working
        assertThat(all).hasSize(2);
        assertThat(all)
                .extracting(Book::getTitle)
                .containsExactlyInAnyOrder("The Hobbit", "A Tale of Two Cities");
    }
}
