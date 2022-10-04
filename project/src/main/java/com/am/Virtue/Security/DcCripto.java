package com.am.Virtue.Security;

import com.am.Virtue.Service.SystemConfigService;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;

public class DcCripto implements PasswordEncoder {

    private ApplicationContext applicationContext;
    private String dataBaseKey;

    public DcCripto(){
    }

    public String encrypt(String strToEncrypt) {

        System.out.println("*** encrypt ***: "+strToEncrypt);
        try {

            byte[] keyBytes16 = new byte[16];

            System.arraycopy(getEncryptionKey(), 0, keyBytes16, 0, Math.min(getEncryptionKey().length, 16));

            byte[] plainBytes = strToEncrypt.getBytes("UTF-8");

            SecretKeySpec skeySpec = new SecretKeySpec(keyBytes16, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, new IvParameterSpec(getIV()));

            return Base64.encodeBase64String(cipher.doFinal(plainBytes));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    public String decrypt(String strToDecrypt) {
        System.out.println("*** decrypt ***: "+strToDecrypt);
        try {
            byte[] keyBytes16 = new byte[16];
            System.arraycopy(getEncryptionKey(), 0, keyBytes16, 0, Math.min(getEncryptionKey().length, 16));

            SecretKeySpec skeySpec = new SecretKeySpec(keyBytes16, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, new IvParameterSpec(getIV()));

            return new String(cipher.doFinal(Base64.decodeBase64(strToDecrypt)), "UTF-8");

        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    }

    @Override
    public String encode(CharSequence rawPassword) {
        String password = String.valueOf(rawPassword);
        String encodedPassword = encrypt(password.trim());
        System.out.println("Method encode: "+encodedPassword);
        return encodedPassword;
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        String password = String.valueOf(rawPassword);

        System.out.println("matches: *************************");
        System.out.println("password: "+password);
        System.out.println("encodedPassword: "+encodedPassword);
        String passwordDecoded = decrypt(encodedPassword);
        System.out.println("passwordDecoded: "+passwordDecoded);
        if(passwordDecoded==null)
            passwordDecoded="";
        return (passwordDecoded.equals(password))? true : false;
    }

    public void setDataBaseKey(String dataBaseKey) {
        this.dataBaseKey = dataBaseKey;
    }

    private byte[] getEncryptionKey(){

        if(this.applicationContext == null){
            return null;
        }

        String databaseKey = this.applicationContext.getBean(SystemConfigService.class).findSystemConfigByKey((this.dataBaseKey)).getValue();

        VaultHandler vaultHandler = VaultHandler.getInstance();

        String encryptionKey = vaultHandler.decryptDatabaseKey(databaseKey);

        System.out.println("return enc key: "+encryptionKey);

        byte[] keyBytes = new byte[16];
        try {
            keyBytes = encryptionKey.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return keyBytes;
    }

    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public static byte[] getIV() throws UnsupportedEncodingException {
        return "0000000011111111".getBytes("UTF-8");
    }
}
