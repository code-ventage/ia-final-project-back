# IA Final Project Backend (Spring boot)

## Descripion

### A project to connect Java with SWI-Pl by command line.

This project consist in a REST API that connects to SWI-Pl by command line and execute the predicates in the `numero.pl` and `numerator.pl` files.

Main objective: create an app that the students can use to translate from number in digits notation to number in words notation and vice versa.

## How to run

Open the terminal and go to the project folder. Then, run the following command:

```bash
java -jar NumberTranslator-1.0.0.jar
```

## How to use

This project only works with the following requests:

- From digits: 

```
###
POST http://localhost:34545/consult/letter
Content-Type: application/json

{
  "consult": "1002" // or any other number between 0 and 999 999 999
}
```

- From words:

```
###
POST http://0.0.0.0:34545/consult/digit
Content-Type: application/json

{
  "consult": "mil dos" // or any other number between 0 and 999 999 999 in words notation separated by spaces
}
```

## How to test

Open POSTMAN and use the requests above to test the project.

In Intellij, you can create a .http request file in `Scratches and Consoles/Scratches/your_file.http` and add de comands above to test the project.

Later, you can run the tests by clicking in the green arrow next to the request.

## How its works

The project uses the `ProcessBuilder` class to execute the SWI-Pl command line. The command is executed in the `ConsultRepositoryImpl` class.

The command is executed in the following way:

```java
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
```

As you can see, the project runs teh comand in the terminal and then reads the output of the command to return the result.

The selection of the terminal to run the command is made by the `System.getProperty("os.name").toLowerCase().contains("win")` condition.
Making this project compatible with Windows and Unix systems.

## Requirements

- Java 11 or higher
- SWI-Pl installed on your machine
- Maven

## Authors

 - [Victor Manuel Palmero Valdes](https://github.com/palmerovicdev)
 - [José Lázaro Díaz Estive](https://github.com/jldestive)
 - [Marcos Sebastian Portales Ramos](https://github.com/marcosportales)