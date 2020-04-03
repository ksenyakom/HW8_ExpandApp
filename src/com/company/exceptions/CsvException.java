package com.company.exceptions;

/**
 * Исключение, которое имеет место при преобразовании строки из csv-файла к объекту
 */
public class CsvException extends Exception {

    public CsvException(String message) {
        super(message);
    }
}
