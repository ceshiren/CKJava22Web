package com.ceshiren.hogwarts.wework;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

public class WeWorkPage {
    ChromeDriver driver;
    private final ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
    WebDriverWait wait;

    public WeWorkPage login() throws IOException {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait = new WebDriverWait(driver, Duration.ofSeconds(20), Duration.ofSeconds(2));
        driver.get("https://work.weixin.qq.com/wework_admin/frame#index");

        //声明要赌球的数据的类型
        TypeReference<List<HashMap<String, Object>>> typeReference = new TypeReference<>() {
        };
        //读取数据并存到合适的类型里
        List<HashMap<String, Object>> cookies = mapper.readValue(new File("cookies.yaml"), typeReference);
        //java8
        cookies.stream()
                //过滤企业微信的cookie
                .filter(cookie -> cookie.get("domain").toString().contains("work.weixin.qq.com"))
                .forEach(cookie -> {
                    //写cookie到浏览器
                    driver.manage().addCookie(
                            new Cookie(cookie.get("name").toString(), cookie.get("value").toString()));
                });
        //刷新的时候，浏览器会把新的cookie带到服务器，服务器返回登录后的页面
        driver.navigate().refresh();
        return this;
    }

    public void close(){
//        driver.quit();
    }
}
