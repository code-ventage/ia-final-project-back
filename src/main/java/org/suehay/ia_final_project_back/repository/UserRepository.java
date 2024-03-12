package org.suehay.ia_final_project_back.repository;

import org.suehay.ia_final_project_back.model.request.UserRequest;

import java.util.HashMap;
public interface UserRepository {
    HashMap<String, String> signUp(UserRequest userRequest);
    HashMap<String, String> index(UserRequest userRequest);

    HashMap<String, String> login(UserRequest userRequest);
}