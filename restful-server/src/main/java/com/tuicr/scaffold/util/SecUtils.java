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


    static String generateDigest(boolean passwordAlreadyEncoded, String username,
                                 String realm, String password, String httpMethod, String uri, String qop,
                                 String nonce, String nc, String cnonce) throws IllegalArgumentException {
        String a1Md5;
        String a2 = httpMethod + ":" + uri;
        String a2Md5 = DigestUtils.md5Hex(a2);

        if (passwordAlreadyEncoded) {
            a1Md5 = password;
        } else {
            a1Md5 = encodePasswordInA1Format(username, realm, password);
        }

        String digest;

        if (qop == null) {
            // as per RFC 2069 compliant clients (also reaffirmed by RFC 2617)
            digest = a1Md5 + ":" + nonce + ":" + a2Md5;
        } else if ("auth".equals(qop)) {
            // As per RFC 2617 compliant clients
            digest = a1Md5 + ":" + nonce + ":" + nc + ":" + cnonce + ":" + qop + ":"
                    + a2Md5;
        } else {
            throw new IllegalArgumentException("This method does not support a qop: '"
                    + qop + "'");
        }

        return DigestUtils.md5Hex(digest);
    }


    static String encodePasswordInA1Format(String username, String realm, String password) {
        String a1 = username + ":" + realm + ":" + password;

        return DigestUtils.md5Hex(a1);
    }

    /**
     * @param userName   用户名
     * @param passWord   密码
     * @param httpMethod restful方式
     * @param uri        请求相对路径
     * @param key        秘钥
     * @param realmName  身份证
     * @return
     */
    public static String generateDigest(
            String userName, String passWord,
            String httpMethod, String uri,
            String key, String realmName
    ) {
        long expiryTime = System.currentTimeMillis() + (nonceValiditySeconds * 1000);
        String signatureValue = DigestUtils.md5Hex(expiryTime + ":" + key);
        String nonceValue = expiryTime + ":" + signatureValue;
        String nonceValueBase64 = new String(Base64.encodeBase64(nonceValue.getBytes()));
        String nc = String.valueOf(Math.abs(new SecureRandom().nextInt(Integer.MAX_VALUE)));
        String cnonce = UUID.randomUUID().toString().replaceAll("-", "");
        String response = generateDigest(false, userName, realmName, passWord, httpMethod, uri, "auth", nonceValueBase64, nc, cnonce);


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
                .append("nc=\"").append(nc).append("\", ")
                .append("cnonce=\"").append(cnonce).append("\", ")
                .append("nonce=\"").append(nonceValueBase64).append("\", ");

        return b.toString();
    }
}
