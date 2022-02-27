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
        currentElement = driver.findElement(by);
        return this;
    }


    public void click() {
        currentElement.click();
    }

    public void click(By by) {
        currentBy = by;
        driver.findElement(by).click();
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
}
