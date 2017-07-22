package application;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class CryptoUtils {
    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES";

    public static void encrypt(String key, File inputFile, File outputFile)
            throws CryptoException {
        doCrypto(Cipher.ENCRYPT_MODE, key, inputFile, outputFile);
    }

    public static void decrypt(String key, File inputFile, File outputFile)
            throws CryptoException {
        doCrypto(Cipher.DECRYPT_MODE, key, inputFile, outputFile);
    }

    public static byte[] encryptText(String key, String inputText)
            throws CryptoException {
        return doCryptoText(Cipher.ENCRYPT_MODE, key, inputText);
    }

    public static byte[] decryptText(String key, String inputText)
            throws CryptoException {
        return doCryptoText(Cipher.DECRYPT_MODE, key, inputText);
    }

    private static void doCrypto(int cipherMode, String key, File inputFile,
                                 File outputFile) throws CryptoException {
        try {
            Key secretKey = new SecretKeySpec(key.getBytes(), ALGORITHM);
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(cipherMode, secretKey);

            FileInputStream inputStream = new FileInputStream(inputFile);
            byte[] inputBytes = new byte[(int) inputFile.length()];
            inputStream.read(inputBytes);

            byte[] outputBytes = cipher.doFinal(inputBytes);

            FileOutputStream outputStream = new FileOutputStream(outputFile);
            outputStream.write(outputBytes);

            inputStream.close();
            outputStream.close();

        } catch (NoSuchPaddingException | NoSuchAlgorithmException
                | InvalidKeyException | BadPaddingException
                | IllegalBlockSizeException | IOException ex) {
            throw new CryptoException("Error encrypting/decrypting file", ex);
        }
    }

    private static byte [] doCryptoText(int cipherMode, String key, String inputString) throws CryptoException {
        try {
            Key secretKey = new SecretKeySpec(key.getBytes(), ALGORITHM);
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(cipherMode, secretKey);

            //FileInputStream inputStream = new FileInputStream(inputFile);
            System.out.println(inputString + " inputString");
            //byte[] inputBytes = new byte[ inputString.length() ];
            byte[] inputBytes = Base64.getEncoder().encode( inputString.getBytes() );

            String string1 = new String(Base64.getDecoder().decode(inputBytes));
            System.out.println(string1 + " inputString");
            //byte[] inputBytes = new byte[ (int) inputString.length()];
                    //new byte[(int) inputString.length()];
            //inputStream.read(inputBytes);

            byte[] outputBytes = cipher.doFinal(inputBytes);

            //FileOutputStream outputStream = new FileOutputStream(outputFile);
            //outputStream.write(outputBytes);

            //inputStream.close();
            //outputStream.close();
            return outputBytes;

        } catch (NoSuchPaddingException | NoSuchAlgorithmException
                | InvalidKeyException | BadPaddingException
                | IllegalBlockSizeException ex) {
            throw new CryptoException("Error encrypting/decrypting file", ex);
        }
    }


}
