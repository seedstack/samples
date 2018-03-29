/**
 * Copyright (c) 2013-2016, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.seedstack.samples.seed.websocket;

import javax.inject.Singleton;
import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Predicate;

@ServerEndpoint(value = "/chat")
@Singleton
public class ChatServer {
    private final Set<Session> sessions = Collections.newSetFromMap(new ConcurrentHashMap<>());

    @OnOpen
    public void onOpen(Session session) {
        session.addMessageHandler(String.class, message ->
                sessions.stream()
                        .filter(Predicate.isEqual(session).negate())
                        .forEach(aSession -> aSession.getAsyncRemote().sendText(message))
        );
        sessions.add(session);
    }

    @OnClose
    public void onClose(Session session) {
        sessions.remove(session);
        session.getMessageHandlers().stream().forEach(session::removeMessageHandler);
    }
}