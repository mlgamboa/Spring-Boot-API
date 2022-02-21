package com.pointwest.discussion.config;

import com.pointwest.discussion.models.User;
import com.pointwest.discussion.repositories.UserRepositories;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtToken implements Serializable {

    // The secret keyword is retrieved from the application.properties file
    @Value("${jwt.secret}")
    private String secret;
    // 'secret' is our secret keyword for creating jwt token

    @Autowired
    private UserRepositories userRepositories;
    private static final long serialVersionUID = -2550185165626007488L;
    // pre-prepared value of the class jwt converted in UID
    // class name converted to a value
    // serialVersionUID is used to determine of the Class defined in the application is the same one used for serializing/deserializing a JWT

    public static final long JWT_TOKEN_VALIDITY = 5*60*60;
    // 5hrs * 60 mins * 60secs


    // claims -> info that we would like to present/show the user
    // Logic for preparing and creating the JWT
    private String doGenerateToken(Map<String, Object> claims, String subject){
        // .setClaims includes info to show the recipient -> which is the username
        // .setSubject adds descriptive info about the token
        // .setIssuedAt sets the time and date when the token was created
        // .setExpiration sets the expiration of the token
        // .sighWith creates the token using a prebuilt algorithm using the secret keyword
        // .compact builds the JWT and serializes it to a compact URL-safe string
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis())).setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY*1000)).signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    // Creates the JWT with the user details
    // UserDetails class comes from spring framework containing user info
    public String generateToken(UserDetails userDetails) {
        // Map is an object without duplicate keys
        Map<String, Object> claims = new HashMap<>();
        User user = userRepositories.findByUsername(userDetails.getUsername());
        claims.put("user", user.getId());
        return doGenerateToken(claims, userDetails.getUsername());
    }

    // To extract the claims/user info from the JWT Token
    private Claims getAllClaimsFromToken(String token){
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    // getting the claim from the token
    public <T> T getClaimFromToken(String token, Function<Claims, T>claimsResolver){
        final Claims CLAIMS = getAllClaimsFromToken(token);
        return claimsResolver.apply(CLAIMS);
    }

    // Get username from JWT Token
    public String getUsernameFromToken(String token){
        String claim = getClaimFromToken(token, Claims::getSubject);
        return claim;
    }

    // Get expiration from JWT
    public Date getExpirationDateFromToken(String token){
        return getClaimFromToken(token, Claims::getExpiration);
    }

    // Check if token is expired
    private Boolean isTokenExpired(String token){
        final Date EXPIRATION= getExpirationDateFromToken(token);
        return EXPIRATION.before(new Date());
    }

    // Validate JWT
    public Boolean validateToken(String token, UserDetails userDetails){
        final String USERNAME = getUsernameFromToken(token);
        return (USERNAME.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

}
