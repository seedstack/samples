/**
 * Copyright (c) 2013-2016, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.seedstack.samples.seed.cli;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seedstack.seed.cli.WithCommandLine;
import org.seedstack.seed.it.SeedITRunner;

@RunWith(SeedITRunner.class)
public class CommandLineIT {

    @Test
    @WithCommandLine(command = "my-command", args = {"--name", "world"})
    public void testCommandLine() throws Exception {
        // nothing to do here
    }

    @Test
    @WithCommandLine(command = "my-command", args = {"--abort"}, expectedExitCode = -1)
    public void testFailingCommandLine() throws Exception {
        // nothing to do here
    }
}
