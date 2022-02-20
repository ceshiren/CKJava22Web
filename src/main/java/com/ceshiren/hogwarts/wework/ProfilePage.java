package com.ceshiren.hogwarts.wework;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProfilePage extends WeWorkPage {
    public ProfilePage(ChromeDriver driver) {
        //复用driver的本质是复用相同的浏览器自动化流程 driver=sessonid，不能重新初始化的
        this.driver = driver;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20), Duration.ofSeconds(2));
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("删除")));
    }

    public void update() {

    }

    public User get(){
        String name = driver.findElement(
                By.cssSelector(".member_display_cover_detail_name")).getText();
        String mobile=driver.findElement(
                By.cssSelector(".member_display_item_Phone .member_display_item_right")).getText();

        User user=new User();
        user.username=name;
        user.mobile=mobile;
        return user;
    }

    public void delete() {
        do {
            //js执行延迟 控件的状态切换 1.出现在dom中 2.可别看见css 3.可被点击 4.js事件绑定
            driver.findElement(By.linkText("删除")).click();
            System.out.println("click");
        } while (driver.findElements(By.linkText("确认")).size() <= 0);
        driver.findElement(By.linkText("确认")).click();
    }
}
