package com.company.utils;

import com.company.entity.Person;
import com.company.io.DataReader;
import com.company.io.DataWriter;
import com.company.io.IOConstants;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PersonChange {
    public static void change(Person person) {
        System.out.println(person);

        boolean cycle = true;
        while (cycle) {
            showMenuChangeObject();
            int uc = ConsoleScan.scannerInt("Your choice: ");
            String s;
            switch (uc) {
                case (1): {
                    s = ConsoleScan.scannerString("Enter firstName: ");
                    if (s != null) person.setFirstName(s);
                    break;
                }
                case (2): {
                    s = ConsoleScan.scannerString("Enter lastName: ");
                    if (s != null) person.setLastName(s);
                    break;
                }
                case (3): {
                    s = ConsoleScan.scannerString("Enter email: ");
                    if (s != null) person.setEmail(s);
                    break;
                }
                case (4): {
                    int a = ConsoleScan.scannerInt("Enter age: ");
                    if (a != 0) person.setAge(a);
                    break;
                }
                case (5): {
                    cycle = false;
                    System.out.print("New data saved: ");
                    System.out.println(person);
                    break;
                }
                default: {
                }
            }
        }
    }

    public static void showMenuChangeObject() {
        System.out.print("Choose field: ");
        System.out.print(" 1.Firstname.");
        System.out.print(" 2.Lastname.");
        System.out.print(" 3.Email.");
        System.out.print(" 4.Age.");
        System.out.println(" 5.Save and Exit.");
    }

    public static Person newPerson() {
        String firstName = ConsoleScan.scannerString("Enter firstname: ");
        if (firstName.equals("")) firstName = "noname";
        String lastName = ConsoleScan.scannerString("Enter lastname: ");
        if (lastName.equals("")) lastName = "noname";
        int age = ConsoleScan.scannerInt("Enter age: ");

        String email = ConsoleScan.scannerString("Enter email: ");
        if (email.equals("")) email = "no email";

        Person person = new Person(firstName, lastName, email, age);
        System.out.println(person.toString());
        return person;
    }

    public static List<Person> getAll() {
        DataReader dataReader = new DataReader(IOConstants.FILENAME);
        List<Person> personsFromFile = new ArrayList<Person>();
        try {
            personsFromFile = dataReader.readFromFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return personsFromFile;
    }

    public static void save(List<Person> persons) {
        try {
            DataWriter dataWriter = new DataWriter(IOConstants.FILENAME);
            dataWriter.writeToFile(persons);
            System.out.println("Changes saved to file.");
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
