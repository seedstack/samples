/*
 * Copyright © 2013-2018, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package org.seedstack.samples.ws.ws.calculator;

import javax.inject.Inject;
import javax.jws.WebService;
import org.seedstack.samples.ws.calculator.CalculatorPortType;
import org.seedstack.samples.ws.calculator.NumberFormat;
import org.seedstack.samples.ws.calculator.NumberFormat_Exception;
import org.seedstack.samples.ws.domain.services.CalculationService;

@WebService(
        endpointInterface = "org.seedstack.samples.ws.calculator.CalculatorPortType",
        targetNamespace = "http://seedstack.org/samples/ws/calculator",
        serviceName = "CalculatorService",
        portName = "CalculatorUsernameTokenPort"
)
public class CalculatorUsernameTokenImpl implements CalculatorPortType {
    @Inject
    private CalculationService calculator;

    @Override
    public int add(int numberOne, int numberTwo) throws NumberFormat_Exception {
        if (numberOne < 0 || numberTwo < 0) {
            throw new NumberFormat_Exception("The calculator service can only sum positive numbers",
                    new NumberFormat());
        }
        return calculator.add(numberOne, numberTwo);
    }

    @Override
    public int minus(int numberOne, int numberTwo) {
        return calculator.minus(numberOne, numberTwo);
    }
}
