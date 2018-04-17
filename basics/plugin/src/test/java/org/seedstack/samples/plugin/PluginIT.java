/*
 * Copyright © 2013-2018, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package org.seedstack.samples.plugin;

import static org.assertj.core.api.Assertions.assertThat;

import com.google.inject.Inject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.seedstack.samples.plugin.fixtures.SomeClass;
import org.seedstack.seed.testing.junit4.internal.JUnit4Runner;

@RunWith(JUnit4Runner.class)
public class PluginIT {
    @Inject
    private SomeClass someClass;

    @Test
    public void servicesAreInjectable() {
        assertThat(someClass).isNotNull();
        assertThat(someClass.getSomeOtherClass()).isNotNull();
    }
}
