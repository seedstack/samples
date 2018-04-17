/*
 * Copyright © 2013-2018, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package org.seedstack.samples.ws;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

import javax.inject.Inject;
import javax.xml.ws.BindingProvider;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.seedstack.samples.ws.calculator.CalculatorPortType;
import org.seedstack.samples.ws.calculator.CalculatorService;
import org.seedstack.samples.ws.product.BadProductRequestException;
import org.seedstack.samples.ws.product.ProductInfoPortType;
import org.seedstack.samples.ws.product.ProductInfoService;
import org.seedstack.seed.Configuration;
import org.seedstack.seed.testing.junit4.internal.JUnit4Runner;
import org.seedstack.seed.undertow.LaunchWithUndertow;

@RunWith(JUnit4Runner.class)
@LaunchWithUndertow
public class WebServiceIT {
    @Inject
    private CalculatorService calculatorService;
    @Inject
    private ProductInfoService productInfoService;
    @Configuration("web.runtime.baseUrl")
    private String baseUrl;

    @Test
    public void testSimple() throws Exception {
        ProductInfoPortType productInfoPort = productInfoService.getProductInfoPort();
        ((BindingProvider) productInfoPort).getRequestContext()
                .put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, baseUrl + "product-info");

        assertThat(productInfoPort.productInfoById(1).getDesignation()).isEqualTo("Product #1");
    }

    @Test(expected = BadProductRequestException.class)
    public void testSimpleWithException() throws Exception {
        ProductInfoPortType productInfoPort = productInfoService.getProductInfoPort();
        ((BindingProvider) productInfoPort).getRequestContext()
                .put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, baseUrl + "product-info");

        productInfoPort.productInfoById(-1);

        fail("should have failed");
    }

    @Test
    public void testUsernameToken() throws Exception {
        CalculatorPortType calculatorPort = calculatorService.getCalculatorUsernameTokenPort();
        ((BindingProvider) calculatorPort).getRequestContext()
                .put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, baseUrl + "calculator-username-token");
        // User name and password below will be used for username token policy.
        // You can omit them if you have a custom handler configured in the wsit-client.xml file.
        ((BindingProvider) calculatorPort).getRequestContext().put(BindingProvider.USERNAME_PROPERTY, "demo");
        ((BindingProvider) calculatorPort).getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, "demo");

        assertThat(calculatorPort.add(1, 1)).isEqualTo(2);
    }

    @Test
    public void testCertificate() throws Exception {
        CalculatorPortType calculatorPort = calculatorService.getCalculatorCertificatePort();
        ((BindingProvider) calculatorPort).getRequestContext()
                .put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, baseUrl + "calculator-certificate");

        assertThat(calculatorPort.add(1, 1)).isEqualTo(2);
    }
}
