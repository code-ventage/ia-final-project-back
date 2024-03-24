package org.suehay.ia_final_project_back.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.suehay.ia_final_project_back.model.pythonReponse.GenericResponse;
import org.suehay.ia_final_project_back.model.pythonReponse.PythonResponse;
import org.suehay.ia_final_project_back.model.pythonRequest.GenericPythonRequest;
import org.suehay.ia_final_project_back.model.request.UserRequest;
import org.suehay.ia_final_project_back.model.response.UserResponse;
import org.suehay.ia_final_project_back.util.FileLocator;
import org.suehay.ia_final_project_back.util.Utils;

import java.io.*;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {
    final Utils utils;

    private @Value("${application.debbug}") Boolean isDebbuging;

    @Override
    public GenericResponse signUp(UserRequest userRequest) {
        writeRequest(getGenericRequest(userRequest, "store"));
        makePythonConsult();
        if (isDebbuging)
            return GenericResponse.builder().response(
                                          PythonResponse.builder()
                                                        .status("200")
                                                        .data(List.of(UserResponse.builder()
                                                                                  .userName("Victor")
                                                                                  .password("victor")
                                                                                  .build()))
                                                        .build())
                                  .build();

        GenericResponse response;
        try {
            response = new ObjectMapper().readValue(new File(FileLocator.getPath("response.json")), GenericResponse.class);
        } catch (URISyntaxException | IOException e) {
            throw new RuntimeException(e);
        }
        return response;
    }

    @Override
    public GenericResponse getAll() {
        writeRequest(getGenericRequest(null, "index"));
        makePythonConsult();
        if (isDebbuging)
            return GenericResponse.builder().response(
                                                         PythonResponse.builder()
                                                                       .status("200")
                                                                       .data(List.of(UserResponse.builder()
                                                                                                 .userName("Victor")
                                                                                                 .password("victor")
                                                                                                 .build()))
                                                                       .build())
                                         .build();
        GenericResponse response;
        try {
            response = new ObjectMapper().readValue(new File(FileLocator.getPath("response.json")), GenericResponse.class);
        } catch (URISyntaxException | IOException e) {
            throw new RuntimeException(e);
        }
        return response;
    }

    @Override
    public GenericResponse login(UserRequest userRequest) {
        writeRequest(getGenericRequest(userRequest, "login"));
        makePythonConsult();
        if (isDebbuging)
            return GenericResponse.builder().response(
                                          PythonResponse.builder()
                                                        .status("200")
                                                        .data(List.of(UserResponse.builder()
                                                                                  .userName("Victor")
                                                                                  .password("victor")
                                                                                  .build()))
                                                        .build())
                                  .build();

        GenericResponse response;
        try {
            response = new ObjectMapper().readValue(new File(FileLocator.getPath("response.json")), GenericResponse.class);
        } catch (URISyntaxException | IOException e) {
            throw new RuntimeException(e);
        }
        return response;
    }

    private static GenericPythonRequest getGenericRequest(UserRequest userRequest, String method) {
        return new GenericPythonRequest(
                0,
                method,
                "UserService",
                !Objects.isNull(userRequest) ? new HashMap<>() {{
                    put("username", userRequest.getUserName());
                    put("password", userRequest.getPassword().hashCode() + "");
                }} : null
        );
    }


    private static void writeRequest(GenericPythonRequest genericRequest) {
        try (var bufferedWriter = new BufferedWriter(new FileWriter(FileLocator.getPath("request.json")))) {
            bufferedWriter.write(new ObjectMapper().writeValueAsString(genericRequest));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public void makePythonConsult() {
        String command = null;
        try {
            command = "python3 " + "\"" + FileLocator.getPath("python-final" + File.separator + "app.py") + "\"";
        } catch (URISyntaxException | IOException e) {
        }

        makeGenericConsult(command);
    }

    private void makeGenericConsult(String command) {
        var processBuilder = new ProcessBuilder();

        processBuilder = utils.getProcessBuilder(processBuilder, command);

        try {
            var process = processBuilder.start();
            var reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}