package spb.nicetu.OnlineElectronicsStore.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.Date;


@Component
public class JWTUtil {

    @Value("${security.jwt.secret}")
    private String secret;
    @Value("${security.jwt.lifetime_seconds}")
    private int lifetime;

    public String generateToken(String email) {
        Date expirationDate = Date.from(ZonedDateTime.now().plusSeconds(lifetime).toInstant());

        return JWT.create()
                .withSubject("User details")
                .withClaim("email", email)
                .withIssuedAt(new Date())
                .withIssuer("Electronics Store")
                .withExpiresAt(expirationDate)
                .sign(Algorithm.HMAC256(secret));
    }

    public String retrieveClaim(String token) throws JWTVerificationException {
        DecodedJWT validateToken = validateToken(token);
        return validateToken.getClaim("email").asString();
    }

    private DecodedJWT validateToken(String token) throws JWTVerificationException {
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(secret))
                .withSubject("User details")
                .withIssuer("Electronics Store")
                .build();

        return jwtVerifier.verify(token);
    }
}
