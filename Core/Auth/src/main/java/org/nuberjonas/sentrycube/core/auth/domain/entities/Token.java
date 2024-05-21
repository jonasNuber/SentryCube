package org.nuberjonas.sentrycube.core.auth.domain.entities;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.nuberjonas.sentrycube.core.auth.domain.valueobjects.*;
import org.nuberjonas.sentrycube.core.sharedkernel.valueobjects.SessionId;
import org.nuberjonas.sentrycube.core.sharedkernel.valueobjects.TokenId;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Token {
    private TokenId tokenId;
    private SessionId sessionId;
    private EncodedToken encodedToken;
    private SignKeyId signKeyId;
    private TokenType tokenType;
    private CreationTime creationTime;
    private ExpirationTime expirationTime;

    private Token(TokenId tokenId, SessionId sessionId, EncodedToken encodedToken, SignKeyId signKeyId, TokenType tokenType, CreationTime creationTime, ExpirationTime expirationTime) {
        this.tokenId = tokenId;
        this.sessionId = sessionId;
        this.encodedToken = encodedToken;
        this.signKeyId = signKeyId;
        this.tokenType = tokenType;
        this.creationTime = creationTime;
        this.expirationTime = expirationTime;
    }

    public static Token createAccessToken(Session session, User user, Realm realm) {
        return createToken(session, user, realm, TokenType.ACCESS, realm.accessTokenLifeSpan());
    }

    public static Token createRefreshToken(Session session, User user, Realm realm) {
        return createToken(session, user, realm, TokenType.REFRESH, realm.refreshTokenLifespan());
    }

    private static Token createToken(Session session, User user, Realm realm, TokenType tokenType, Integer tokenLifespan) {
        var keyId = new SignKeyId(RsaKeyManager.getRandomKey());
        var creationTime = session.getCreationTime();
        var expirationTime = new ExpirationTime(creationTime, tokenLifespan);

        var jwtBuilder = Jwts.builder()
                .subject(user.userCredentials().userName().name())
                .claim("user_id", user.userId().id())
                .claim("session_id", session.getSessionId())
                .claim("client_id", session.getClientId());

        if (tokenType == TokenType.ACCESS) {
            jwtBuilder
                    .claim("realm_roles", user.realmRoles())
                    .claim("client_roles", user.clientRoles());
        }

        var token = jwtBuilder
                .signWith(RsaKeyManager.getPrivateKey(keyId), SignatureAlgorithm.RS256)
                .compact();

        return new Token(
                new TokenId(),
                session.getSessionId(),
                new EncodedToken(token),
                keyId,
                tokenType,
                creationTime,
                expirationTime
        );
    }

    public TokenId getTokenId() {
        return tokenId;
    }

    public SessionId getSessionId() {
        return sessionId;
    }

    public EncodedToken getEncodedToken() {
        return encodedToken;
    }

    public SignKeyId getSignKeyId() {
        return signKeyId;
    }

    public TokenType getTokenType() {
        return tokenType;
    }

    public CreationTime getCreationTime() {
        return creationTime;
    }

    public ExpirationTime getExpirationTime() {
        return expirationTime;
    }

    // only for simplicity
    private static class RsaKeyManager{
        private static final Map<Integer, KeyPair> keyPairs = new HashMap<>();

        private static Random random;

         static {
            random = new Random();

            try{
                keyPairs.put(0, createKeyPair("MIICXAIBAAKBgHzugmr9JcB17XHcHhJ48RU/D88YMxV11CQ+Ipf3eGUrGx648HKG\n" +
                                "859funyPlExQl+HbhN44yTXfZM5Ow4pvLmm7lb/HLZ4NgnAR9YDAWqOvzNsqW6DY\n" +
                                "aHN/bV8ddsIfuhoQIz+X/+KiT6mMumuJHVehEiSyk0mabvPc6vH3C1cLAgMBAAEC\n" +
                                "gYAYXD2L9R6mX5hUKlKm0XRY6zn+7L4RQi+CfacrMLMGLxiTrgYcntnkSrtHTIvj\n" +
                                "MZfcD3ziA/qgCZZTcCJK3REUgOqA53zsmthKlo5WO0nIXoCX6sL6JUzMXeHWKiQ1\n" +
                                "iMkRkvSAuxOgHDMQROu6FImoC4SxnchcY3b7bGRy2wQukQJBAMHIMPzR86NlJNX/\n" +
                                "NT8fqU9fOJhXRlyKtlZqRIsJoRc7B+kAA5eGIESKRXSpYwV9NpvabJCpKNKfVIzD\n" +
                                "ZqzsZYMCQQClCzPXZySE+wdtEGwLsoBAJ/PL6gMYTc83WgFkH3K1EUN5K7HNtIgs\n" +
                                "oIBH0zEYb1ZYKLEhp5NP2MeSWHq7qpnZAkAUK+idiKVZ7t9kv0olpgAT2Q+lHACh\n" +
                                "BXdxi2JTLs9sAaMTKAM3tKiLOfF88VzzZ1NV6ei/LjynoBpHBNgdTAZNAkEAnhh/\n" +
                                "DmV07BVy5ZyHgyT4CXy74qdFU7ClQEZtcrVVMfFZiua6KWw3zVfpyeOZ8egk88n2\n" +
                                "82AMkr37Xkp3NvGS8QJBAK2r06oAEBif5NytOhCFsfXhwKhvyMD/lM6ooBrABd2L\n" +
                                "BSy//SQOGwvidgvw/AWe/hBE19zEVf3d6T6ADAfaMAw=",
                        "MIGeMA0GCSqGSIb3DQEBAQUAA4GMADCBiAKBgHzugmr9JcB17XHcHhJ48RU/D88Y\n" +
                                "MxV11CQ+Ipf3eGUrGx648HKG859funyPlExQl+HbhN44yTXfZM5Ow4pvLmm7lb/H\n" +
                                "LZ4NgnAR9YDAWqOvzNsqW6DYaHN/bV8ddsIfuhoQIz+X/+KiT6mMumuJHVehEiSy\n" +
                                "k0mabvPc6vH3C1cLAgMBAAE="));

                keyPairs.put(1, createKeyPair("MIICXQIBAAKBgQCOlYB7xuUQyS17ceoWwiby5lFCKhoDm8R5mSw2+sUTlB8cS5Ve\n" +
                                "6vGzcjOFkizYZE4APO31DoELeYA1tO5HQXH3BygNoWH0xVsS7c/CM8cqodiH+0xZ\n" +
                                "Dp4ZXblbbWzzSg4lTk/XH5f0k1n4DGJ0hxFK53FQysGfnIu3Zr5KPAy60wIDAQAB\n" +
                                "AoGAQ0HeQ4TElLBxFePcKjQXL0jZV76IWt9lRH6uTpjn0Z5zNmOo6AcnUCA0kXMs\n" +
                                "WD6oVJBpJslasdD0W2EnbNFAMjPR8m9hpckfjecpHDm62jEssZVXCZtd2JhQ5mTi\n" +
                                "VtcKek03o2MV0w24eYDXTIaQgZNFSs2mE/gzZ+2sHGTQgdECQQDKTtwDpBnW/OEV\n" +
                                "jjQT1+7miM2RVB0UQoxK+X96odnDdQsgnc7qegQiNsiWu/SmPfqOy0K62Y515fCb\n" +
                                "eodjx8HNAkEAtGzkbQgo2WwhqF3ho+zh1R/XSAdheJ1m9mp5zae1dn0iZVP0V/Qa\n" +
                                "LqDIM2D+ZaUOCJjSO2yiDN2W9Cw1lEZPHwJBAIBoL1OMS+5eEYt59+oEa5kdicTN\n" +
                                "a6uZVayc0eIHas2Zs5dfMp83FPqWZRjvcag2vJr3voIUZkzExsQYsGiPuJ0CQBqE\n" +
                                "Zo4fxOEvo0K7iY4uIoLQSzOq4gh4GcndnEk9fM4NViDOVfsXRUHVH5dtifYmbAmX\n" +
                                "bE6PiMGbT7RTSwyfyMECQQCSLNCnzniKsvkcWFN0H4PsU0WfUmpCW6/qbmSVdaT6\n" +
                                "uvQGt7i5fvE9EaFnBrIIXkehFuvl1wS/tiugs7bOz5Fp",
                                "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCOlYB7xuUQyS17ceoWwiby5lFC\n" +
                                "KhoDm8R5mSw2+sUTlB8cS5Ve6vGzcjOFkizYZE4APO31DoELeYA1tO5HQXH3BygN\n" +
                                "oWH0xVsS7c/CM8cqodiH+0xZDp4ZXblbbWzzSg4lTk/XH5f0k1n4DGJ0hxFK53FQ\n" +
                                "ysGfnIu3Zr5KPAy60wIDAQAB"));

                keyPairs.put(2, createKeyPair("MIICWwIBAAKBgGGBreGwEvdmxuJN0VuZA2Y4Q/tQqIU7BLUeegU9GUsco9lO7YAW\n" +
                                "ysFW3rvb8XSJ4Z4Yk3F45YIKRUE/CMdOrBkCjDCE8hGeagRypHPvMPehKI8LL2jr\n" +
                                "L0ZVgDBBVSWmRpVTH05XOgX+ZBAlBw/ymHG/BA5WigsgHn09TrQBo/H/AgMBAAEC\n" +
                                "gYBCGzlYGZfCQ9vy8e9Oup6jXuI4MKcQLMM0SEVR5+qOEgzD987tWIZ+Lfc0Rhmd\n" +
                                "eR2NofvUorJ3R59MbP/2oE/siz5uQxFPiVcBbN0eVMOSDn6V4Pn0W11pspUfUO0O\n" +
                                "2N2YTPtXVgHK+geey/kPUJ+UqVleTKi3B13lBdCmzOpOAQJBALy2oz0mdBF01WKH\n" +
                                "HxUAhb7P1brsVCVsWpkqMWO2/D5GSG0GmgfNPgP+FFghZPqlkm38Z+57V+WxYQCz\n" +
                                "zjt0KJECQQCERd8iAGxDFEfWsPLZEnH25nr/mbR/93AFuP4ATPKFhWj0XUQFKaoK\n" +
                                "s0bRpUHSKK2rSohpTr99DFYED/43qTmPAkAbZrmVK3w2/KvvPod42TaSWkNCfQZK\n" +
                                "2Mze6I17WRqPQE1L52V+NIMIuV/i3BZ5fokgh0HgEvDUXUkkfPc5EsjBAkAkk1WS\n" +
                                "B1/oZJFA0ok0cR9GUJEuYIhC4l79lhvWZXCibzOIbsnCS7UTZGNu0S2UOV9cgC2c\n" +
                                "QQQ+Rcz7LpjzNz4NAkEAmbam3ibpv0hyef63sIZv8tBh3B8UuLo/ex3bfdA7NEZO\n" +
                                "v7AkkLaCCFWyAybj3Jm1WNWCBww4YxvfF8LzzhOzhg==",
                                "MIGeMA0GCSqGSIb3DQEBAQUAA4GMADCBiAKBgGGBreGwEvdmxuJN0VuZA2Y4Q/tQ\n" +
                                "qIU7BLUeegU9GUsco9lO7YAWysFW3rvb8XSJ4Z4Yk3F45YIKRUE/CMdOrBkCjDCE\n" +
                                "8hGeagRypHPvMPehKI8LL2jrL0ZVgDBBVSWmRpVTH05XOgX+ZBAlBw/ymHG/BA5W\n" +
                                "igsgHn09TrQBo/H/AgMBAAE="));
            } catch (Exception exception){
                throw new RuntimeException(exception);
            }
        }

        private static KeyPair createKeyPair(String base64PublicKey, String base64PrivateKey) throws Exception {
            var publicKeyBytes = Base64.getDecoder().decode(base64PublicKey);
            var publicKeySpec = new X509EncodedKeySpec(publicKeyBytes);
            var publicKey = KeyFactory.getInstance("RSA").generatePublic(publicKeySpec);

            var privateKeyBytes = Base64.getDecoder().decode(base64PrivateKey);
            var privateKeySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
            var privateKey = KeyFactory.getInstance("RSA").generatePrivate(privateKeySpec);

            return new KeyPair(publicKey, privateKey);
        }

        static Integer getRandomKey(){
            return random.nextInt(3);
        }

        static PublicKey getPublicKey(SignKeyId keyId) {
            KeyPair keyPair = keyPairs.get(keyId.id());
            if (keyPair != null) {
                return keyPair.getPublic();
            } else {
                throw new IllegalArgumentException("Key ID not found");
            }
        }

        static PrivateKey getPrivateKey(SignKeyId keyId) {
            KeyPair keyPair = keyPairs.get(keyId.id());
            if (keyPair != null) {
                return keyPair.getPrivate();
            } else {
                throw new IllegalArgumentException("Key ID not found");
            }
        }
    }

}
