package com.hadoop.com.rpc.login;

public interface LoginServiceInterface {
     long versionID=1L;
     String login(String username,String passwd);
}
