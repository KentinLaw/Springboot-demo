package com.kt.wms.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.kt.wms.model.system.User;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author kentinlaw
 * @version 1.0
 * @date 2020/4/2 16:09
 * @description token验证工具类
 */
@Data
@Component
@ConfigurationProperties(prefix = "jwt")
public class TokenUtils {

    private long expireTime;

    private String issuer;

    private String tokenSecret;

    //生成token
    public String getToken(User user) {
        Date expiresTime = new Date(System.currentTimeMillis() + expireTime);
        //JWT默认带Header，拼接Payload
        //最后添加Signature
        String token = JWT.create()
                .withIssuer(issuer)
                .withIssuedAt(new Date())
                .withExpiresAt(expiresTime)
                .withClaim("username",user.getUsername())
                .withClaim("nickname",user.getNickname())
                .sign(Algorithm.HMAC256(tokenSecret));
        return token;
    }

    //根据token获取username
    public String getUsernameFromToken(String token) {
        String username;
        try {
            DecodedJWT jwt = JWT.require(Algorithm.HMAC256(tokenSecret))
                    .build()
                    .verify(token);
            username = jwt.getClaim("username").asString();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }


    public Boolean verify(String token, User user){
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(tokenSecret)).build();
        DecodedJWT jwt = verifier.verify(token);

        String username = jwt.getClaim("username").asString();
        return Objects.equals(username.equals(user.getUsername()),!isTokenExpired(jwt));
    }
    /**
     * 判断令牌是否过期
     *
     * @param jwt 令牌
     * @return 是否过期
     */
    public Boolean isTokenExpired(DecodedJWT jwt) {
        try {
            Date expiration = jwt.getExpiresAt();
            return expiration.before(new Date());
        } catch (Exception e) {
            return true;
        }
    }
    //验证token
    public Map<String, String> verify(String token){
        Map<String, Claim> map = new HashMap<String, Claim>(200);
        try{
            //验证token是否合法
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(tokenSecret)).build();
            DecodedJWT jwt = verifier.verify(token);
            map = jwt.getClaims();
        }catch (Exception e){
            throw new RuntimeException("401");
        }
        Map<String, String> resultMap = new HashMap<>(map.size());
        //java8写法
        map.forEach((k, v) -> resultMap.put(k, v.asString()));
        return resultMap;
    }

}
