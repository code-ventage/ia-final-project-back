package org.suehay.ia_final_project_back.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;
import org.suehay.ia_final_project_back.model.pythonRequest.GenericPythonRequest;
import org.suehay.ia_final_project_back.model.request.UserRequest;
import org.suehay.ia_final_project_back.util.FileLocator;

import java.io.*;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Objects;

@Repository
public class UserRepositoryImpl implements UserRepository {
    @Override
    public HashMap<String, String> signUp(UserRequest userRequest) {
        var response = new HashMap<String, String>();
        writeRequest(getGenericRequest(userRequest, "store"));
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
    public HashMap<String, String> login(UserRequest userRequest) {
        var response = new HashMap<String, String>();
        writeRequest(getGenericRequest(userRequest, "login"));
        makePythonConsult();

        // todo ver respuesta
        return null;
    }

    private static GenericPythonRequest getGenericRequest(UserRequest userRequest, String method) {
        return new GenericPythonRequest(
                0,
                method,
                "userService",
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

    public String makePythonConsult() {
        String command = null;
        try {
            command = "python3 " + "\"" + FileLocator.getPath("python-final" + File.separator + "app.py") + "\"";
        } catch (URISyntaxException | IOException e) {
        }

        return getMakeGenericConsult(command);
    }

    private String getMakeGenericConsult(String command) {
        var processBuilder = new ProcessBuilder();

        if (System.getProperty("os.name").toLowerCase().contains("win")) {
            processBuilder.command("cmd.exe", "/c", command);
        } else {
            processBuilder.command("bash", "-c", command);
        }

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

        return "pepino";
    }

}