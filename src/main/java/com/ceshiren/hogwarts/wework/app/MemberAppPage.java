package com.ceshiren.hogwarts.wework.app;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class MemberAppPage extends BaseAppPage {

    public MemberAppPage(AppiumDriver driver) {
        super(driver);
    }

    public UserProfile getProfile() {
        UserProfile user = new UserProfile();
        user.setName(driver.findElement(By.id("jpy")).getText());
        user.setMail(driver.findElement(By.id("btd")).getText());

        return user;
    }
}
