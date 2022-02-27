package com.ceshiren.hogwarts.wework.web;

import com.ceshiren.hogwarts.wework.web.ContactPage;
import com.ceshiren.hogwarts.wework.web.ProfilePage;
import com.ceshiren.hogwarts.wework.web.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ContactPageTest {

    private final ContactPage contactPage = new ContactPage();

    @AfterEach
    public void afterAll() {
        contactPage.close();
    }

    @Test
    void addMember() throws IOException {
        contactPage.login();
        contactPage.open();
        contactPage.addMember("202202201533", "202202201533", "18002201533");
        ProfilePage profile = contactPage.searchMemeber("202202201533");
        User user = profile.get();
//        User user = contactPage.searchMemeber("202202201533").get();
        assertEquals(user.username, "202202201533");
        assertEquals(user.mobile, "18002201533");
        profile.delete();
//        assertTrue(user.contains("18002201533"));
    }

    @Test
    void addMemberLink() throws IOException {
        User user = contactPage
                .login()
                .open()
                .addMember("202202201533", "202202201533", "18002201533")
                .searchMemeber("202202201533").get();
        assertEquals(user.username, "202202201533");
        assertEquals(user.mobile, "18002201533");
        assertThat(user.mobile, containsString("1533"));
    }

    @Test
    void deleteMember() {
    }

    @Test
    void getMemeber() {

    }

    @Test
    void updateMember() {
    }
}