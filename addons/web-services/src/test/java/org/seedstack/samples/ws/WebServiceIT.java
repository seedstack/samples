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

import java.net.URL;
import javax.inject.Inject;
import javax.xml.ws.BindingProvider;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.seedstack.samples.ws.calculator.CalculatorPortType;
import org.seedstack.samples.ws.calculator.CalculatorService;
import org.seedstack.samples.ws.product.BadProductRequestException;
import org.seedstack.samples.ws.product.ProductInfoPortType;
import org.seedstack.samples.ws.product.ProductInfoService;
import org.seedstack.seed.it.AbstractSeedWebIT;

public class WebServiceIT extends AbstractSeedWebIT {
    @Inject
    private CalculatorService calculatorService;
    @Inject
    private ProductInfoService productInfoService;

    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class);
    }

    @Test
    @RunAsClient
    public void testSimple(@ArquillianResource URL baseURL) throws Exception {
        ProductInfoPortType productInfoPort = productInfoService.getProductInfoPort();
        ((BindingProvider) productInfoPort).getRequestContext()
                .put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, baseURL + "product-info");

        assertThat(productInfoPort.productInfoById(1).getDesignation()).isEqualTo("Product #1");
    }

    @Test(expected = BadProductRequestException.class)
    @RunAsClient
    public void testSimpleWithException(@ArquillianResource URL baseURL) throws Exception {
        ProductInfoPortType productInfoPort = productInfoService.getProductInfoPort();
        ((BindingProvider) productInfoPort).getRequestContext()
                .put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, baseURL + "product-info");

        productInfoPort.productInfoById(-1);

        fail("should have failed");
    }

    @Test
    @RunAsClient
    public void testUsernameToken(@ArquillianResource URL baseURL) throws Exception {
        CalculatorPortType calculatorPort = calculatorService.getCalculatorUsernameTokenPort();
        ((BindingProvider) calculatorPort).getRequestContext()
                .put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, baseURL + "calculator-username-token");
        // User name and password below will be used for username token policy.
        // You can omit them if you have a custom handler configured in the wsit-client.xml file.
        ((BindingProvider) calculatorPort).getRequestContext().put(BindingProvider.USERNAME_PROPERTY, "demo");
        ((BindingProvider) calculatorPort).getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, "demo");

        assertThat(calculatorPort.add(1, 1)).isEqualTo(2);
    }

    @Test
    @RunAsClient
    public void testCertificate(@ArquillianResource URL baseURL) throws Exception {
        CalculatorPortType calculatorPort = calculatorService.getCalculatorCertificatePort();
        ((BindingProvider) calculatorPort).getRequestContext()
                .put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, baseURL + "calculator-certificate");

        assertThat(calculatorPort.add(1, 1)).isEqualTo(2);
    }
}
