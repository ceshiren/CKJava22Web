package com.ceshiren.hogwarts.wework.app;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class WeWorkAppPage {

    private final AppiumDriver driver;

    public WeWorkAppPage() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.PLATFORM_NAME, "android");

        capabilities.setCapability("appPackage", "com.tencent.wework");
        capabilities.setCapability("appActivity", "com.tencent.wework.launch.LaunchSplashActivity");
        //不要重新数据化，保留登录信息
        capabilities.setCapability("noReset", true);
        driver = new AppiumDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

    }

    public ContactAppPage toContact() throws MalformedURLException {

//        driver.findElement(By.xpath("//*[@text='通讯录']")).click();
        driver.findElement(By.cssSelector("*[text=通讯录]")).click();

        return new ContactAppPage(driver);
    }
}
