package co.imp.tech.utils;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;

@Component
public class AESCryptoUtil {
    @Value("${security.aes.secret-key}")
    private String aesSecretKey;

    private static final String AES_ALGORITHM = "AES";
    private static final String AES_CIPHER_ALGORITHM = "AES/CBC/PKCS5Padding";

    private SecretKey secretKey;
    private IvParameterSpec ivParameterSpec;

    @PostConstruct
    public void init() throws Exception {
        this.secretKey = generateKey(256);
        this.ivParameterSpec = generateIv();
    }

    public SecretKey generateKey(int keySize) throws Exception {
        MessageDigest sha = MessageDigest.getInstance("SHA-256");
        byte[] keyBytes = sha.digest(aesSecretKey.getBytes(StandardCharsets.UTF_8));
        return new SecretKeySpec(keyBytes, 0, keySize / 8, AES_ALGORITHM);
    }

    public static IvParameterSpec generateIv() {
        byte[] iv = new byte[16];
        new SecureRandom().nextBytes(iv);
        return new IvParameterSpec(iv);
    }


}
