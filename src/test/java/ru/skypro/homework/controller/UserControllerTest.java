package ru.skypro.homework.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.minidev.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.mock.web.MockPart;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import ru.skypro.homework.model.ImageEntity;
import ru.skypro.homework.model.UserEntity;
import ru.skypro.homework.model.special.Role;
import ru.skypro.homework.repository.ImageRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.security.UserDetailsManager;
import ru.skypro.homework.service.ImageService;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.authentication;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private UserDetailsManager userDetailsManager;

    private Authentication authentication;
    private final UserEntity users = new UserEntity();
    private final ImageEntity image = new ImageEntity();

    @BeforeEach
    void setUp() {
        users.setRole(Role.USER);
        users.setFirstName("Тестировщик");
        users.setLastName("Тест");
        users.setPhone("+7(995)5557788");
        users.setUsername("test@gmail.com");
        users.setPassword(encoder.encode("hello123"));
        userRepository.save(users);

        UserDetails userDetails = userDetailsManager.loadUserByUsername(users.getUsername());

        authentication = new UsernamePasswordAuthenticationToken(userDetails.getUsername(),
                userDetails.getPassword(),
                userDetails.getAuthorities());
    }

    @AfterEach
    void deleteObjects() {
        userRepository.delete(users);
        imageRepository.delete(image);
    }

    @Test
    void testUpdateUserPassword() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("currentPassword", "hello123");
        jsonObject.put("newPassword", "hello321");

        mockMvc.perform(post("/users/set_password")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonObject.toString())
                        .with(authentication(authentication)))
                .andExpect(status().isOk());
    }

    @Test
    void testGetInfoUser() throws Exception {
        mockMvc.perform(get("/users/me")
                        .with(authentication(authentication)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName")
                        .value(users.getFirstName()));
    }

    @Test
    void testUpdateInfoUser() throws Exception {
        String newFirstName = "Test1";
        String newLastName = "Test2";
        users.setFirstName(newFirstName);
        users.setLastName(newLastName);

        mockMvc.perform(patch("/users/me")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(users))
                        .with(authentication(authentication)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName")
                        .value(newFirstName))
                .andExpect(jsonPath("$.lastName")
                        .value(newLastName));
    }
}
