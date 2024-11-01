package org.suehay.ia_final_project_back.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.suehay.ia_final_project_back.model.request.ConsultRequest;
import org.suehay.ia_final_project_back.model.response.ConsultResponse;
import org.suehay.ia_final_project_back.service.ConsultService;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequiredArgsConstructor
@RequestMapping("/consult")
public class ConsultController {

    private final ConsultService consultService;

    @PostMapping("/digit")
    public ResponseEntity<ConsultResponse> makeDigitConsult(@RequestBody ConsultRequest consultRequest) {
        return ResponseEntity.ok(consultService.makeDigitConsult(consultRequest));
    }

    @PostMapping("/letter")
    public ResponseEntity<ConsultResponse> makeLetterConsult(@RequestBody ConsultRequest consultRequest) {
        return ResponseEntity.ok(consultService.makeLetterConsult(consultRequest));
    }

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        StringBuilder response = new StringBuilder();
        var processBuilder = new ProcessBuilder();

        processBuilder.command("bash", "-c", "python3 \"/mnt/631e5359-39b1-4668-9456-b59d54cd5574/02 - " +
                "Projects/ia_final_project_back/src/main/resources/script.py\"");

        try {
            var process = processBuilder.start();

            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));
            writer.write("input data");
            writer.flush();
            writer.close();

            var reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                response.append(line);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok(response.toString());
    }

    @PostMapping()
    public ResponseEntity<Boolean> setMultipart(@RequestBody MultipartFile file) {
        throw new RuntimeException("Not implemented");
    }

    private static final String UPLOAD_DIR = "uploads/";


    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("File is empty");
        }

        try {
            // Define la ruta donde se guardará el archivo
            Path path = Paths.get(UPLOAD_DIR + file.getOriginalFilename());
            Files.createDirectories(path.getParent());
            Files.write(path, file.getBytes());

            return ResponseEntity.ok("File uploaded successfully: " + file.getOriginalFilename());
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("File upload failed");
        }
    }

}