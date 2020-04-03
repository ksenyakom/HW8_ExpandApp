package com.company.io;

import com.company.entity.Person;
import com.company.exceptions.CsvException;
import com.company.utils.PersonCsvUtil;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataReader {
    private String fileName;

    public DataReader(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

    public List<Person> readFromFile() throws IOException {
        /**
         * Создаем и инициализируем кузультирующую коллекцию
         */
        List<Person> persons = new ArrayList<>();

        /**
         * Конструкция try с ресурсами
         * Ресурсы в скобках будут освобождены (закрыт канал чтения файла) автоматически
         */
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            reader.readLine(); // пропускаем строку с заголовками

            String csvString;
            /**
             * Читаем csv-файл пока не вернется пустая строка (конец файла)
             */
            while ((csvString = reader.readLine()) != null) {
                Person person = PersonCsvUtil.toObject(csvString);

                persons.add(person);

            }
        } catch (CsvException e) {
            e.printStackTrace();
        }

        return persons;
    }
}
