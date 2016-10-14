package ru.innopolis.university.ramis.gilmanov;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.innopolis.university.ramis.gilmanov.service.Calculator;
import ru.innopolis.university.ramis.gilmanov.service.ResourceGetter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ConnectException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by innopolis on 13.10.16.
 */
public class MyRunnable implements Runnable {

    private static Logger logger = LoggerFactory.getLogger(MyRunnable.class);

    private ResourceGetter resourceGetter;

    private Calculator calculator;

    private List<Long> longs = new ArrayList<>();

    public MyRunnable(ResourceGetter resourceGetter, Calculator calculator) {
        this.resourceGetter = resourceGetter;
        this.calculator = calculator;
    }

    public MyRunnable() {
    }

    /**
     *
     * @return calculator
     */
    public Calculator getCalculator() {
        return calculator;
    }

    public void setCalculator(Calculator calculator) {
        this.calculator = calculator;
    }

    /**
     *
     * @return resourceGetter
     */
    public ResourceGetter getResourceGetter() {
        return resourceGetter;
    }

    public void setResourceGetter(ResourceGetter resourceGetter) {
        this.resourceGetter = resourceGetter;
    }

    @Override
    public void run() {
        String resourceName = getResourceGetter().getResourceName();
        //проверка на файл
        if(getResourceGetter().isFile(resourceName)){
            try {
                logger.info(resourceName + " is file");
                //получение содержимого ресурса
                longs = getResourceGetter().getResourceContent(new Scanner(new File(resourceName)));
                logger.info(resourceName + " resource content received");
            } catch (FileNotFoundException e) {
                logger.error(resourceName + " not found");
                e.printStackTrace();
            }
        }
        //проверка на URL
        else if (getResourceGetter().isURL(resourceName)){
            try {
                logger.info(resourceName + " is url");
                URL url = new URL(resourceName);
                //получение содержимого ресурса
                longs = getResourceGetter().getResourceContent(new Scanner(url.openStream()));
                logger.info(resourceName + " resource content received");
            } catch (MalformedURLException e) {
                logger.error(resourceName + " - check URL");
                e.printStackTrace();
            } catch (ConnectException e) {
                logger.error(resourceName + " - connection error");
                e.printStackTrace();
            } catch (IOException e) {
                logger.error(resourceName + " - IOException");
                e.printStackTrace();
            }
        }
        if (!longs.isEmpty()){
            logger.info(resourceName + " Start summ");
            for (Long number: longs){
                //подсчет суммы и вывод в консоль
                System.out.println(getCalculator().add(number) + "\t\t" + resourceName);
            }
            logger.info(resourceName + " Finished summ");
        } else {
            System.out.println(resourceName + " positive numbers not found");
            logger.warn(resourceName + " positive numbers not found");
        }
        System.out.println("\t\t\t\tEND: " + resourceName);
    }
}