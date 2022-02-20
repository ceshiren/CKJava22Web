package com.ceshiren.hogwarts.wework;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.IOException;

public class ContactPage extends WeWorkPage {
    public ContactPage() {

    }

    public ContactPage open() {
        driver.get("https://work.weixin.qq.com/wework_admin/frame#contacts");
        return this;
    }

    @Override
    public ContactPage login() throws IOException {
        super.login();
        return this;
    }

    public ContactPage addMember(String acctid, String name, String mobile) {
        By addMemberButton = By.linkText("添加成员");
        wait.until(ExpectedConditions.visibilityOfElementLocated(addMemberButton));
        driver.findElement(addMemberButton).click();
        driver.findElement(By.name("username")).sendKeys(name);
        driver.findElement(By.name("acctid")).sendKeys(acctid);
        driver.findElement(By.name("biz_mail")).sendKeys(String.valueOf(System.currentTimeMillis()));
        driver.findElement(By.name("mobile")).sendKeys(mobile);
        driver.findElement(By.linkText("保存")).click();
        driver.navigate().refresh();
        return this;
    }

    public ProfilePage searchMemeber(String account) {
        By memberSearchInput = By.id("memberSearchInput");
        driver.findElement(memberSearchInput).sendKeys(account);
        //todo:
        return new ProfilePage(driver);
    }

    public void updateMember(String account, String name, String phone) {

    }
}
