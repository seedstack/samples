/*
 * Copyright © 2013-2018, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package org.seedstack.samples.cli;

import java.util.Map;
import org.seedstack.seed.cli.CliArgs;
import org.seedstack.seed.cli.CliCommand;
import org.seedstack.seed.cli.CliOption;
import org.seedstack.seed.cli.CommandLineHandler;

@CliCommand("hello")
public class HelloCommandLineHandler implements CommandLineHandler {
    @CliOption(name = "U", longName = "upper-case")
    private boolean upperCase;

    @CliOption(name = "t", longName = "translation", valueCount = -1, valueSeparator = '=')
    private Map<String, String> translations;

    @CliOption(name = "l", longName = "lang", valueCount = 1, defaultValues = "en")
    private String language;

    @CliArgs(mandatoryCount = 1)
    private String[] args;

    public Integer call() {
        String output = String.format("%s %s!", getHello(), args[0]);

        if (upperCase) {
            output = output.toUpperCase();
        }

        System.out.println(output);

        return 0;
    }

    private String getHello() {
        if (translations != null) {
            return translations.getOrDefault(language, "Hello");
        } else {
            return "Hello";
        }
    }
}
