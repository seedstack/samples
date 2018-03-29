/*
 * Copyright © 2013-2018, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
/*
 *
 */

package org.seedstack.samples.ws.ws.product;

import javax.inject.Inject;
import javax.jws.WebService;
import org.seedstack.business.assembler.dsl.FluentAssembler;
import org.seedstack.samples.ws.domain.model.Product;
import org.seedstack.samples.ws.product.BadProductRequest;
import org.seedstack.samples.ws.product.BadProductRequestException;
import org.seedstack.samples.ws.product.ProductInfo;
import org.seedstack.samples.ws.product.ProductInfoPortType;

@WebService(
        endpointInterface = "org.seedstack.samples.ws.product.ProductInfoPortType",
        targetNamespace = "http://seedstack.org/samples/ws/product",
        serviceName = "ProductInfoService",
        portName = "ProductInfoPort"
)
public class ProductInfoImpl implements ProductInfoPortType {
    @Inject
    private FluentAssembler fluentAssembler;

    @Override
    public ProductInfo productInfoById(long idProduct) throws BadProductRequestException {
        if (idProduct <= 0) {
            throw new BadProductRequestException("Error retrieving product",
                    buildBadProductFaultInfo("Product identifier cannot be negative or 0", idProduct));
        } else {
            return fluentAssembler.assemble(new Product(idProduct, "Product #" + idProduct)).to(ProductInfo.class);
        }
    }

    private BadProductRequest buildBadProductFaultInfo(String message, long idProduct) {
        BadProductRequest faultInfo = new BadProductRequest();
        faultInfo.setMessage(message);
        faultInfo.setRequestedProduct(idProduct);
        return faultInfo;
    }
}
