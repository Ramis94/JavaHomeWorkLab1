package ru.innopolis.university.ramis.gilmanov.service;

import java.math.BigInteger;

/**
 * Created by innopolis on 13.10.16.
 */
public interface Calculator {

    /**
     * метод для подсчета суммы
     * @param arg принимает число, прибавляет к предыдущему результату
     * @return число типа BigInteger
     */
    public BigInteger add(Long arg);
}
