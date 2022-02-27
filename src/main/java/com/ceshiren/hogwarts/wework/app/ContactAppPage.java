package com.ceshiren.hogwarts.wework.app;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class ContactAppPage {
    AppiumDriver driver;
    public ContactAppPage(AppiumDriver driver) {
        this.driver=driver;
    }

    public MemberAppPage toMember(){
        return new MemberAppPage();
    }
    public MemberAppPage searchMember(String pattern){
        return new MemberAppPage();
    }
    public ContactAppPage addMember(UserProfile user){
        driver.findElement(By.id("kcc")).click();
        driver.findElement(By.cssSelector("*[text=添加成员]")).click();
        driver.findElement(By.cssSelector("*[text=手动输入添加]")).click();
        driver.findElement(By.id("bsm")).sendKeys(user.getName());
        driver.findElement(By.id("hgi")).sendKeys(user.getMobile());
        driver.findElement(By.cssSelector("*[text=保存]")).click();
        driver.navigate().back();
        driver.navigate().back();
        return this;
    }
}
