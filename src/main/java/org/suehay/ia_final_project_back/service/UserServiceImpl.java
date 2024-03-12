package org.suehay.ia_final_project_back.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.suehay.ia_final_project_back.model.pythonReponse.GenericResponse;
import org.suehay.ia_final_project_back.model.pythonReponse.GenericScoreResponse;
import org.suehay.ia_final_project_back.model.request.UserRequest;
import org.suehay.ia_final_project_back.model.request.UserScoreRequest;
import org.suehay.ia_final_project_back.repository.UserRepository;
import org.suehay.ia_final_project_back.repository.UserScoreRepository;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    final UserRepository userRepository;
    final UserScoreRepository userScoreRepository;

    @Override
    public GenericResponse signUp(UserRequest userRequest) {
        return userRepository.signUp(userRequest);
    }

    @Override
    public GenericResponse login(UserRequest userRequest) {
        return userRepository.login(userRequest);
    }

    @Override
    public GenericResponse getAll() {
        return userRepository.getAll();
    }

    @Override
    public GenericScoreResponse save(UserScoreRequest userScoreRequest) {
        return userScoreRepository.save(userScoreRequest);
    }

    @Override
    public GenericScoreResponse index() {
        return userScoreRepository.index();
    }
}