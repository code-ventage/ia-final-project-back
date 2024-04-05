package org.suehay.ia_final_project_back.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.suehay.ia_final_project_back.model.pythonReponse.GenericScoreResponse;
import org.suehay.ia_final_project_back.model.pythonRequest.GenericPythonRequest;
import org.suehay.ia_final_project_back.model.request.UserScoreRequest;
import org.suehay.ia_final_project_back.util.FileLocator;
import org.suehay.ia_final_project_back.util.Utils;

import java.io.*;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Objects;

@Repository
@RequiredArgsConstructor
public class UserScoreRepositoryImpl implements UserScoreRepository {
    final Utils utils;
    @Override
    public GenericScoreResponse save(UserScoreRequest userScoreRequest) {
        writeRequest(getGenericRequest(userScoreRequest, "store"));
        makePythonConsult();

        GenericScoreResponse response;
        try {
            response = new ObjectMapper().readValue(new File(FileLocator.getPath("response.json")), GenericScoreResponse.class);
        } catch (URISyntaxException | IOException e) {
            throw new RuntimeException(e);
        }
        return response;
    }

    @Override
    public GenericScoreResponse index() {
        writeRequest(getGenericRequest(null, "index"));
        makePythonConsult();
        GenericScoreResponse response;
        try {
            response = new ObjectMapper().readValue(new File(FileLocator.getPath("response.json")), GenericScoreResponse.class);
        } catch (URISyntaxException | IOException e) {
            throw new RuntimeException(e);
        }
        return response;
    }

    private static GenericPythonRequest getGenericRequest(UserScoreRequest userScoreRequest, String method) {
        return new GenericPythonRequest(
                0,
                method,
                "UserScoreService",
                !Objects.isNull(userScoreRequest) ? new HashMap<>() {{
                    put("username", userScoreRequest.getUserName());
                    put("score", userScoreRequest.getScore());
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
        } catch (URISyntaxException | IOException ignored) {
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