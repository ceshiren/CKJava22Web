package com.ceshiren.hogwarts.wework.app;

import io.appium.java_client.AppiumDriver;

public class MemberAppPage extends BaseAppPage{

    public MemberAppPage(AppiumDriver driver) {
        super(driver);
    }

    public UserProfile getProfile(){
        return new UserProfile();
    }
}
