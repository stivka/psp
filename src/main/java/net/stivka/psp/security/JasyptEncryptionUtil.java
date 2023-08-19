package net.stivka.psp.security;

import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;

public class JasyptEncryptionUtil {
    
    // Run this util with the command
    // java JasyptEncryptionUtil yourJasyptPassword textToEncrypt

    public static void main(String[] args) {
        // if (args.length != 2) {
        //     System.out.println("Usage: java JasyptEncryptionUtil <encryptor_password> <text_to_encrypt>");
        //     return;
        // }

        // String encryptorPassword = args[0];
        // String textToEncrypt = args[1];
        String encryptorPassword = "";
        String textToEncrypt = "";git

        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        
        config.setPassword(encryptorPassword);
        config.setAlgorithm("PBEWITHMD5ANDDES");
        config.setKeyObtentionIterations("1000");
        config.setPoolSize("1");
        config.setProviderName("SunJCE");
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
        config.setIvGeneratorClassName("org.jasypt.iv.RandomIvGenerator");
        config.setStringOutputType("base64");
        
        encryptor.setConfig(config);
        
        String encryptedText = encryptor.encrypt(textToEncrypt);
        
        System.out.println("Encrypted Text: " + encryptedText);
    }
}