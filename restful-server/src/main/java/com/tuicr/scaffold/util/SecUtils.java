package com.tuicr.scaffold.util;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

import java.security.SecureRandom;
import java.util.UUID;

/**
 * @author ylxia
 * @version 1.0
 * @package com.tuicr.wxchat.util
 * @date 16/2/12
 */
public class SecUtils {

    private static int nonceValiditySeconds = 300;

    /**
     * @param userName   用户名
     * @param passWord   密码
     * @param httpMethod restful方式
     * @param uri        请求相对路径
     * @param response   请求报文
     * @param key        秘钥
     * @param realmName  身份证
     * @return
     */
    public static String generateDigest(
            String userName, String passWord,
            String httpMethod, String uri, String response,
            String key, String realmName
    ) {
        long expiryTime = System.currentTimeMillis() + (nonceValiditySeconds * 1000);
        String signatureValue = DigestUtils.md5Hex(expiryTime + ":" + key);
        String nonceValue = expiryTime + ":" + signatureValue;
        String nonceValueBase64 = new String(Base64.encodeBase64(nonceValue.getBytes()));

        // qop is quality of protection, as defined by RFC 2617.
        // we do not use opaque due to IE violation of RFC 2617 in not
        // representing opaque on subsequent requests in same session.

        StringBuffer b = new StringBuffer("Digest ")
                .append("username=\"").append(userName).append("\", ")
                .append("password=\"").append(passWord).append("\", ")
                .append("httpMethod=\"").append(httpMethod).append("\", ")
                .append("uri=\"").append(uri).append("\", ")
                .append("response=\"").append(response).append("\", ")
                .append("realm=\"").append(realmName).append("\", ")
                .append("qop=\"auth\",")
                .append("nc=\"").append(new SecureRandom().nextInt(9999999)).append("\", ")
                .append("cnonce=\"").append(UUID.randomUUID().toString().replaceAll("-", "")).append("\", ")
                .append("nonce=\"").append(nonceValueBase64).append("\", ");

        return b.toString();
    }
}