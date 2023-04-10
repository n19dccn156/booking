package com.group.booking.Utils;

import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class DecryptUltil {
    
    public static String decryptAES(String content) {
        try {
            MessageDigest sha = MessageDigest.getInstance("SHA-1");
            byte[] key = EncryptUltil.SECRET_KEY.getBytes("UTF-8");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16);
            SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(Base64.getDecoder().decode(content)));
        } catch (Exception e) {
            return "";
        }
    }
}
