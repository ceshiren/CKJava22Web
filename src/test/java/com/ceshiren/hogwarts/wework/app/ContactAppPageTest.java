package com.ceshiren.hogwarts.wework.app;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ContactAppPageTest {

    @Test
    void addMember() {
        WeWorkAppPage wework=new WeWorkAppPage();
        UserProfile profile = wework.toContact().addMember(null).searchMember("").getProfile();
        assertThat(profile, null);
    }
}