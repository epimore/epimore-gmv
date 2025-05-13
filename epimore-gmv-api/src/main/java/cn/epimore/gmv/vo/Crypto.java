package cn.epimore.gmv.vo;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.security.Security;
import java.util.Random;

public class Crypto {
    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    private static final String BASE_STR = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final String DEFAULT_KEY = "1234567890All in Rust 1234567890"; // 32 长度

    public static String genAsciiChars(int size) {
        Random random = new SecureRandom();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < size; i++) {
            int index = random.nextInt(BASE_STR.length());
            stringBuilder.append(BASE_STR.charAt(index));
        }
        return stringBuilder.toString();
    }

    public static String encrypt(String key, String data) throws Exception {
        String ivStr = genAsciiChars(16);
        byte[] iv = ivStr.getBytes(StandardCharsets.UTF_8);

        SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(iv);

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding", "BC");
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);

        byte[] encrypted = cipher.doFinal(data.getBytes(StandardCharsets.UTF_8));

        // Combine IV and encrypted data
        byte[] ivAndEncrypted = new byte[iv.length + encrypted.length];
        System.arraycopy(iv, 0, ivAndEncrypted, 0, iv.length);
        System.arraycopy(encrypted, 0, ivAndEncrypted, iv.length, encrypted.length);

        return Base64.toBase64String(ivAndEncrypted);
    }

    public static String decrypt(String key, String data) throws Exception {
        byte[] ivAndEncrypted = Base64.decode(data);

        byte[] iv = new byte[16];
        System.arraycopy(ivAndEncrypted, 0, iv, 0, iv.length);

        byte[] encrypted = new byte[ivAndEncrypted.length - iv.length];
        System.arraycopy(ivAndEncrypted, iv.length, encrypted, 0, encrypted.length);

        SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(iv);

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding", "BC");
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);

        byte[] decrypted = cipher.doFinal(encrypted);
        return new String(decrypted, StandardCharsets.UTF_8);
    }

    public static String defaultEncrypt(String data) throws Exception {
        return encrypt(DEFAULT_KEY, data);
    }

    public static String defaultDecrypt(String data) throws Exception {
        return decrypt(DEFAULT_KEY, data);
    }

    public static void main(String[] args) {
        try {
            String originalText = "Ms@2023%Kht";
            String encryptedText = defaultEncrypt(originalText);
            System.out.println("Encrypted: " + encryptedText);

            String decryptedText = defaultDecrypt(encryptedText);
            System.out.println("Decrypted: " + decryptedText);

            System.out.println(originalText.equals(decryptedText));

//            String decryptedText = defaultDecrypt("NVJleGlXZFNnSG1Ca1hDas8s8TVttw2sO8Wt9iHewsQ=");
//            System.out.println("Decrypted: " + decryptedText);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}