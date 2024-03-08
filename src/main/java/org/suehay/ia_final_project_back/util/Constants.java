package org.suehay.ia_final_project_back.util;

import java.io.File;
import java.io.FileFilter;
import java.util.Comparator;

/**
 * La clase Constants proporciona constantes y objetos reutilizables relacionados con operaciones de archivos.
 *
 * @author Bikoru
 */
public class Constants {

    /**
     * Un comparador que impone el orden natural en los objetos comparables.
     */
    public static final Comparator NATURAL_ORDER = Comparator.naturalOrder();

    /**
     * Un filtro de archivos que acepta solo directorios.
     */
    public static FileFilter directories = File::isDirectory;

    /**
     * Un filtro de archivos que acepta solo archivos regulares.
     */
    public static FileFilter files = File::isFile;
}