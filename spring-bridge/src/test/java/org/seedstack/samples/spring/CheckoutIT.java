package org.seedstack.samples.spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seedstack.seed.it.SeedITRunner;

import javax.inject.Inject;
import javax.inject.Named;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SeedITRunner.class)
public class CheckoutIT {
    @Inject
    @Named("checkoutService")
    CheckoutService checkoutService;

    @Test
    public void testCheckout() throws Exception {
        assertThat(checkoutService.checkout(100, "cc")).isEqualTo(120);
    }
}
