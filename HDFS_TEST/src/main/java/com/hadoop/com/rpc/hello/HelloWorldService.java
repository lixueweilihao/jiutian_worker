package com.hadoop.com.rpc.hello;

import org.apache.hadoop.ipc.VersionedProtocol;

public interface HelloWorldService extends VersionedProtocol {
    long versionID = 1;
    String sayHello(String msg);
}
