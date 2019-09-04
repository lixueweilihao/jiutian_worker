package com.modify.flume.sink;

import com.google.common.collect.Lists;
import org.apache.flume.*;
import org.apache.flume.conf.Configurable;
import org.apache.flume.sink.AbstractSink;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.*;
import java.util.List;
import java.util.Objects;

/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

/**
 * Created on 2019-08-29
 *
 * @author :hao.li
 */
public class UDPEventSink extends AbstractSink implements Configurable {
    private static final Logger LOG = LoggerFactory.getLogger(UDPEventSink.class);
    private static final String defaultTargetIp = "localhost";
    private static final int defaultTargetPort = 514;
    private static final int defaultBatchSize = 100;

    private String targetIp = null;
    private int targetPort = 0;
    private int batchSize = 0;
    private DatagramSocket ds;
    private DatagramPacket dp;

    public UDPEventSink() throws SocketException {
        ds = new DatagramSocket();
    }

    public void start() {
        super.start();
    }

    public void stop() {
        super.stop();
    }

    @Override
    public Status process() throws EventDeliveryException {
        Status result = Status.READY;
        Channel ch = getChannel();
        Transaction transaction = ch.getTransaction();
        Event event = null;
        List<byte[]> infos = Lists.newArrayList();
        transaction.begin();
        try {
            int txnEventCount = 0;
            for (txnEventCount = 0; txnEventCount < batchSize; txnEventCount++) {
                event = ch.take();
                if (event == null) {
                    break;
                }
                byte[] body = event.getBody();
                infos.add(body);
            }

            if (infos.size() > 0) {
                for (byte[] by : infos) {
                    dp = new DatagramPacket(by, by.length, InetAddress.getByName(targetIp), targetPort);
                    ds.send(dp);
                }
            }
            transaction.commit();
            if (txnEventCount < 1) {
                return Status.BACKOFF;
            }
            return result;
        } catch (UnknownHostException e1) {
            LOG.error("Target Ip is missing,and the result is{}.", e1.getMessage());
            return Status.BACKOFF;
        } catch (IOException e2) {
            transaction.rollback();
            LOG.warn("OPENTSDB IO error,reason is {}.", e2.getMessage());
            return Status.BACKOFF;
        } catch (Throwable th) {
            transaction.rollback();
            LOG.error("process failed", th);
            if (th instanceof Error) {
                throw (Error) th;
            } else {
                throw new EventDeliveryException(th);
            }
        } finally {
            if (transaction != null) {
                transaction.close();
            }
        }
    }


    @Override
    public void configure(Context context) {
        targetIp = context.getString("udp.targetIp", defaultTargetIp);
        targetPort = context.getInteger("udp.targetPort", defaultTargetPort);
        batchSize = context.getInteger("udp.batchSize", defaultBatchSize);
        Objects.requireNonNull(targetIp, "targetIp");
        Objects.requireNonNull(targetPort, "targetPort");
        Objects.requireNonNull(batchSize, "batchSize");
    }
}
