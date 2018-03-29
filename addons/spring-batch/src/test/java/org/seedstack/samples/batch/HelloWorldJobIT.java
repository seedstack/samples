/**
 * Copyright (c) 2013-2016, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.seedstack.samples.batch;

import org.junit.Test;
import org.seedstack.seed.cli.WithCommandLine;
import org.seedstack.seed.it.AbstractSeedIT;

public class HelloWorldJobIT extends AbstractSeedIT {

    @Test
    @WithCommandLine(command = "run-job", args = {"--job", "helloWorldJob"}, expectedExitCode = 0)
    public void helloWorld() throws Exception {
        // Batch post-execution assertions go here
    }

}
