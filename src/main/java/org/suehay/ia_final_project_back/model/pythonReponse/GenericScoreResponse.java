package org.suehay.ia_final_project_back.model.pythonReponse;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class GenericScoreResponse {
    @JsonProperty("version")
    String version;
    @JsonProperty("response")
    PythonScoreResponse response;
}