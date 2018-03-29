/*
 * Copyright © 2013-2018, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package org.seedstack.samples.plugin.fixtures;

import javax.inject.Inject;
import org.seedstack.samples.plugin.SomeAnnotation;

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
