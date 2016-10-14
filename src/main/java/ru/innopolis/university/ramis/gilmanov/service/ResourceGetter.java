package ru.innopolis.university.ramis.gilmanov.service;

import java.util.List;
import java.util.Scanner;

/**
 * Created by innopolis on 13.10.16.
 */
public interface ResourceGetter {

    /**
     * проверка на файл
     * @param arg
     * @return
     */
    boolean isFile(String arg);

    /**
     * проверка на URL
     * @param arg
     * @return
     */
    boolean isURL(String arg);

    /**
     * выводит имя ресурса
     * @return
     */
    String getResourceName();

    /**
     *
     * @param scanner
     * @return список содержимого ресурса
     */
    List<Long> getResourceContent(Scanner scanner);
}
