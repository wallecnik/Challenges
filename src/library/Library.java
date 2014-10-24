package library;

import java.io.BufferedReader;

/**
 * Many functions that would be used the same way all the time
 *
 * @author Wallecnik
 */
public class Library {

    /**
     * Reads the text-only file and returns it in a String
     *
     * @param filePath path to the file
     * @return         content of the file
     */
    public static String readFile(String filePath) {

        String output = "";

        try {
            String aLine = "";
            java.io.FileReader file = new java.io.FileReader(filePath);
            BufferedReader buffer = new BufferedReader(file);
            do {
                aLine = buffer.readLine();
                output += aLine + "\n";
            } while (aLine != null);

        }
        catch (Exception e) {
            System.out.println(e);
        }

        return output;

    }

}
