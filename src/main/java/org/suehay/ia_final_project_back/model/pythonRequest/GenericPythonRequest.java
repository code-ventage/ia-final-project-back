package org.suehay.ia_final_project_back.model.pythonRequest;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.HashMap;
@Data
@Builder
@AllArgsConstructor
public class GenericPythonRequest {
    @JsonProperty(value = "version")
    final Integer version;
    @JsonProperty(value = "method")
    final String method;
    @JsonProperty(value = "service")
    final String service;
    @JsonProperty(value = "data")
    final HashMap<String, String> data;
}