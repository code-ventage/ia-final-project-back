package org.suehay.ia_final_project_back.util;

import org.springframework.stereotype.Component;

@Component
public class Utils {

    public ProcessBuilder getProcessBuilder(ProcessBuilder processBuilder, String command) {
        if (System.getProperty("os.name").toLowerCase().contains("win")) {
            processBuilder.command("cmd.exe", "/c", command);
        } else {
            processBuilder.command("bash", "-c", command);
        }
        return processBuilder;
    }

}