package org.example;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWEObject;
import com.nimbusds.jose.crypto.RSADecrypter;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.KeyUse;
import com.nimbusds.jose.jwk.RSAKey;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.text.ParseException;
import java.util.UUID;

/**
 * Created on 2/20/2020.
 *
 * @author schancay
 * @since 2/20/2020
 */
public class App {
    private static final Logger LOGGER = LogManager.getLogger(App.class);

    protected static PrivateKey readPrivateKey(File fileKey)
            throws NoSuchAlgorithmException, IOException, InvalidKeySpecException {
        LOGGER.info("Reading file [{}]", fileKey.getAbsoluteFile());

        try {
            byte[] keyBytes = Files.readAllBytes(Paths.get(fileKey.getAbsolutePath()));
            PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
            KeyFactory kf = KeyFactory.getInstance("RSA");
            return kf.generatePrivate(spec);
        } catch (Exception e) {
            LOGGER.error("Error loading private key file", e);
            throw e;
        }
    }

    protected static PublicKey readPublicKey(File fileKey)
            throws NoSuchAlgorithmException, IOException, InvalidKeySpecException {
        LOGGER.info("Reading file [{}]", fileKey.getAbsoluteFile());

        try {
            byte[] keyBytes = Files.readAllBytes(Paths.get(fileKey.getAbsolutePath()));
            X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
            KeyFactory kf = KeyFactory.getInstance("RSA");
            return kf.generatePublic(spec);
        } catch (Exception e) {
            LOGGER.error("Error loading public key file", e);
            throw e;
        }
    }

    protected static String decrypt(File privateKeyFile, String data) throws InvalidKeySpecException, NoSuchAlgorithmException, JOSEException, ParseException, IOException {
        LOGGER.info("Start decryption data");

        String dataDecrypted;
        try {
            if (StringUtils.isEmpty(data)) {
                dataDecrypted = "";
            } else {
                JWEObject jweObject = JWEObject.parse(data);
                PrivateKey privateKey = readPrivateKey(privateKeyFile);
                jweObject.decrypt(new RSADecrypter(privateKey));
                dataDecrypted = jweObject.getPayload().toString();
            }
        } catch (Exception e) {
            LOGGER.error("Invalid private key", e);
            throw e;
        }

        LOGGER.info("Success decryption data");
        return dataDecrypted;
    }

    public static void main(String[] args) throws NoSuchAlgorithmException, IOException, InvalidKeySpecException {
        LOGGER.info("Start app");

        File filePrivateKey = new File("../keys/private_key.der");
        File filePublicKey = new File("../keys/public_key.der");

        LOGGER.info("Private key: {}", filePrivateKey.getAbsolutePath());
        LOGGER.info("Public key: {}", filePublicKey.getAbsolutePath());

        PrivateKey privateKey = readPrivateKey(filePrivateKey);
        PublicKey publicKey = readPublicKey(filePublicKey);

        KeyPairGenerator gen = KeyPairGenerator.getInstance("RSA");
        gen.initialize(2048);

        RSAPublicKey rsaPublicKey = (RSAPublicKey) publicKey;
        RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) privateKey;

        JWK jwkRsa = new RSAKey.Builder(rsaPublicKey).privateKey(rsaPrivateKey).keyUse(KeyUse.SIGNATURE)
                .keyID(UUID.randomUUID().toString()).build();

        LOGGER.info("JWK RSA: \n\n{}\n", jwkRsa);
    }
}
