package com.alpergayretoglu.movie_provider.service;

import com.alpergayretoglu.movie_provider.model.entity.User;
import com.alpergayretoglu.movie_provider.repository.UserRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    /*
    @Test
    @Order(1)
    public void addUserTest() {
        UserCreateRequest req = UserCreateRequest.builder()
                .name("NAME")
                .surname("SURNAME")
                .email("EMAIL@EMAIL.COM")
                .password("PASSWORD")
                .role(UserRole.GUEST)
                .build();
        User user = userService.addUser(req);

        assertThat(userRepository.existsByEmail("EMAIL@EMAIL.COM")).isEqualTo(true);
    }
*/

    /*
    @Test
    @Order(2)
    public void updateUserTest() { // TODO: Find out why this is failing
        assertThat(userRepository.findByEmail("EMAIL@EMAIL.COM").isPresent()).isTrue();

        User user = userRepository.findByEmail("EMAIL@EMAIL.COM").get();

        UserUpdateRequest req = UserUpdateRequest.builder()
                .name("NAME2")
                .surname("SURNAME")
                .email("EMAIL@EMAIL.COM")
                .password("PASSWORD")
                .role(UserRole.GUEST)
                .build();

        userService.updateUser(user.getId(), req);

        assertThat(user.getName()).isEqualTo("NAME2");
    }
    */


    @Test
    @Order(3)
    public void deleteUser() {
        assertThat(userRepository.findByEmail("EMAIL@EMAIL.COM").isPresent()).isTrue();

        User user = userRepository.findByEmail("EMAIL@EMAIL.COM").get();

        userService.deleteUser(user.getId());

        assertThat(userRepository.findByEmail("EMAIL@EMAIL.COM").isPresent()).isFalse();
    }
}










