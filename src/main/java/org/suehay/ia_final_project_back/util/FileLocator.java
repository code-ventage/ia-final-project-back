package org.suehay.ia_final_project_back.util;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

public class FileLocator {
    public FileLocator() {
    }

    public static String getPath(String filename) throws URISyntaxException, IOException {
        String path_separator = System.getProperty("file.separator");
        File auxiliar = new File(FileLocator.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath());
        String directoryPath;
        if (auxiliar.isDirectory()) {
            auxiliar = new File(auxiliar.getParentFile().getParentFile().getPath());
            String var10000 = auxiliar.getCanonicalPath();
            directoryPath = var10000 + path_separator;
        } else {
            String JarURL = auxiliar.getCanonicalPath();
            directoryPath = JarURL.substring(0, JarURL.lastIndexOf(path_separator) + 1);
        }

        System.out.println(directoryPath + filename);
        return directoryPath + filename;
    }

    public static String locateDirectory(String directoryName) throws URISyntaxException, IOException {
        String containerWrongPath = getPath("");
        String containerFinalPath = containerWrongPath.substring(0, containerWrongPath.lastIndexOf(File.separator));
        return getRecursivePath(containerFinalPath, directoryName);
    }

    private static String getRecursivePath(String containerFinalPath, String directoryName) {
        File[] directories = (new File(containerFinalPath)).listFiles(File::isDirectory);
        if (directories != null) {
            File[] var3 = directories;
            int var4 = directories.length;

            for(int var5 = 0; var5 < var4; ++var5) {
                File directory = var3[var5];
                if (directory.getName().equals(directoryName)) {
                    return directory.getAbsolutePath();
                }

                String recursivePath = getRecursivePath(directory.getAbsolutePath(), directoryName);
                if (recursivePath != null) {
                    return recursivePath;
                }
            }
        }

        return null;
    }
}