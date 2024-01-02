package com.ScreenSoundMusicas.ScreenSoundMusicas.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class GetKeyFromProperties {

    public String obterKeyValue (String keyName){

        Properties properties = new Properties();
        String fileName = "application-secret.properties";
        String keyValue = null;

        try (InputStream input = new FileInputStream(Thread.currentThread().getContextClassLoader().getResource("").getPath() + fileName)){

            if (input == null){
                System.out.println("Sorry, unable to find " + fileName);
            } else {
                properties.load(input);

                keyValue = properties.getProperty(keyName);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return keyValue;
    }
}
