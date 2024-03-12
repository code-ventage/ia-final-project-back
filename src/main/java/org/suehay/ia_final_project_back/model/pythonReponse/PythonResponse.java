package org.suehay.ia_final_project_back.model.pythonReponse;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.suehay.ia_final_project_back.model.response.UserResponse;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PythonResponse {
    @JsonProperty("status")
    String status;
    @JsonProperty("message")
    String message;
    @JsonProperty("data")
    List<UserResponse> data;
}