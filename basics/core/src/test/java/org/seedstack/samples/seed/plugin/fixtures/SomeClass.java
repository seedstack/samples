/**
 * Copyright (c) 2013-2016, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.seedstack.samples.seed.plugin.fixtures;

import org.seedstack.samples.seed.plugin.SomeAnnotation;

import javax.inject.Inject;

@SomeAnnotation
public class SomeClass {
    private final SomeOtherClass someOtherClass;

    @Inject
    public SomeClass(SomeOtherClass someOtherClass) {
        this.someOtherClass = someOtherClass;
    }

    public SomeOtherClass getSomeOtherClass() {
        return someOtherClass;
    }
}
