package org.suehay.ia_final_project_back.repository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.suehay.ia_final_project_back.util.FileLocator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;


@Repository
public class ConsultRepositoryImpl implements ConsultRepository {

    private @Value("${consult.query.suffix}") String querySuffix;
    private @Value("${consult.query.prefix}") String queryPrefix;

    public String makeConsultToProlog(String nums) {
        String command =
                null;
        try {
            command = queryPrefix
                    + nums
                    + querySuffix + " \""
                    + FileLocator.getPath("numero.pl") + "\"";
        } catch (URISyntaxException | IOException e) {
        }

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
            StringBuilder response = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                response.append(line);
            }
            return response.substring(0, response.length() - 1).replace(" ", "");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "error";
    }
}