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
import java.net.URL;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;
import javax.inject.Inject;
import javax.websocket.ContainerProvider;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class WebSocketIT {
    private final Queue<String> conversation = new ConcurrentLinkedQueue<>();
    @Inject
    private ChatClient chatClient1;
    @Inject
    private ChatClient chatClient2;
    @ArquillianResource
    private URL baseURL;
    private CountDownLatch countDownLatch1 = new CountDownLatch(1);
    private CountDownLatch countDownLatch2 = new CountDownLatch(1);

    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class);
    }

    @Test
    @RunAsClient
    public void testWebSocketCommunication() throws Exception {
        chatClient1.setHandler(message -> {
            conversation.add("client1: " + message);
            countDownLatch1.countDown();
        });
        connectToServer(chatClient1);

        chatClient2.setHandler(message -> {
            conversation.add("client2: " + message);
            countDownLatch2.countDown();
        });
        connectToServer(chatClient2);

        chatClient1.sendMessage("Hello World from client1!");
        countDownLatch2.await();
        assertThat(conversation).containsExactly("client2: Hello World from client1!");
        chatClient2.sendMessage("Hello World from client2!");
        countDownLatch1.await();
        assertThat(conversation).containsExactly("client2: Hello World from client1!",
                "client1: Hello World from client2!");
    }

    private Session connectToServer(Object endpoint) throws Exception {
        WebSocketContainer container = ContainerProvider.getWebSocketContainer();
        URI uri = new URI("ws://" + baseURL.getHost() + ":" + baseURL.getPort() + baseURL.getPath() + "chat");
        return container.connectToServer(endpoint, uri);
    }
}
