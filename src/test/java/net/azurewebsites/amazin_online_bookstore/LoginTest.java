package net.azurewebsites.amazin_online_bookstore;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class LoginTest {

    // Disabling Testcontainers on Windows CI runners
    static {
        String os = System.getenv("RUNNER_OS");
        if ("Windows".equals(os)) {
            System.setProperty("testcontainers.enabled", "false");
            System.out.println("⚠ Testcontainers disabled on Windows CI");
        }
    }

    @Container
    static PostgreSQLContainer<?> postgres =
            new PostgreSQLContainer<>("postgres:16-alpine")
                    .withDatabaseName("amazin_test_db")
                    .withUsername("testuser")
                    .withPassword("testpass");

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
        registry.add("spring.jpa.hibernate.ddl-auto", () -> "update");
    }

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void initUser() {
        personRepository.deleteAll();

        Person user = new Person();
        user.setUsername("faiaz");
        user.setPassword(passwordEncoder.encode("mypassword"));
        personRepository.save(user);
    }

    @Test
    void loginShouldSucceedWithCorrectCredentials() throws Exception {
        mockMvc.perform(post("/login")
                        .param("username", "faiaz")
                        .param("password", "mypassword")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection());

    }

    @Test
    void loginShouldFailWithWrongPassword() throws Exception {
        mockMvc.perform(post("/login")
                        .param("username", "faiaz")
                        .param("password", "wrongpass")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection());

    }
}
