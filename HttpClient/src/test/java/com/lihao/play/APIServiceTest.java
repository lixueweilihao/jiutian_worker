package com.lihao.play;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class APIServiceTest {
    private APIService apiService;

    @Before
    public void init() {
        this.apiService = new APIService();
    }
    @Test
    public void testdoGet() throws Exception {
        String url = "http://10.49.130.41/common/consumer/getConsumeDetail.htm";
        HttpResult httpResult = this.apiService.doGet(url);
        System.out.println(httpResult.getResponseCode());
        System.out.println(httpResult.getResult());
    }

    @Test
    public void doGet1() {
    }

    @Test
    public void doPost() {
    }

    @Test
    public void doPost1() {
    }

    @Test
    public void doPut() {
    }

    @Test
    public void doDelete() {
    }
}