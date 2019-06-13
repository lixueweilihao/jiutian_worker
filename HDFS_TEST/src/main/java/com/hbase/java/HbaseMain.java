package com.hbase.java;

import java.io.IOException;

/**
 * Copyright @ 2018
 * All right reserved.
 *
 * @author Li Hao
 * @since 2018/12/21  16:21
 */
public class HbaseMain {
    public static void main(String[] args) throws IOException {
        HbaseMain hm = new HbaseMain();
        HbaseUtils hu = new HbaseUtils();
        hu.init();
        hu.insert();
    }
}

