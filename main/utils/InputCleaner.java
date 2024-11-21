package main.utils;//Utility class to convert strings to ints

public class InputCleaner {
    public static int cleanToIntegers(String input) {
        //Remove letters and symbols
        String cleanInput = input.replaceAll("[^0-9]", "");

        if (cleanInput.isEmpty()) {
            System.out.println("Invalid response, please input an integer");
            return -1;
        }

        //returns an integer
        return Integer.parseInt(cleanInput);
    }

    public static double cleanToDouble(String input) {
        //Remove letters and symbols
        String cleanInput = input.replaceAll("[^0-9]", "");

        if (cleanInput.isEmpty()) {
            System.out.println("Invalid response, please input an integer");
            return -1;
        }

        //returns an integer
        return Double.parseDouble(cleanInput);
    }
}
