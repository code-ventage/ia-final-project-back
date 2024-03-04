package org.suehay.ia_final_project_back.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.suehay.ia_final_project_back.model.request.ConsultRequest;
import org.suehay.ia_final_project_back.model.response.ConsultResponse;
import org.suehay.ia_final_project_back.repository.ConsultRepository;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class ConsultServiceImpl implements ConsultService {

    private final ConsultRepository consultRepository;

    @Override
    public ConsultResponse makeDigitConsult(ConsultRequest consultRequest) {
        return ConsultResponse.builder().hashResponse(makeDigitConsultToProlog(consultRequest.getConsult())).build();
    }

    public HashMap<String, String> makeDigitConsultToProlog(String nums) {
        var solution = proccessResponse(consultRepository.makeDigitConsultToProlog(nums));
        if (solution == null) return null;

        return solution;
    }

    private static HashMap<String, String> proccessResponse(String prologResponse) {
        if (prologResponse.equals("error")) return null;

        var solution = new HashMap<String, String>();


        var response = prologResponse.split("=");

        if (response.length > 1) {
            solution.put("N", response[1]);
        }
        return solution;
    }

    @Override
    public ConsultResponse makeLetterConsult(ConsultRequest consultRequest) {
        return ConsultResponse.builder().hashResponse(makeLetterConsultToProlog(consultRequest.getConsult())).build();
    }

    public HashMap<String, String> makeLetterConsultToProlog(String letters) {
        var solution = proccessResponse(consultRepository.makeLetterConsultToProlog(letters));
        if (solution == null) return null;

        return solution;

    }
}