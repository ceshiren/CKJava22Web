package com.ceshiren.hogwarts.wework.app;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class ContactAppPage extends BaseAppPage{

    public ContactAppPage(AppiumDriver driver) {
        super(driver);
    }

    public MemberAppPage toMember(){
        return new MemberAppPage(driver);
    }
    public MemberAppPage searchMember(String pattern){
        driver.findElement(By.id("kci")).click();
        driver.findElement(By.cssSelector("android.widget.EditText")).sendKeys(pattern);
        driver.findElement(By.id("fkc")).click();
        return new MemberAppPage(driver);
    }
    public ContactAppPage addMember(UserProfile user){
        //todo: 研发写的id是不变的，但是经过混淆会变成随机id，混淆工具会提供原始id到最终随机id的一个算法
        // add_member -> bsm
        // getIdFromOriginId(String ){}
//        driver.findElement(By.id(getIdFromOriginId("manager"))).click();
        driver.findElement(By.id("kcc")).click();
        driver.findElement(By.cssSelector("*[text=添加成员]")).click();
        driver.findElement(By.cssSelector("*[text=手动输入添加]")).click();
        driver.findElement(By.id("bsm")).sendKeys(user.getName());
        driver.findElement(By.id("hgi")).sendKeys(user.getMobile());
        driver.findElement(By.cssSelector("*[text=保存]")).click();
        driver.findElement(By.cssSelector("*[text=手动输入添加]"));
        driver.navigate().back();
        driver.findElement(By.cssSelector("*[text=添加成员]"));
        driver.navigate().back();
        return this;
    }
}
