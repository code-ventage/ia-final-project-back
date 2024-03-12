package org.suehay.ia_final_project_back;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.suehay.ia_final_project_back.model.request.UserRequest;
import org.suehay.ia_final_project_back.repository.UserRepositoryImpl;

@SpringBootTest
class IaFinalProjectBackApplicationTests {

    @Test
    void contextLoads() {
        var pepino = new UserRepositoryImpl();
        /*pepino.signUp(new UserRequest("suehay", "1234"));
        pepino.index(new UserRequest("suehay", "1234"));*/
        pepino.login(new UserRequest("suehay", "1234"));
    }

}