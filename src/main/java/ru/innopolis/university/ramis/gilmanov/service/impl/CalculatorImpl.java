package ru.innopolis.university.ramis.gilmanov.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.innopolis.university.ramis.gilmanov.service.Calculator;

import java.math.BigInteger;

/**
 * Created by innopolis on 13.10.16.
 */
public class CalculatorImpl implements Calculator {

    private static Logger logger = LoggerFactory.getLogger(CalculatorImpl.class);

    private BigInteger summa = BigInteger.valueOf(0);

    /**
     *
     * @param arg
     * @return summa
     */
    @Override
    public BigInteger add(Long arg) {
        logger.debug("summa = " + summa);
        return summa = summa.add(BigInteger.valueOf(arg.intValue()));
    }
}
