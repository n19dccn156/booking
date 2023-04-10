package com.group.booking.Utils;

import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class EncryptUltil {
    
    public static final String SECRET_KEY = "aes-algorithm";

    public static String encryptAES(String content) {
        try {
            MessageDigest sha = MessageDigest.getInstance("SHA-1");
            byte[] key = SECRET_KEY.getBytes("UTF-8");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16);
            SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return Base64.getEncoder().encodeToString(cipher.doFinal(content.getBytes()));
        } catch (Exception e) {
            return "";
        }
    }
}
