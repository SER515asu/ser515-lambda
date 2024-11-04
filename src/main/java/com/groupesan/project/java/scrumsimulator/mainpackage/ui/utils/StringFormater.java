package com.groupesan.project.java.scrumsimulator.mainpackage.ui.utils;

public class StringFormater {
    
    public static String capitalizeFirstLetter(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }

        return input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
    }

    public static String snakeCaseConverter(String input){
        if (input == null || input.isEmpty()) {
            return input;
        }

        String[] inputArr = input.split("_");
        String newString = "";

        for(String word: inputArr){
            newString += word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase();
            newString += " ";
        }

        return newString.trim();
    }
}
