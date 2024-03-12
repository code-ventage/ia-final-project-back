package org.suehay.ia_final_project_back.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
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
    public HashMap<String, String> signUp(UserScoreRequest userScoreRequest) {
        var response = new HashMap<String, String>();
        writeRequest(getGenericRequest(userScoreRequest, "store"));
        makePythonConsult();

        // todo leer la resinga respuesta
        return null;
    }

    @Override
    public HashMap<String, String> index() {
        var response = new HashMap<String, String>();
        writeRequest(getGenericRequest(null, "index"));
        makePythonConsult();

        // todo ver respuesta
        return null;
    }

    @Override
    public HashMap<String, String> login(UserScoreRequest userScoreRequest) {
        var response = new HashMap<String, String>();
        writeRequest(getGenericRequest(userScoreRequest, "login"));
        makePythonConsult();

        // todo ver respuesta
        return null;
    }

    private static GenericPythonRequest getGenericRequest(UserScoreRequest userScoreRequest, String method) {
        return new GenericPythonRequest(
                0,
                method,
                "userScoreService",
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
        } catch (URISyntaxException | IOException e) {
        }

        getMakeGenericConsult(command);
    }

    private void getMakeGenericConsult(String command) {
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