package ASM3.config;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;

public class JwtUtil {
    private static final String SECRET_KEY = "xmCk7NspGQQZGOkRfDXUznwH0cfMfWXrZRdSrKmhMNQ57aIFNb+4pVWVyUaVGYcXB8FD3X2DM14ZmN8Jxc+Ieg==";
    private static final long EXPIRATION_TIME = 86400000; // 1 day in milliseconds

    // Hàm tạo token
    public static String generateToken(String userId, String role) {
        Date expirationDate = new Date(System.currentTimeMillis() + EXPIRATION_TIME);

        return Jwts.builder()
                .setSubject(userId)
                .setIssuedAt(new Date())
                .claim("role", role)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }


    // Hàm trích xuất quyền từ token
    public static String getRoleFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();

        return (String) claims.get("role");
    }

    // Hàm trích xuất userID từ token
    public static String getUserIdFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }
}

