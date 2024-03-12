package org.suehay.ia_final_project_back.repository;

import org.suehay.ia_final_project_back.model.request.UserScoreRequest;

import java.util.HashMap;

public interface UserScoreRepository {
    HashMap<String, String> signUp(UserScoreRequest userScoreRequest);
    HashMap<String, String> index();

    HashMap<String, String> login(UserScoreRequest userScoreRequest);
}