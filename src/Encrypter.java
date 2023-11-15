package Lab12;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Encrypter {

    private int shift;
    private String encrypted;

    /**
     * Default Constructor
     */
    public Encrypter() {
        this.shift = 1;
        this.encrypted = "";
    }

    /**
     * Non-default Constructor
     * @param s - custom shift amount
     */
    public Encrypter(int s) {
        this.shift = s;
        this.encrypted = "";
    }

    /**
     * Encrypts the content of a file and writes the result to another file.
     *
     * @param inputFilePath      the path to the file containing the text to be encrypted
     * @param encryptedFilePath the path to the file where the encrypted text will be written
     * @throws Exception if an error occurs while reading or writing the files
     */
    public void encrypt(String inputFilePath, String encryptedFilePath) throws Exception {
        //TODO: 1. Call the read method, encrypt the file contents, and then write to new file
        String file = readFile(inputFilePath);
        String result = "";

        for (int i = 0; i < file.length(); i++) {
            char ch = file.charAt(i);

            if (Character.isLetter(ch)) {
                char encryptedChar = (char) (ch + shift);

                if ((Character.isLowerCase(ch) && encryptedChar > 'z') || (Character.isUpperCase(ch) && encryptedChar > 'Z')) {
                    encryptedChar = (char) (ch - (26 - shift));
                }

                result = result + encryptedChar;
            } else {
                result = result + ch;
            }

        }
        writeFile(result, encryptedFilePath);
    }

    /**
     * Decrypts the content of an encrypted file and writes the result to another file.
     *
     * @param messageFilePath    the path to the file containing the encrypted text
     * @param decryptedFilePath the path to the file where the decrypted text will be written
     * @throws Exception if an error occurs while reading or writing the files
     */
    public void decrypt(String messageFilePath, String decryptedFilePath) throws Exception {
        //TODO: 2. Call the read method, decrypt the file contents, and then write to new file
        String file = readFile(messageFilePath);
        String result = "";

        for (int i = 0; i < file.length(); i++) {
            char ch = file.charAt(i);

            if (Character.isLetter(ch)) {
                char encryptedChar = (char) (ch - shift);


                if ((Character.isLowerCase(ch) && encryptedChar < 'a') ||(Character.isLowerCase(ch) && encryptedChar > 'z')) {
                    encryptedChar = (char) (ch - (26 + shift));
                    if (Character.isUpperCase(encryptedChar)) {
                        encryptedChar = Character.toLowerCase(encryptedChar);
                        encryptedChar = (char) (ch + (18 + shift));

                    }
                    result = result + encryptedChar;
                }
                else if ((Character.isUpperCase(ch) && encryptedChar < 'A') || (Character.isUpperCase(ch) && encryptedChar > 'Z')) {
                    encryptedChar = (char) (ch + (18 + shift));

                    result = result + encryptedChar;
                }
                else{
                result = result + encryptedChar;}
            }
            else {
                result = result + ch;
            }

        }
        writeFile(result, decryptedFilePath);
    }

    /**
     * Reads the content of a file and returns it as a string.
     *
     * @param filePath the path to the file to be read
     * @return the content of the file as a string
     * @throws Exception if an error occurs while reading the file
     */
    private static String readFile(String filePath) throws Exception {
        String message = "";
        // Read each line and append it to the StringBuilder
        //TODO: 3. Read file from filePath
        File file = new File(filePath);

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            StringBuilder stringBuilder = new StringBuilder();
            //append it to the StringBuilder
            while ((message = reader.readLine()) != null) {
                stringBuilder.append(message).append("\n");
            }

            // Convert StringBuilder to String
            message = stringBuilder.toString();

            //System.out.println("File content as String:\n" + message);
            //System.out.println(fileContent.length());
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
        return message;
    }

    /**
     * Writes data to a file.
     *
     * @param data     the data to be written to the file
     * @param filePath the path to the file where the data will be written
     */
    private static void writeFile(String data, String filePath) {
        //TODO: 4. Write to filePath
        try {
            // Create a FileWriter object
            FileWriter writer = new FileWriter(filePath);

            // Write the content to the file
            writer.write(data);

            // Close the writer to flush and release resources
            writer.close();

            System.out.println("String successfully written to the file.");
        } catch (IOException e) {
            System.err.println("Error writing to the file: " + e.getMessage());
        }
    }

    /**
     * Returns a string representation of the encrypted text.
     *
     * @return the encrypted text
     */
    @Override
    public String toString() {
        return encrypted;
    }
}
