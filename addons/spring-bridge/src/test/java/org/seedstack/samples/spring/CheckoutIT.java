/*
 * Copyright © 2013-2018, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package org.seedstack.samples.spring;

import static org.assertj.core.api.Assertions.assertThat;

import javax.inject.Inject;
import javax.inject.Named;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.seedstack.seed.it.SeedITRunner;

@RunWith(SeedITRunner.class)
public class CheckoutIT {
    @Inject
    @Named("checkoutService")
    CheckoutService checkoutService;

    @Test
    public void testCheckout() {
        assertThat(checkoutService.checkout(100, "cc")).isEqualTo(120);
    }
}
