package com.ceshiren.hogwarts.wework.app;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class BaseAppPage {
    AppiumDriver driver;
    By currentBy;
    WebElement currentElement;

    public BaseAppPage(AppiumDriver driver) {
        this.driver = driver;
    }

    public String getIdFromOriginId(String originId) {
        String randomId = "";
        return randomId;
    }

    public BaseAppPage find(String selector) throws Exception {
        find(selector, "css");
        return this;
    }

    public BaseAppPage find(By by) throws Exception {
        try {
            currentElement = driver.findElement(by);
        } catch (Exception e) {
            handleExceptions();
            //使用递归解决多次异常处理问题
            find(by);
        }
        return this;
    }


    /**
     * find 代表对控件的定位
     *
     * @param selector
     * @param strategy
     * @return
     * @throws Exception
     */
    public BaseAppPage find(String selector, String strategy) throws Exception {
        By by;
        if (strategy.equals("id")) {
            by = By.id(selector);
        } else if (strategy.equals("css")) {
            by = By.cssSelector(selector);
        } else {
            throw new Exception("unknown");
        }
        currentBy = by;
        find(by);

        return this;
    }


    public void click() {
        try {
            currentElement.click();
        } catch (Exception e) {
            handleExceptions();
            click();
        }
    }

    public void click(By by) throws Exception {
        try {
            find(by).click();
        } catch (Exception e) {
            handleExceptions();
            click(by);
        }
    }

    public void click(String selector, String strategy) throws Exception {
        find(selector, strategy);
        click();
    }

    public void click(String selector) throws Exception {
        find(selector).click();
    }

    public void sendKeys(By by, String text) {
        currentBy = by;
        driver.findElement(by).sendKeys(text);
    }

    public void sendKeys(String text) {
        currentElement.sendKeys(text);
    }

    public String getAttribute(String name) {
        return currentElement.getAttribute(name);
    }

    public String getText() {
        return currentElement.getText();
    }

    /**
     * 无控件上下文的action
     */
    public void back() {
        driver.navigate().back();
    }

    public void handleExceptions(){
        //todo: 解决弹框和各种异常
        //异常类型
        //1. 行为异常，重试机制可也解决
        //2. po实现，重试机制通常效果不大
        //3. 全流程与环境异常 直接fail并自动启动下次执行
    }
}
