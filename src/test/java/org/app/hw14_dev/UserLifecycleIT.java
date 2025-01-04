package org.app.hw14_dev;

import org.app.hw14_dev.model.dto.request.UserRequest;
import org.app.hw14_dev.model.dto.response.UserResponse;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

class UserLifecycleIT extends BaseIT {

    @Test
    void testRegisterSuccessfulUser() {
        String username = "denys";
        String email = "denys@example.com";
        String password = "password";
        UserRequest userCreateRequest = UserRequest.builder()
                .username(username)
                .email(email)
                .password(password)
                .build();

        ResponseEntity<String> signupResponse =
                restTemplate.postForEntity(SERVER_BASE_URL + port + API_BASE_URL + "auth/signup",
                        userCreateRequest, String.class);

        assertThat(signupResponse.getStatusCode().value()).isEqualTo(HttpStatus.CREATED.value());

        UserRequest userLoginRequest = UserRequest.builder()
                .username(username)
                .email(email)
                .password(password)
                .build();
        ResponseEntity<String> loginResponse =
                restTemplate.postForEntity(SERVER_BASE_URL + port + API_BASE_URL + "auth/signin",
                        userLoginRequest, String.class);

        assertThat(loginResponse.getStatusCode().value()).isEqualTo(HttpStatus.OK.value());
        assertThat(loginResponse.getBody()).isNotNull();

        String jwt = loginResponse.getBody();

        HttpHeaders authHeaders = new HttpHeaders();
        authHeaders.setBearerAuth(jwt);

//        HttpEntity<Void> objectHttpEntity = new HttpEntity<>(authHeaders);
//        String customerId = "1";
//        UserResponse customerResponse = restTemplate.exchange(SERVER_BASE_URL + port + API_BASE_URL + "/customers/" + customerId,
//                        HttpMethod.GET, objectHttpEntity, UserResponse.class)
//                .getBody();
//
//        assertThat(customerResponse).isNotNull();
//        AssertionsForClassTypes.assertThat(customerResponse)
//                .usingRecursiveComparison()
//               // .ignoringFields("email", "password")
//                .isEqualTo(userCreateRequest);
    }
}
