package gestionefile;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


/**
 *
 * @author KT
 * @version 31/01/2024
 */

public class Main {

    public static void main(String[] args) {
        try {
            String jsonContent;
            try ( // Leggi il file user.json
                    BufferedReader br = new BufferedReader(new FileReader("user.json"))) {
                jsonContent = br.readLine();
            }

            // Estrai le informazioni necessarie dall'JSON
            String id = jsonContent.split("\"id\":")[1].split(",")[0].trim();
            String name = jsonContent.split("\"name\":")[1].split(",")[0].replaceAll("\"", "").trim();
            String surname = jsonContent.split("\"surname\":")[1].split(",")[0].replaceAll("\"", "").trim();
            String role = jsonContent.split("\"role\":")[1].replaceAll("\"", "").trim();

            // Chiedi all'utente di inserire username e password
            Scanner scanner = new Scanner(System.in);
            System.out.print("Inserisci il nuovo username: ");
            String username = scanner.nextLine();
            System.out.print("Inserisci la nuova password: ");
            String password = scanner.nextLine();

            // Cifra la password usando il cifrario di Vigénere (puoi implementare la tua funzione)
            String encryptedPassword = vigenereEncrypt (password, "chiave");

            // Scrivi nel file output.csv
            BufferedWriter bw = new BufferedWriter(new FileWriter("output.csv"));
            bw.write(username + ";" + encryptedPassword);
            bw.close();

            // Copia il file output.csv in copia.csv
            BufferedReader reader = new BufferedReader(new FileReader("output.csv"));
            BufferedWriter writer = new BufferedWriter(new FileWriter("copia.csv"));

            String line;
            while ((line = reader.readLine()) != null) {
                writer.write(line);
                writer.newLine();
            }

            reader.close();
            writer.close();

            System.out.println("Operazioni completate con successo.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Funzione di cifratura di Vigénere 
    private static String vigenereEncrypt(String text, String key) {
        StringBuilder encryptedText = new StringBuilder();
        int textLength = text.length();
        int keyLength = key.length();

        for (int i = 0; i < textLength; i++) {
            char currentChar = text.charAt(i);
            char keyChar = key.charAt(i % keyLength);

            int encryptedChar = (currentChar + keyChar) % 128;
            encryptedText.append((char) encryptedChar);
        }

        return encryptedText.toString();
    }
}
