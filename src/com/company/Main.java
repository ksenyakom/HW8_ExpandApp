package com.company;

import com.company.entity.Person;
import com.company.io.DataReader;
import com.company.io.DataWriter;
import com.company.io.IOConstants;
import com.company.utils.ConsoleScan;
import com.company.utils.Menu;
import com.company.utils.PersonChange;

import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        writeObjectsToFile();

        boolean cycle = true;
        while (cycle) {
            Menu.showMenu();
            int choice = ConsoleScan.scannerInt("Your choice: ");
            switch (choice) {
                case (1): {
                    readObjectsToFile();
                    break;
                }
                case (2): {
                    changeObject();
                    break;
                }
                case (3): {
                    addObject();
                    break;
                }
                case (4): {
                    deleteObject();
                    break;
                }
                case (5): {
                    cycle = false;
                    break;
                }
                default: {
                    System.out.println("Choose option");
                }
            }
        }
    }

    public static void deleteObject() {
        List<Person> persons = PersonChange.getAll();
        int uc = ConsoleScan.scannerInt("Choose object number to Delete:");
        uc--;
        if (uc < persons.size()) {
            persons.remove(uc);
            PersonChange.save(persons);
            System.out.println("Object deleted from file.");
        } else System.out.println("Object number do not exist.");

    }

    public static void addObject() {
        List<Person> persons = PersonChange.getAll();
        Person newPerson = PersonChange.newPerson();
        persons.add(newPerson);
        PersonChange.save(persons);
    }

    public static void changeObject() {
        List<Person> persons = PersonChange.getAll();
        int uc = ConsoleScan.scannerInt("Choose object number to change:");
        uc--;
        if (uc < persons.size()) {
            PersonChange.change(persons.get(uc));
            PersonChange.save(persons);
        } else System.out.println("Object number do not exist.");
    }


    public static void writeObjectsToFile() {
        /**
         * Создание и инициализация объектов
         */
        Person person1 = new Person("Harry", "Potter", "harry.potter@mail.com", 17);
        Person person2 = new Person("Hermione", "Granger ", "hermione.granger@mail.com", 17);
        Person person3 = new Person("Ronald", "Ronald", "ronald.weasley@mail.com", 17);

        /**
         * Создаем коллекцию на основе созданных объектов
         * Внимание конструкция List.of(...) создает неизменяемую коллекцию
         * (вы не сможете добавлять и удалять объекты из коллекции)
         */
        List<Person> persons = List.of(person1, person2, person3);

        /**
         * Инициализация объекта инкапсулирующего логикузаписи в файл
         */
        DataWriter dataWriter = new DataWriter(IOConstants.FILENAME);
        try {
            /**
             * Вызов метода записывающего данные в файл
             */
            dataWriter.writeToFile(persons);
            System.out.println("Объекты записаны в файл");
        } catch (IOException e) {
            /**
             * Обработка ошибки ввода/вывода
             */
            e.printStackTrace();
        }
    }

    public static void readObjectsToFile() {
        DataReader dataReader = new DataReader(IOConstants.FILENAME);
        try {
            /**
             * Чтение (парсинг) объектов из файла
             */
            List<Person> personsFromFile = dataReader.readFromFile();

            /**
             * Вывод коллекции объектов на экран
             */
            System.out.println(personsFromFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
