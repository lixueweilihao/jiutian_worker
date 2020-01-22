package com.ibort.service;

import com.ibort.api.HelloService;

/**
 * @author jeffy
 * @date 2018/6/15
 **/
public class HelloServiceImpl implements HelloService {

    /**
     * 实现api定义的接口
     * @param name
     * @return
     */
    public String sayHi(String name) {
        System.out.println("收到客户端请求，参数为：  " + name);
        return "Hi, " + name;
    }
}
