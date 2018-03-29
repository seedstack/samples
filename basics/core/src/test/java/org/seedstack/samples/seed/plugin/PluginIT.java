/**
 * Copyright (c) 2013-2016, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.seedstack.samples.seed.plugin;

import com.google.inject.Inject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.seedstack.samples.seed.plugin.fixtures.SomeClass;
import org.seedstack.seed.it.SeedITRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SeedITRunner.class)
public class PluginIT {
    @Inject
    private SomeClass someClass;

    @Test
    public void servicesAreInjectable() {
        assertThat(someClass).isNotNull();
        assertThat(someClass.getSomeOtherClass()).isNotNull();
    }
}
