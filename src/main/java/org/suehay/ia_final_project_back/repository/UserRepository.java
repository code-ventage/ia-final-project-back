package org.suehay.ia_final_project_back.repository;

import org.suehay.ia_final_project_back.model.pythonReponse.GenericResponse;
import org.suehay.ia_final_project_back.model.request.UserRequest;

public interface UserRepository {
    GenericResponse signUp(UserRequest userRequest);
    GenericResponse getAll();

    GenericResponse login(UserRequest userRequest);
}