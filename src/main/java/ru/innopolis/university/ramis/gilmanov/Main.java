package ru.innopolis.university.ramis.gilmanov;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import ru.innopolis.university.ramis.gilmanov.service.Calculator;
import ru.innopolis.university.ramis.gilmanov.service.impl.CalculatorImpl;
import ru.innopolis.university.ramis.gilmanov.service.impl.ResourceGetterImpl;


public class Main {

    private static Logger logger = LoggerFactory.getLogger(Main.class);

    /**
     * Точка входа в приложение. Пример запуска приложения: java Main C:\\1.txt C:\\2.txt http://localhost:8080/
     * @param args передаем аргументы ввиде: C:\\1.txt C:\\2.txt http://localhost:8080/
     */
    public static void main(String[] args) {

        if(args.length != 0){
            MyRunnable resource;
            Calculator calculator = new CalculatorImpl();
            for (String nameResource : args){
                resource = new MyRunnable(new ResourceGetterImpl(nameResource), calculator);
                Thread thread = new Thread(resource);
                thread.start();
                MDC.put("resourceName", nameResource);
                logger.info("Start");
            }
        } else {
            System.out.println("Please, enter resources");
            logger.warn("NO RESOURCES");
        }
    }
}
