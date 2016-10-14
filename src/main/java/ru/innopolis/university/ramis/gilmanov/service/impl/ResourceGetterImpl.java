package ru.innopolis.university.ramis.gilmanov.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.innopolis.university.ramis.gilmanov.exeptions.NotNumberException;
import ru.innopolis.university.ramis.gilmanov.service.ResourceGetter;
import ru.innopolis.university.ramis.gilmanov.service.Validator;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by innopolis on 13.10.16.
 */
public class ResourceGetterImpl implements ResourceGetter {

    private static Logger logger = LoggerFactory.getLogger(ResourceGetterImpl.class);

    private String resourceName;
    private Validator validator = new ValidatorImpl();

    public ResourceGetterImpl(String resourceName) {
        this.resourceName = resourceName;
    }

    public ResourceGetterImpl() {
    }

    /**
     *
     * @return имя ресурса
     */
    @Override
    public String getResourceName() {
        return resourceName;
    }

    /**
     * проверка на файл
     * @param arg имя файла или путь к нему с именем
     * @return boolean
     */
    @Override
    public boolean isFile(String arg) {
        boolean result = false;
        File file = new File(arg);
        if (file.isFile()){
            result = true;
            logger.debug("Is File : " + arg);
        }
        return result;
    }

    /**
     * проверка на URL
     * @param arg имя URL
     * @return boolean
     */
    @Override
    public boolean isURL(String arg) {
        boolean result = false;
        try {
            URL url = new URL(arg);
            result = true;
            logger.debug("Is URL : " + arg);
        } catch (MalformedURLException e) {
            result = false;
        }
        return result;
    }

    /**
     *
     * @param scanner принимаем сканнер
     * @return список четных не отрицательных чисел
     */
    @Override
    public List<Long> getResourceContent(Scanner scanner) {
        List<Long> longList = new ArrayList<>();
        Long number = null;
        while (scanner.hasNext()) {
            try {
                number = Long.parseLong(scanner.next());
                if (validator.isAnEvenPositive(number)) {
                    longList.add(number);
                    logger.debug("Add number : " + number);
                }
            } catch (NumberFormatException e) {
                if (number != null) {
                    logger.error("Error in " + getResourceName() + " : " + number.toString() + " - is not a number");
                    new NotNumberException("Not number", number.toString(), getResourceName()).printStackTrace();
                }
                break;
            }
        }
        return longList;
    }
}
