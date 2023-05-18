package com.alpergayretoglu.movie_provider.controller;


import com.alpergayretoglu.movie_provider.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MockMvc mockMvc;

    /*
    // given/when/then format - BDD style
    @Test
    public void givenUsers_whenGetAllUsers_thenListOfUsers() throws Exception {
        // given - setup or precondition
        List<User> users =
                List.of(User.builder()
                        .name("Adam")
                        .surname("Smith")
                        .email("adam_smith@mail.com")
                        .password("123456")
                        .role(UserRole.GUEST)
                        .build());
        userRepository.saveAll(users);

        // when - action
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/api/v1/users"));

        // then - verify the output
        response.andExpect(MockMvcResultMatchers.status().isOk());
        response.andExpect(MockMvcResultMatchers.jsonPath("$.size()", CoreMatchers.is(users.size())));
    }
     */
}

