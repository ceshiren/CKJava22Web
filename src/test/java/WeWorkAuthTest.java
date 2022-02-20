import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class WeWorkAuthTest {

    private static ChromeDriver driver;
    private final ObjectMapper mapper = new ObjectMapper(new YAMLFactory());

    @BeforeAll
    static void beforeAll() {
        driver = new ChromeDriver();
        driver.get("https://work.weixin.qq.com/wework_admin/frame#index");
    }

    @Test
    void saveCookies() throws IOException {
        //打开浏览器后网址可能会有跳转，获取最新的url地址
        String url = driver.getCurrentUrl();
        //扫码过程
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1000));
        //一旦扫码完成，url会跳转，跳转后自动停止等待并执行后续的操作
        wait.until(webDriver -> !webDriver.getCurrentUrl().equals(url));

        //扫码完成后保存cookies，因为cookies里有服务器存储的sessionid
        Set<Cookie> cookies = driver.manage().getCookies();
        mapper.writeValue(new File("cookies.yaml"), cookies);
    }

    @Test
    void loadCookies() throws IOException {
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

    }

}
