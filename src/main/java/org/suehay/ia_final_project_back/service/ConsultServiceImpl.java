package org.suehay.ia_final_project_back.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.suehay.ia_final_project_back.model.request.ConsultRequest;
import org.suehay.ia_final_project_back.model.response.ConsultResponse;
import org.suehay.ia_final_project_back.repository.ConsultRepository;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ConsultServiceImpl implements ConsultService {

    private final ConsultRepository consultRepository;

    @Override
    public ConsultResponse makeConsult(ConsultRequest consultRequest) {
        return ConsultResponse.builder().hashResponse(makeConsultToProlog(Arrays.stream(consultRequest.getConsult().split(" ")).toList())).build();
    }

    public HashMap<String, String> makeConsultToProlog(List<String> nums) {

        var numbers = new StringBuilder();

        nums.forEach(value -> numbers.append(value).append(","));

        var prologResponse = consultRepository.makeConsultToProlog(numbers.substring(0, numbers.length() - 1));

        if (prologResponse.equals("error")) return null;

        var solution = new HashMap<String, String>();


        var response = prologResponse.split("=");

        if (response.length > 1) {
            var key = response[0];
            var value = response[1];
            solution.put(key, value);
        }

        return solution;
    }
}