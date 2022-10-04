package com.am.Virtue.Security;

import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.vault.authentication.TokenAuthentication;
import org.springframework.vault.client.VaultEndpoint;
import org.springframework.vault.core.VaultTemplate;

import java.net.URI;

public class VaultHandler {

    private static VaultHandler vaultHandler = null;

    private ApplicationContext applicationContext;

    private VaultHandler() {
    }

    public static VaultHandler getInstance(){
        if(vaultHandler == null){
            vaultHandler = new VaultHandler();
        }
        return vaultHandler;
    }

    public String decryptDatabaseKey(String value) {
        System.out.println("applicationContext: "+applicationContext);
        System.out.println("decryptDatabaseKey: "+value);
        try {
            String serviceUrlBase64 = this.applicationContext.getBean(Environment.class).getProperty("service_url");
            String serviceTokenBase64 = this.applicationContext.getBean(Environment.class).getProperty("service_token");
            String serviceKeyBase64 = this.applicationContext.getBean(Environment.class).getProperty("service_key");

            VaultEndpoint endpoint = VaultEndpoint.from(new URI(decodeBase64(serviceUrlBase64)));

            VaultTemplate vaultTemplate = new VaultTemplate(endpoint, new TokenAuthentication(decodeBase64(serviceTokenBase64)));

            String decryptedKey = vaultTemplate.opsForTransit().decrypt(decodeBase64(serviceKeyBase64),value);
            System.out.println("decryptedKey: "+decryptedKey);
            return decryptedKey;
        }catch (Exception ex){
            System.out.println("decrypt ex: "+ex.getMessage());
            ex.printStackTrace();
            return null;
        }
    }

    public String encryptDatabaseKey(String value) {
        System.out.println("applicationContext: "+this.applicationContext);
        System.out.println("encryptDatabaseKey: "+value);
        try {
            String serviceUrlBase64 = this.applicationContext.getBean(Environment.class).getProperty("service_url");
            String serviceTokenBase64 = this.applicationContext.getBean(Environment.class).getProperty("service_token");
            String serviceKeyBase64 = this.applicationContext.getBean(Environment.class).getProperty("service_key");

            VaultEndpoint endpoint = VaultEndpoint.from(new URI(decodeBase64(serviceUrlBase64)));

            VaultTemplate vaultTemplate = new VaultTemplate(endpoint, new TokenAuthentication(decodeBase64(serviceTokenBase64)));

            String encryptedKey = vaultTemplate.opsForTransit().encrypt(decodeBase64(serviceKeyBase64),value);
            System.out.println("encryptedKey: "+encryptedKey);
            return encryptedKey;
        }catch (Exception ex){
            System.out.println("encrypt ex: "+ex.getMessage());
            ex.printStackTrace();
            return null;
        }
    }

    private String decodeBase64(String encodedString){
        System.out.println("encodedString: "+encodedString);
        return encodedString;
//        byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
//        String decodedString = new String(decodedBytes);
//        return decodedString;
    }

    public void setApplicationContext(ApplicationContext applicationContext) {
        System.out.println("**setApplicationContext**: "+applicationContext);
        this.applicationContext = applicationContext;
    }
}
