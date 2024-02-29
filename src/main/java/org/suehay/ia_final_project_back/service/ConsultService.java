package org.suehay.ia_final_project_back.service;

import org.suehay.ia_final_project_back.model.request.ConsultRequest;
import org.suehay.ia_final_project_back.model.response.ConsultResponse;

public interface ConsultService {
    ConsultResponse makeConsult(ConsultRequest consultRequest);
}