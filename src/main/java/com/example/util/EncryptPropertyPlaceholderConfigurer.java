package com.example.util;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

public class EncryptPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {
    private String[] encryptPropNames = {"jdbc.username", "jdbc.password"};

    @Override
    protected String convertProperty(String propertyName, String propertyValue) {
        if (isEncryptProp(propertyName)) {
            String ans = DESUtil.getDecryptString(propertyValue);
            return ans;
        } else {
            return propertyValue;
        }
    }

    private boolean isEncryptProp(String propertyName) {
        for (String name: encryptPropNames) {
            if (name.equals(propertyName)) {
                return true;
            }
        }
        return false;
    }
}
