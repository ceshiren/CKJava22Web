package com.ceshiren.hogwarts.wework.app;

import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

class ContactAppPageTest {

    @Test
    void addMember() throws MalformedURLException {
        WeWorkAppPage wework = new WeWorkAppPage();
        String mobile = generateMobile();
        String name = "ckjava22_" + mobile;

        UserProfile user1 = new UserProfile();
        user1.setMobile(mobile);
        user1.setName(name);

        UserProfile profile = wework.toContact().addMember(user1).searchMember(mobile).getProfile();
        assertThat(profile, equalTo(user1));
    }

    String generateMobile() {
        return "156" + String.valueOf(System.currentTimeMillis()).substring(5);
    }

}