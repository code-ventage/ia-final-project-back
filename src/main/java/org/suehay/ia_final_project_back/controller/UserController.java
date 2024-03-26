package org.suehay.ia_final_project_back.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.suehay.ia_final_project_back.model.pythonReponse.GenericResponse;
import org.suehay.ia_final_project_back.model.pythonReponse.GenericScoreResponse;
import org.suehay.ia_final_project_back.model.request.UserRequest;
import org.suehay.ia_final_project_back.model.request.UserScoreRequest;
import org.suehay.ia_final_project_back.service.UserService;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    final UserService userService;

    @PostMapping("/signUp")
    public ResponseEntity<GenericResponse> signUp(@RequestBody UserRequest userRequest) {
        return ResponseEntity.ok(userService.signUp(userRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<GenericResponse> login(@RequestBody UserRequest userRequest) {
        return ResponseEntity.ok(userService.login(userRequest));
    }

    @GetMapping("/getAll")
    public ResponseEntity<GenericResponse> getAll() {
        return ResponseEntity.ok(userService.getAll());
    }

    @GetMapping("/score/index")
    public ResponseEntity<GenericScoreResponse> index() {
        var response = userService.index();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/score/store")
    public ResponseEntity<GenericScoreResponse> store(@RequestBody UserScoreRequest userRequest) {
        return ResponseEntity.ok(userService.save(userRequest));
    }
}