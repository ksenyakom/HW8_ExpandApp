package com.company.utils;

import com.company.entity.Person;
import com.company.exceptions.CsvException;
import com.company.exceptions.ExceptionMessageConstants;

public class PersonCsvUtil {

    public final static String DELIMITER = ";";

    public static final String[] HEADERS = {"First name", "Last name", "E-mail", "Age"};

    public static String headers() {
        return String.join(DELIMITER, HEADERS) + "\n";
    }

    /**
     * Преобразуем объект Person к строке для записи в csv-файл
     */
    public static String toCsvString(Person person) {
        return person.getFirstName() + DELIMITER +
                person.getLastName() + DELIMITER +
                person.getEmail() + DELIMITER +
                person.getAge() + "\n";
    }

    /**
     * Преобразуем строку из csv-файла к объекту
     */
    public static Person toObject(String csvString) throws CsvException {
        String[] dataArray = csvString.split(DELIMITER);

        if (dataArray.length != 4) {
            String errorMessage = String.format(ExceptionMessageConstants.ERROR_MESSAGE_PATTERN, csvString);
            throw new CsvException(errorMessage);
        }

        String firstName = dataArray[0];
        String lastName = dataArray[1];
        String email = dataArray[2];
        int age = Integer.parseInt(dataArray[3]);

        return new Person(firstName, lastName, email, age);
    }
}
