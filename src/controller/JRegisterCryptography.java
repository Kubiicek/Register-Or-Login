package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.swing.JOptionPane;

public class JRegisterCryptography {

    private static final String FILE_PATH = "user_credentials.txt";

    public static int registerUser(String username, char[] password) {
        if (username != null && !username.isEmpty() && password != null && password.length > 0) {
            try {
               
                MessageDigest digest = MessageDigest.getInstance("SHA-256");
                byte[] hashedPassword = digest.digest(new String(password).getBytes());

                if (!userExists(username)) {
                    saveUserCredentials(username, hashedPassword);
                    return 1;
                } else {
                    return 3;
                }
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
                return 2;
            }
        }
        return 2;
    }

    private static boolean userExists(String username) {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":", 2);
                if (parts.length == 2 && parts[0].equals(username)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private static void saveUserCredentials(String username, byte[] hashedPassword) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            String hashedPasswordHex = bytesToHex(hashedPassword);
            writer.write(username + ":" + hashedPasswordHex);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
