package ru.innopolis.university.ramis.gilmanov.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.innopolis.university.ramis.gilmanov.service.Validator;

/**
 * Created by innopolis on 13.10.16.
 */
public class ValidatorImpl implements Validator {

    private static Logger logger = LoggerFactory.getLogger(ValidatorImpl.class);

    /**
     * проверка числа на четность и не отрицание
     * @param number
     * @return boolean
     */
    @Override
    public boolean isAnEvenPositive(Long number) {
        boolean result = false;
        if (number > 0 && number % 2 == 0) {
            logger.debug("number is positive : " + number);
            result = true;
        }
        return result;
    }
}
