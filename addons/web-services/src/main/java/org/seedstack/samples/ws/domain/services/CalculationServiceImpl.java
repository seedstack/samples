/*
 * Copyright © 2013-2018, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package org.seedstack.samples.ws.domain.services;

public class CalculationServiceImpl implements CalculationService {

    @Override
    public int add(int one, int two) {
        return one + two;
    }

    @Override
    public int minus(int one, int two) {
        return one - two;
    }

}
