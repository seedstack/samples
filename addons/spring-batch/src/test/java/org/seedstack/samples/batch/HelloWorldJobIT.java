/*
 * Copyright © 2013-2018, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package org.seedstack.samples.batch;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seedstack.seed.cli.WithCliCommand;
import org.seedstack.seed.testing.Arguments;
import org.seedstack.seed.testing.junit4.internal.JUnit4Runner;

@RunWith(JUnit4Runner.class)
public class HelloWorldJobIT {
    @Test
    @WithCliCommand(command = "run-job")
    @Arguments({"--job", "helloWorldJob"})
    public void helloWorld() {
        // Batch post-execution assertions go here
    }
}
