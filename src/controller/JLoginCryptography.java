package controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class JLoginCryptography {

    private static final String FILE_PATH = "user_credentials.txt";
    
    public static boolean authenticateUser(String username, char[] password) {
        if (username == null || username.isEmpty() || password == null || password.length == 0) {
            return false;
        }
        
        try {
        	
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashedPassword = digest.digest(new String(password).getBytes());

            return verifyUserCredentials(username, hashedPassword);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static boolean verifyUserCredentials(String username, byte[] hashedPassword) {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":", 2);
                if (parts.length == 2 && parts[0].equals(username)) {
                    String storedHashedPassword = parts[1];
                    return storedHashedPassword.equals(bytesToHex(hashedPassword));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
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
