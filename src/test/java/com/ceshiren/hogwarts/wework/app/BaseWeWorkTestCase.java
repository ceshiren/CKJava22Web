package com.ceshiren.hogwarts.wework.app;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import java.net.MalformedURLException;

public class BaseWeWorkTestCase {

    static WeWorkAppPage wework;

    @BeforeAll
    static void beforeAll() throws MalformedURLException {
        //todo: 环境还原
        //还原方式
        //保证数据的唯一性
        //UI自动化
        //接口自动化
        //数据库还原

        wework = new WeWorkAppPage();

    }

    @AfterAll
    static void afterAll() {
        //如果进程被非正常中止，afterAll可能得不到执行
        wework.close();
    }
}
