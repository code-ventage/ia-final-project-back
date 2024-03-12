package org.suehay.ia_final_project_back.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UserScoreResponse {
    @JsonProperty("username")
    final String userName;
    @JsonProperty("score")
    final String score;
}