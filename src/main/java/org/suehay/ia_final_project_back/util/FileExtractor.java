package org.suehay.ia_final_project_back.util;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * La clase FileExtractor proporciona diferentes métodos para cargar información desde archivos.
 * <p>
 * Los métodos incluyen la búsqueda de una línea que contenga una cadena específica en un archivo,
 * <p>
 * la obtención de una lista de archivos y directorios en una carpeta, la carga de un archivo en un
 * <p>
 * String o en un array de Strings, la carga de un archivo en un array de bytes, la carga de un objeto
 * <p>
 * desde un archivo y más.
 *
 * @author Bikoru
 */
@Slf4j
public class FileExtractor {

    /**
     * Devuelve la línea de aparición de una cadena especificada en un archivo de la dirección especificada.
     *
     * @param path   La dirección del archivo.
     * @param search La cadena a buscar.
     * @return El número de línea en la que se encontró la cadena, o -1 si no se encontró.
     * @throws IOException das
     */
    public static int containerLine(String path, String search) throws IOException {
        List<String> lines = Files.readAllLines(Path.of(path));
        return lines.stream().filter(s -> s.contains(search)).findFirst().map(lines::indexOf).orElse(-1);
    }

    /**
     * Devuelve una lista de archivos del tipo carpeta en la dirección especificada.
     *
     * @param path La dirección de la carpeta.
     * @return Una lista de archivos del tipo carpeta.
     */
    public static List<File> directoryFileList(String path) {
        return Arrays.stream(Objects.requireNonNull(new File(path).listFiles(Constants.directories))).toList();
    }

    /**
     * Devuelve una lista de archivos del tipo archivo en la dirección especificada.
     *
     * @param path La dirección de la carpeta.
     * @return Una lista de archivos del tipo archivo.
     * @throws IOException dsa
     */
    public static List<File> fileFileList(String path) throws IOException {
        return Arrays.stream(Objects.requireNonNull(new File(path).listFiles(Constants.files))).toList();
    }

    /**
     * Devuelve una lista de todos los archivos en la dirección especificada.
     *
     * @param path La dirección de la carpeta.
     * @return Una lista de todos los archivos.
     */
    public static List<File> directoryAndFilesFileList(String path) throws IOException {
        return Arrays.stream(Objects.requireNonNull(new File(path).listFiles())).toList();
    }

    /**
     * Devuelve una lista de direcciones de las carpetas en la dirección especificada.
     *
     * @param path La dirección de la carpeta.
     * @return Una lista de direcciones de las carpetas.
     * @throws IOException ads
     */
    public static ArrayList<String> directoryDirectoryList(String path) throws IOException {
        return directoryFileList(path).stream().map(File::getPath).collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Devuelve una lista de direcciones de los archivos en la dirección especificada.
     *
     * @param path La dirección de la carpeta.
     * @return Una lista de direcciones de los archivos.
     * @throws IOException asd
     */
    public static ArrayList<String> directoryAList(String path) throws IOException {
        return fileFileList(path).stream().map(File::getPath).collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Carga el contenido de un archivo de la dirección especificada en un solo String.
     *
     * @param path La dirección del archivo.
     * @return El contenido del archivo en un String.
     * @throws IOException sda
     */
    public static String loadFileAsAString(String path) throws IOException {
        var content = new StringBuilder();
        var reader = new BufferedReader(new FileReader(path));
        return getString(content, reader);
    }

    /**
     * Carga el contenido de un archivo de la dirección especificada en un solo String utilizando un InputStream.
     *
     * @param path La dirección del archivo.
     * @return El contenido del archivo en un String.
     * @throws IOException sad
     */
    public static String loadFileAsAStringInputStream(String path) throws IOException {
        var content = new StringBuilder();
        var reader = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
        return getString(content, reader);
    }

    /**
     * Método auxiliar para cargar el contenido de un archivo en un String.
     *
     * @param content El StringBuilder donde se almacenará el contenido del archivo.
     * @param reader  El BufferedReader que se utilizará para leer el archivo.
     * @return El contenido del archivo en un String.
     * @throws IOException asd
     */
    private static String getString(StringBuilder content, BufferedReader reader) throws IOException {
        String line;
        int i = 1;
        while ((line = reader.readLine()) != null) if (!line.equals("\n")) {
            if (i != 0) {
                content.append(line);
                i--;
            } else {
                content.append("\n").append(line);
            }
        }
        reader.close();
        return content.toString();
    }

    /**
     * Carga el contenido de un archivo de la dirección especificada en un array de Strings.
     *
     * @param path La dirección del archivo.
     * @return El contenido del archivo en un array de Strings.
     * @throws IOException sad
     */
    public static List<String> loadFileAsStringArr(String path) throws IOException {
        return Files.readAllLines(Path.of(path));
    }

    /**
     * Carga el contenido de un archivo de la dirección especificada en un array de bytes.
     *
     * @param path La dirección del archivo.
     * @return El contenido del archivo en un array de bytes.
     * @throws IOException      asd
     * @throws OutOfMemoryError asd
     */
    public static byte[] loadFileAsByteArr(String path) throws IOException, OutOfMemoryError {
        return loadBytesSecuence(path, 0, new File(path).length() + 1);
    }

    /**
     * Carga el primer byte de un archivo de la dirección especificada.
     *
     * @param path La dirección del archivo.
     * @return El primer byte del archivo.
     * @throws IOException dsa
     */
    public static byte loadByte(String path) throws IOException {
        return Files.readAllBytes(Path.of(path))[0];
    }

    /**
     * Carga una secuencia de bytes de un archivo de la dirección especificada.
     *
     * @param path       La dirección del archivo.
     * @param beginIndex El índice de inicio de la secuencia de bytes.
     * @param endIndex   El índice final de la secuencia de bytes.
     * @return La secuencia de bytes del archivo.
     * @throws IOException      dsa
     * @throws OutOfMemoryError a s
     */
    public static byte[] loadBytesSecuence(String path, long beginIndex, long endIndex) throws IOException, OutOfMemoryError {
        if (beginIndex < 0 || endIndex < 0) {
            throw new IOException("Los índices no pueden ser negativos");
        }
        if (beginIndex > endIndex) {
            throw new IOException("El índice de inicio no puede ser mayor que el índice final");
        }
        if (endIndex - beginIndex > Integer.MAX_VALUE) {
            throw new OutOfMemoryError("El archivo es demasiado grande para leer en un array");
        }
        byte[] arr = new byte[(int) (endIndex - beginIndex)];
        try (RandomAccessFile file = new RandomAccessFile(path, "r")) {
            file.seek(beginIndex);
            file.readFully(arr);
        }
        return arr;
    }

    /**
     * Carga un objeto desde un archivo de la dirección especificada.
     *
     * @param path La dirección del archivo.
     * @return El objeto cargado desde el archivo.
     * @throws IOException            sad
     * @throws ClassNotFoundException asd
     */
    public static Object loadObject(String path) throws IOException, ClassNotFoundException {
        Object object;
        var file = new FileInputStream(path);
        var in = new ObjectInputStream(file);
        object = in.readObject();
        in.close();
        file.close();
        return object;
    }

    /**
     * Guarda un objeto en un archivo de la dirección especificada.
     *
     * @param path   La dirección del archivo.
     * @param object El objeto a guardar.
     * @throws IOException asd
     */
    public static void saveObject(String path, Object object) throws IOException {
        var file = new FileOutputStream(path);
        var out = new ObjectOutputStream(file);
        out.writeObject(object);
        out.close();
        file.close();
    }
}