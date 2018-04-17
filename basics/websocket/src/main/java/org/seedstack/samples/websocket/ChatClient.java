/*
 * Copyright © 2013-2018, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package org.seedstack.samples.websocket;

import java.util.function.Consumer;
import javax.websocket.ClientEndpoint;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import org.seedstack.seed.web.websocket.BaseClientEndpointConfigurator;

@ClientEndpoint(configurator = BaseClientEndpointConfigurator.class)
public class ChatClient {
    private Session session;
    private Consumer<String> handler;

    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
    }

    @OnMessage
    public void processMessage(String message) {
        if (handler != null) {
            handler.accept(message);
        }
    }

    public void setHandler(Consumer<String> handler) {
        this.handler = handler;
    }

    public void sendMessage(String message) {
        if (session == null) {
            throw new IllegalStateException("No active session");
        }

        session.getAsyncRemote().sendText(message);
    }
}