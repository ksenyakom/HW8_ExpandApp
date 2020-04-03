package com.company.utils;

import java.util.Scanner;

public class ConsoleScan {

    public static String scannerString(String message) {
        System.out.print(message);
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        return s;
    }

    public static int scannerInt(String message) {
        int i = 0;
        try{
        System.out.print(message);
        Scanner scanner = new Scanner(System.in);
         i = scanner.nextInt();}
        catch (Exception e){

        }
        return i;
    }
}
