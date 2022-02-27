package com.ceshiren.hogwarts.wework.app;

import io.appium.java_client.AppiumDriver;

public class BaseAppPage {
    AppiumDriver driver;

    public BaseAppPage(AppiumDriver driver) {
        this.driver = driver;
    }

    public String getIdFromOriginId(String originId){
        String randomId="";
        return randomId;
    }
}
