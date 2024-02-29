package org.suehay.ia_final_project_back.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;
import java.util.Map;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConsultResponse implements Serializable {
    @JsonProperty("hash_response")
    private Map<String, String> hashResponse;
}