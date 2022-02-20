package com.ceshiren.hogwarts.wework;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class ContactPageTest {

    private final ContactPage contactPage = new ContactPage();

    @AfterEach
    public void afterAll(){
        contactPage.close();
    }

    @Test
    void addMember() throws IOException {
        contactPage.login();
        contactPage.open();
        contactPage.addMember("202202201533", "202202201533", "18002201533" );
        contactPage.getMemeber("202202201533").deleteCurrentMember();
//        assertTrue(user.contains("18002201533"));
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