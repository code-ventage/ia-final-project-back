package org.suehay.ia_final_project_back;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.suehay.ia_final_project_back.model.request.UserRequest;
import org.suehay.ia_final_project_back.repository.UserRepositoryImpl;

@SpringBootTest
class IaFinalProjectBackApplicationTests {
    @Autowired
    UserRepositoryImpl userRepository;

    @Test
    void contextLoads() {
        userRepository.login(new UserRequest("suehay", "1234"));
    }

}