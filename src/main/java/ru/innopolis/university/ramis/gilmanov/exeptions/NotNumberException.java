package ru.innopolis.university.ramis.gilmanov.exeptions;

/**
 * Created by GRamis on 14.10.2016.
 */
public class NotNumberException extends Exception {

    private String resourceName;
    private String resourceArg;

    /**
     *
     * @param message сообщение
     * @param resourceArg не числовой аргумент из-за которого появилась эта ошибка
     * @param resourceName имя ресурса где произошла эта ошибка
     */
    public NotNumberException(String message, String resourceArg, String resourceName){
        super(message);
        this.resourceName = resourceName;
        this.resourceArg = resourceArg;
    }

    /**
     * вывод сообщения
     */
    @Override
    public void printStackTrace() {
        System.err.println("Error in " + resourceName + " : " + resourceArg + " - is not a number");
    }
}
