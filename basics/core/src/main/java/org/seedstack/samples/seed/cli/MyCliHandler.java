/**
 * Copyright (c) 2013-2016, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.seedstack.samples.seed.cli;

import org.seedstack.seed.cli.CliCommand;
import org.seedstack.seed.cli.CliOption;
import org.seedstack.seed.cli.CommandLineHandler;

@CliCommand("my-command")
public class MyCliHandler implements CommandLineHandler {
    @CliOption(name = "n", longName = "name", valueCount = 1)
    private String name;

    @CliOption(name = "a", longName = "abort")
    private boolean abort;

    @Override
    public Integer call() throws Exception {
        if (abort) {
            return -1;
        }

        System.out.println(String.format("Hello %s!", name));

        return 0;
    }
}
