package org.suehay.ia_final_project_back;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.suehay.ia_final_project_back.repository.ConsultRepository;
import org.suehay.ia_final_project_back.repository.UserRepository;
import org.suehay.ia_final_project_back.repository.UserScoreRepository;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RequiredArgsConstructor
class IaFinalProjectBackApplicationTests {
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserScoreRepository userScoreRepository;

    @Autowired
    ConsultRepository consultRepository;

    @Test
    void consultRepositoryDigit() {
        // ---------- From letters -----------------
        assertThat(consultRepository.makeDigitConsultToProlog("cero")).isEqualTo("N = 0");
        assertThat(consultRepository.makeDigitConsultToProlog("uno")).isEqualTo("N = 1");
        assertThat(consultRepository.makeDigitConsultToProlog("mil doscientos treinta y cuatro")).isEqualTo("N = 1234");
        assertThat(consultRepository.makeDigitConsultToProlog("doscientos un mil trescientos veinticinco")).isEqualTo("N = 201325");
        assertThat(consultRepository.makeDigitConsultToProlog("ciento veintiun millones trescientos un mil doscientos")).isEqualTo("N = 121301200");
        assertThat(consultRepository.makeDigitConsultToProlog("ciento un millones trescientos un mil doscientos")).isEqualTo("N = 101301200");
    }

    @Test
    void consultRepositoryLetter(){
        // ---------- From digits -----------------
        assertThat(consultRepository.makeLetterConsultToProlog("0")).isEqualTo("N = cero");
        assertThat(consultRepository.makeLetterConsultToProlog("1")).isEqualTo("N = uno");
        assertThat(consultRepository.makeLetterConsultToProlog("1234")).isEqualTo("N = mil doscientos treinta y cuatro");
        assertThat(consultRepository.makeLetterConsultToProlog("201325")).isEqualTo("N = doscientos un mil trescientos veinticinco");
        assertThat(consultRepository.makeLetterConsultToProlog("121301200")).isEqualTo("N = ciento veintiun millones trescientos un mil doscientos");
        assertThat(consultRepository.makeLetterConsultToProlog("101301200")).isEqualTo("N = ciento un millones trescientos un mil doscientos");
    }

}