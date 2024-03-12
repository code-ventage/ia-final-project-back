package org.suehay.ia_final_project_back.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UserResponse {
    @JsonProperty(value = "username")
    final String userName;
    @JsonProperty(value = "password")
    final String password;
}