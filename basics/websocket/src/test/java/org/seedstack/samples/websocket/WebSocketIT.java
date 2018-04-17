/*
 * Copyright © 2013-2018, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package org.seedstack.samples.websocket;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URI;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import javax.websocket.ContainerProvider;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.seedstack.seed.Configuration;
import org.seedstack.seed.testing.junit4.internal.JUnit4Runner;
import org.seedstack.seed.undertow.LaunchWithUndertow;

@RunWith(JUnit4Runner.class)
@LaunchWithUndertow
public class WebSocketIT {
    private final Queue<String> conversation = new ConcurrentLinkedQueue<>();
    @Inject
    private ChatClient chatClient1;
    @Inject
    private ChatClient chatClient2;
    @Configuration("web.runtime.contextPath")
    private String contextPath;
    @Configuration("web.runtime.host")
    private String host;
    @Configuration("web.runtime.port")
    private int port;
    private CountDownLatch countDownLatch1 = new CountDownLatch(1);
    private CountDownLatch countDownLatch2 = new CountDownLatch(1);

    @Test
    public void testWebSocketCommunication() throws Exception {
        chatClient1.setHandler(message -> {
            conversation.add("client1: " + message);
            countDownLatch1.countDown();
        });
        Session session1 = openSession(chatClient1);

        chatClient2.setHandler(message -> {
            conversation.add("client2: " + message);
            countDownLatch2.countDown();
        });
        Session session2 = openSession(chatClient2);

        chatClient1.sendMessage("Hello World from client1!");
        countDownLatch2.await(2, TimeUnit.SECONDS);
        assertThat(conversation).containsExactly("client2: Hello World from client1!");
        chatClient2.sendMessage("Hello World from client2!");
        countDownLatch1.await(2, TimeUnit.SECONDS);
        assertThat(conversation).containsExactly("client2: Hello World from client1!",
                "client1: Hello World from client2!");

        session1.close();
        session2.close();
    }

    private Session openSession(Object endpoint) throws Exception {
        WebSocketContainer container = ContainerProvider.getWebSocketContainer();
        return container.connectToServer(endpoint, new URI(
                "ws://"
                        + host
                        + ":"
                        + port
                        + contextPath
                        + "/chat"));
    }
}
