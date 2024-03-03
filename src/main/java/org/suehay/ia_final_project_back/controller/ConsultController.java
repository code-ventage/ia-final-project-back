package org.suehay.ia_final_project_back.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.suehay.ia_final_project_back.model.request.ConsultRequest;
import org.suehay.ia_final_project_back.model.response.ConsultResponse;
import org.suehay.ia_final_project_back.service.ConsultService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/consult")
public class ConsultController {

    private final ConsultService consultService;

    @PostMapping
    public ResponseEntity<ConsultResponse> makeConsult(@RequestBody ConsultRequest consultRequest){
        return ResponseEntity.ok(consultService.makeConsult(consultRequest));
    }

}