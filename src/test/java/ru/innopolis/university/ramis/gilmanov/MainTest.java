package ru.innopolis.university.ramis.gilmanov;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.innopolis.university.ramis.gilmanov.service.Calculator;
import ru.innopolis.university.ramis.gilmanov.service.ResourceGetter;
import ru.innopolis.university.ramis.gilmanov.service.Validator;
import ru.innopolis.university.ramis.gilmanov.service.impl.CalculatorImpl;
import ru.innopolis.university.ramis.gilmanov.service.impl.ResourceGetterImpl;
import ru.innopolis.university.ramis.gilmanov.service.impl.ValidatorImpl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
/**
 * Created by innopolis on 13.10.16.
 */
public class MainTest {

    private Logger logger = LoggerFactory.getLogger(MainTest.class);
    private Validator validator;
    private ResourceGetter resourceGetter;
    private Calculator calculator;

    private Mockery context;

    /**
     * инициализация переменных до запуска теста
     */
    @Before
    public void before(){
        logger.info("This is @Before method");
        this.context = new JUnit4Mockery();
        validator = new ValidatorImpl();
        calculator = new CalculatorImpl();
        resourceGetter = new ResourceGetterImpl();
    }

    /**
     * проверка числа на четность и не отрицание
     */
    @Test
    public void testValidator(){
        logger.info("This is testValidator method");
        assertTrue("Number is not an even positive", validator.isAnEvenPositive(6L));
    }

    /**
     * проверка на сложение
     */
    @Test
    public void testCalculator(){
        logger.info("This is testCalculator method");
        assertTrue("False add", BigInteger.valueOf(10L).equals(calculator.add(10L)));
    }

    /**
     * проверка на файл
     */
    @Test
    public void isFile(){
        logger.info("This is isFile method");
        assertTrue("Is not a File", resourceGetter.isFile("/home/innopolis/IdeaProjects/HomeworkInno/files/1"));
    }

    /**
     * проверка на URL
     */
    @Test
    public void isURL(){
        logger.info("This is isURL method");
        assertTrue("Is not a File", resourceGetter.isURL("http://localhost:8080/"));
    }

    /**
     * получение содержимого ресурса
     */
    @Test
    public void getResourceContent(){
        logger.info("This is getResourceContent method");

        final ResourceGetter resourceGetterMock = context.mock(ResourceGetter.class);
        final List<Long> longs = new ArrayList<>();
        final Scanner scanner = new Scanner("1");
        longs.add(1L);
        longs.add(2L);
        longs.add(4L);
        longs.add(8L);
        context.checking(new Expectations(){{
            oneOf(resourceGetterMock).getResourceContent(scanner);
            will(returnValue(longs));
        }});

        List<Long> longs1 = resourceGetterMock.getResourceContent(scanner);

        assertEquals(longs, longs1);
    }

    /**
     * вывод сообщения о завершении теста
     */
    @After
    public void after(){
        logger.info("This is @After method");
    }
}
