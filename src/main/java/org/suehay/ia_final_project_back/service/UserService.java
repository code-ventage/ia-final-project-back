package org.suehay.ia_final_project_back.service;

import org.suehay.ia_final_project_back.model.pythonReponse.GenericResponse;
import org.suehay.ia_final_project_back.model.pythonReponse.GenericScoreResponse;
import org.suehay.ia_final_project_back.model.request.UserRequest;
import org.suehay.ia_final_project_back.model.request.UserScoreRequest;

public interface UserService {
    GenericResponse signUp(UserRequest userRequest);
    GenericResponse login(UserRequest userRequest);

    GenericResponse getAll();
    GenericScoreResponse save(UserScoreRequest userScoreRequest);
    GenericScoreResponse index();
}