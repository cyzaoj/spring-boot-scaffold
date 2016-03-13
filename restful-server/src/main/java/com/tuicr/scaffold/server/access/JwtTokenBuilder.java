package com.tuicr.scaffold.server.access;

import com.google.common.base.Preconditions;
import com.google.common.base.Verify;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author ylxia
 * @version 1.0
 * @package com.tuicr.authcenter.server
 * @date 16/3/13
 */
public class JwtTokenBuilder {

    private final String secret;
    private final UserDetailsService userService;

    public JwtTokenBuilder(String secret, UserDetailsService userService) {
        this.secret = Verify.verifyNotNull(secret);
        this.userService = Preconditions.checkNotNull(userService);
    }

    /**
     * 从token中获取用户信息
     *
     * @param token
     * @return
     */
    public UserDetails parseUserFromToken(String token) {
        String username = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
        return userService.loadUserByUsername(username);
    }

    /**
     * 根据user生成token秘钥字符串
     *
     * @param user
     * @return
     */
    public String createTokenForUser(UserDetails user) {
        return Jwts.builder()
                .setSubject(user.getUsername())
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }
}