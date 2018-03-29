/*
 * Copyright © 2013-2018, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package org.seedstack.samples.ws.domain.services;

import org.seedstack.business.Service;

/**
 * ICalculatorService provides a basic calculator service.
 */
@Service
public interface CalculationService {

    /**
     * Adds the number one to the number two.
     *
     * @param one the first number
     * @param two the second number
     * @return result
     */
    int add(int one, int two);

    /**
     * Substrates the number two to number one.
     *
     * @param one the first number
     * @param two the second number
     * @return result
     */
    int minus(int one, int two);

}