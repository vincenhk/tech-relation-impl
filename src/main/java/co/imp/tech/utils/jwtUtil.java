package co.imp.tech.utils;

import io.jsonwebtoken.Jwts;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.lang.reflect.Field;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class jwtUtil {


    @Value("${security.jwt.secret-key}")
    private String secretKey;

    private static SecretKey key;

    @PostConstruct
    public void initJwt() {
        try {
            byte[] decodedKey = Base64.getDecoder().decode(secretKey);
            key = new SecretKeySpec(decodedKey, 0, decodedKey.length, "HmacSHA256");
        } catch (IllegalArgumentException e) {
            throw new IllegalStateException("Failed to decode the secret key, ensure it's Base64 encoded", e);
        } catch (Exception e) {
            throw new IllegalStateException("Failed to initialize JWT", e);
        }
    }

    public static String generateToken(String email, Map<String, Object> claims) {
        return Jwts.builder()
                .setSubject(email)
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(key)
                .compact();
    }

    public static <T> Map<String, Object> toClaims(T data) throws Exception {
        Map<String, Object> claims = new HashMap<>();
        Field[] fields = data.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                Object value = field.get(data);
                claims.put(field.getName(), value);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return claims;
    }
}
