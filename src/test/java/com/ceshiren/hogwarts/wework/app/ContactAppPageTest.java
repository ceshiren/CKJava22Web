package com.ceshiren.hogwarts.wework.app;

import org.junit.jupiter.api.*;

import java.net.MalformedURLException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

class ContactAppPageTest extends BaseWeWorkTestCase {
    @BeforeEach
    void beforeEach() {
        //单个用例的前置操作
    }

    @AfterEach
    void afterEach() {
        //单个用例的后置操作
    }

    @Test
    void addMember() throws Exception {

        String mobile = generateMobile();
        String name = "ckjava22_" + mobile;

        UserProfile user1 = new UserProfile();
        user1.setMobile(mobile);
        user1.setName(name);

        UserProfile profile = wework
                .toContact()
                .addMember(user1)
                .searchMember(mobile)
                .getProfile();
        assertThat(profile.name, equalTo(user1.name));
        assertThat(profile.mail, containsString(user1.name + "@"));
    }

    String generateMobile() {
        return "156" + String.valueOf(System.currentTimeMillis()).substring(5);
    }

}