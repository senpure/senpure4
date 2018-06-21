package com.senpure.base.util;


import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;


public class Rsa {


    private static Logger logger = LoggerFactory.getLogger(Rsa.class);


    public final static String ALGORITHM = "RSA";
    // private static String CHIPER="RSA/ECB/NoPadding";
    //  private static String CHIPER="rsa";
    public final static String CIPHER_ECB = "RSA/ECB/NoPadding";
    public final static String CIPHER_NONE = "RSA";
    private int keySize = 2048;
    private int encode_len = keySize / 8 - 11;
    private int decode_len = keySize / 8;


    private String cipher = "RSA";

    public Rsa() {
        this(2048, CIPHER_NONE);
    }

    public Rsa(int keySize, String cipher) {
        this.keySize = keySize;
        encode_len = keySize / 8 - 11;
        decode_len = keySize / 8;
        this.cipher = cipher;
    }

    public Map<String, String> generatorRsaKey(String seed) {

        KeyPairGenerator keyPairGenerator = null;
        try {
            keyPairGenerator = KeyPairGenerator.getInstance(ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            logger.debug("error ", e);
        }

        SecureRandom secureRandom = new SecureRandom(seed.getBytes());
        keyPairGenerator.initialize(keySize, secureRandom);
        KeyPair keyPair = keyPairGenerator.genKeyPair();

        //甲方公钥
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        //甲方私钥
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        Map<String, String> map = new HashMap();
        String priKey = Base64.encode(privateKey.getEncoded());
        String pubKey = Base64.encode(publicKey.getEncoded());

        map.put("privateKey", priKey);
        map.put("publicKey", pubKey);
        return map;
    }

    public static String getRsaPrivateKey(Map<String, String> map) {
        return map.get("privateKey");
    }

    public static String getRsaPublicKey(Map<String, String> map) {

        return map.get("publicKey");
    }


    private PrivateKey getPrivateKey(String key) {
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(Base64.decode(key));
        PrivateKey privateKey = null;
        try {
            KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
            privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return privateKey;
    }

    private PublicKey getPublicKey(String key) {

        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(Base64.decode(key));
        PublicKey publicKey = null;
        try {
            KeyFactory factory = KeyFactory.getInstance(ALGORITHM);
            publicKey = factory.generatePublic(x509EncodedKeySpec);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return publicKey;
    }

    /**
     * 加密
     *
     * @return
     */
    public String rsaEncryptBase64ByPrivateKey(String content, String key) {
        byte[]
                bytes = rsaEncrypt(content, getPrivateKey(key));

        if (bytes == null) {
            return null;

        }
        return Base64.encode(bytes);
    }

    public String rsaEncryptBase64ByPublicKey(String content, String key) {
        byte[]
                bytes = rsaEncrypt(content, getPublicKey(key));
        if (bytes == null) {
            return null;
        }
        return Base64.encode(bytes);
    }

    public byte[] rsaEncrypt(String content, Key key) {
        try {
            Cipher cipher = Cipher.getInstance(this.cipher);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] bytes = content.getBytes("utf-8");
            if (bytes.length <= encode_len) {
                return cipher.doFinal(bytes);
            } else {
                byte[] encode = new byte[]{};
                for (int i = 0; i < bytes.length; i += encode_len) {
                    byte[] subarray = ArrayUtils.subarray(bytes, i, i + encode_len);
                    encode = ArrayUtils.addAll(encode, cipher.doFinal(subarray));
                }
                return encode;
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解密
     *
     * @return
     */
    public String rsaDecryptBase64ByPublicKey(String encrypted, String key) {

        byte[] bytes = Base64.decode(encrypted);
        return rsaDecrypt(bytes, getPublicKey(key));

    }

    public String rsaDecryptBase64ByPrivateKey(String encrypted, String key) {

        byte[] bytes = Base64.decode(encrypted);
        return rsaDecrypt(bytes, getPrivateKey(key));
    }

    private String rsaDecrypt(byte[] bytes, Key key) {
        try {
            Cipher cipher = Cipher.getInstance(this.cipher);
            cipher.init(Cipher.DECRYPT_MODE, key);
            if (bytes.length <= decode_len) {
                byte[] data = cipher.doFinal(bytes);
                return new String(data, "utf-8");
            } else {
                byte[] data = new byte[]{};
                for (int i = 0; i < bytes.length; i += decode_len) {
                    byte[] subarray = ArrayUtils.subarray(bytes, i, i + decode_len);
                    //  logger.debug("{}", i);
                    data = ArrayUtils.addAll(data, cipher.doFinal(subarray));
                }
                return new String(data, "utf-8");
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return null;
    }


    public void rasShow(String seed) {
        Map<String, String> map = generatorRsaKey(seed);
        logger.debug("privateKey {}", getRsaPrivateKey(map));
        logger.debug("publicKey {}", getRsaPublicKey(map));
        String content = "测试一下rsa加密可以是否可用，hello world";
        logger.debug("str:       {}", content);
        String encrypted = rsaEncryptBase64ByPrivateKey(content, getRsaPrivateKey(map));
        logger.debug("私钥加密后 {}", encrypted);
        logger.debug(Base64.decode(encrypted).length + "");
        logger.debug("公钥解密后 {}", rsaDecryptBase64ByPublicKey(encrypted, getRsaPublicKey(map)));
        encrypted = rsaEncryptBase64ByPublicKey(content, getRsaPublicKey(map));
        logger.debug("公钥加密后 {}", encrypted);
        logger.debug(Base64.decode(encrypted).length + "");
        logger.debug("私钥解密后 {}", rsaDecryptBase64ByPrivateKey(encrypted, getRsaPrivateKey(map)));

    }

    public static void main(String[] args) {

        Rsa rsa = new Rsa(1024, CIPHER_NONE);

        rsa.rasShow("1024");
        // rasShow();
    }
}
