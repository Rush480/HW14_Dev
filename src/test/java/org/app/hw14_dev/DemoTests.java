package org.app.hw14_dev;

import org.app.hw14_dev.model.dto.request.UserRequest;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.assertj.core.api.Assertions.assertThat;

class DemoTests extends AbstractIntegrationTest {

    UserRequest userCreateRequest = UserRequest.builder()
            .username("denys")
            .email("denys@example.com")
            .password("password")
            .build();
    @Test
    void createUserTest() {


        ResponseEntity<String> signupResponse =
                restTemplate.postForEntity(SERVER_BASE_URL + port + API_BASE_URL + "/auth/signup",
                        userCreateRequest, String.class);


        assertThat(signupResponse.getStatusCode().value()).isEqualTo(HttpStatus.CREATED.value());
    }
}
