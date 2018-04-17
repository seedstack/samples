/*
 * Copyright © 2013-2018, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package org.seedstack.samples.guice;

import static org.assertj.core.api.Assertions.assertThat;

import javax.inject.Inject;
import javax.inject.Named;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.seedstack.seed.testing.junit4.internal.JUnit4Runner;

@RunWith(JUnit4Runner.class)
public class GuiceIT {
    @Inject
    private Greeter greeter1;

    @Inject
    @Named("toto")
    private Greeter greeter2;

    @Test
    @Trace
    public void testMethodTracingWorks() {

    }

    @Test
    public void testInjectionWorks() {
        assertThat(greeter1).isNotNull();
        assertThat(greeter2).isNotNull();
    }
}
