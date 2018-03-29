/*
 * Copyright © 2013-2018, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package org.seedstack.samples.ddd.application.services;

import org.seedstack.business.Service;
import org.seedstack.samples.ddd.domain.model.cargo.Cargo;
import org.seedstack.samples.ddd.domain.model.handling.HandlingEvent;

/**
 * This interface provides a way to let other parts of the system know about events that have occurred.
 * <p/>
 * It may be implemented synchronously or asynchronously, using for example JMS.
 */
@Service
public interface ApplicationEventService {

    /**
     * A cargo has been handled.
     *
     * @param event handling event
     */
    void cargoWasHandled(HandlingEvent event);

    /**
     * A cargo has been misdirected.
     *
     * @param cargo cargo
     */
    void cargoWasMisdirected(Cargo cargo);

    /**
     * A cargo has arrived at its final destination.
     *
     * @param cargo cargo
     */
    void cargoHasArrived(Cargo cargo);

    /**
     * A handling event regitration attempt is received.
     *
     * @param attempt handling event registration attempt
     */
    //void receivedHandlingEventRegistrationAttempt(HandlingEventRegistrationAttempt attempt);

}
