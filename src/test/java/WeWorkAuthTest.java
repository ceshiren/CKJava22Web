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
        String url = driver.getCurrentUrl();
        //扫码过程
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1000));
        //一旦扫码完成，url会跳转，跳转后自动停止等待并执行后续的操作
        wait.until(webDriver -> !webDriver.getCurrentUrl().equals(url));

        Set<Cookie> cookies = driver.manage().getCookies();
        mapper.writeValue(new File("cookies.yaml"), cookies);
    }

    @Test
    void loadCookies() throws IOException {
        TypeReference<List<HashMap<String, Object>>> typeReference = new TypeReference<>() {
        };
        List<HashMap<String, Object>> cookies = mapper.readValue(new File("cookies.yaml"), typeReference);
        cookies.stream()
                .filter(cookie -> cookie.get("domain").toString().contains("work.weixin.qq.com"))
                .forEach(cookie -> {
                    driver.manage().addCookie(
                            new Cookie(cookie.get("name").toString(), cookie.get("value").toString()));
                });
        driver.navigate().refresh();

    }

}
