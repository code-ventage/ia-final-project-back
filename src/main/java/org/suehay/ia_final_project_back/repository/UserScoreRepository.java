package org.suehay.ia_final_project_back.repository;

import org.suehay.ia_final_project_back.model.pythonReponse.GenericScoreResponse;
import org.suehay.ia_final_project_back.model.request.UserScoreRequest;

public interface UserScoreRepository {
    GenericScoreResponse save(UserScoreRequest userScoreRequest);
    GenericScoreResponse index();
}