/*
 * Copyright © 2013-2018, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package org.seedstack.samples.ddd.domain.model.cargo;

import org.seedstack.business.domain.ValueObject;

/**
 * Routing status.
 */
public enum RoutingStatus implements ValueObject {
    NOT_ROUTED, ROUTED, MISROUTED
}
