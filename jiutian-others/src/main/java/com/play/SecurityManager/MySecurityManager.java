package com.play.SecurityManager;


public class MySecurityManager extends SecurityManager {

    @Override
    public void checkRead(String file) {
        //super.checkRead(file, context);
        if (file.endsWith("kafka"))
            throw new SecurityException("你没有读取的本文件的权限");
    }

}
