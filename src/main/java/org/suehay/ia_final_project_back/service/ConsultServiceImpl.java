package org.suehay.ia_final_project_back.service;

import lombok.RequiredArgsConstructor;
import org.jpl7.Query;
import org.jpl7.Term;
import org.springframework.stereotype.Service;
import org.suehay.ia_final_project_back.model.request.ConsultRequest;
import org.suehay.ia_final_project_back.model.response.ConsultResponse;
import org.suehay.ia_final_project_back.repository.ConsultRepository;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ConsultServiceImpl implements ConsultService {

    private final ConsultRepository consultRepository;

    @Override
    public ConsultResponse makeConsult(ConsultRequest consultRequest) {

        var finalResult = new HashMap<String, String>();
        makeConsultToProlog(Arrays.stream(consultRequest.getConsult().split(" ")).toList())
                .values()
                .stream()
                .map(term -> toString())
                .toList()
                .forEach(value -> finalResult.put("N", value));

        return ConsultResponse.builder().hashResponse(finalResult).build();
    }

    public Map<String, Term> makeConsultToProlog(List<String> nums) {

        StringBuilder consult = new StringBuilder("numero(N,[");

        nums.forEach(value -> consult.append(value).append(","));

        var consultResult = new StringBuilder(consult.substring(0, consult.length() - 1));

        consultResult.append("],[]).");

        System.out.println("executing query : " + consultResult);

        Query query = consultRepository.getQuery(consultResult);

        Map<String, Term> solution = query.getSolution();
        if (solution == null) return null;

        System.out.println("Prolog response : " + solution);

        return solution;
    }
}