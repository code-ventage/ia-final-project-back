package org.suehay.ia_final_project_back.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.suehay.ia_final_project_back.util.FileLocator;
import org.suehay.ia_final_project_back.util.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;


@Repository
@RequiredArgsConstructor
public class ConsultRepositoryImpl implements ConsultRepository {

    final Utils utils;

    private @Value("${consult.query.prefix}") String digitQueryPrefix;
    private @Value("${consult.query.suffix}") String digitQuerySuffix;

    private @Value("${consult.query.lettersPrefix}") String letterQueryPrefix;
    private @Value("${consult.query.lettersSuffix}") String letterQuerySuffix;
    @Override
    public String makeDigitConsultToProlog(String nums) {
        String command =
                null;
        try {
            command = digitQueryPrefix
                    + nums
                    + digitQuerySuffix + " \""
                    + FileLocator.getPath("numero.pl") + "\"";
        } catch (URISyntaxException | IOException e) {
        }

        return getMakeGenericConsult(command);
    }

    private String getMakeGenericConsult(String command) {
        var processBuilder = new ProcessBuilder();

        processBuilder = utils.getProcessBuilder(processBuilder, command);

        try {
            var process = processBuilder.start();
            var reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            var response = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                response.append(line);
            }
            return response.substring(0, response.length() - 1).replace("'", "");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "error";
    }

    @Override
    public String makeLetterConsultToProlog(String letters) {
        String command = null;
        try {
            command = letterQueryPrefix
                    + letters
                    + letterQuerySuffix + " \""
                    + FileLocator.getPath("numerator.pl") + "\"";
        } catch (URISyntaxException | IOException e) {
        }

        return getMakeGenericConsult(command);
    }
}