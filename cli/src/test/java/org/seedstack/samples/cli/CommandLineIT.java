/**
 * Copyright (c) 2013-2016, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.seedstack.samples.cli;

import org.junit.Before;
import org.junit.Test;
import org.seedstack.seed.cli.WithCommandLine;
import org.seedstack.seed.it.AbstractSeedIT;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;

public class CommandLineIT extends AbstractSeedIT {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @Test
    @WithCommandLine(command = "hello", args = {"test"}, expectedExitCode = 0)
    public void basic_operation() {
        assertThat(outContent.toString()).contains("Hello test!");
        System.setOut(null);
        System.setErr(null);
    }

    @Test
    @WithCommandLine(command = "hello", args = {"test", "-t", "fr=Bonjour", "-l", "fr"}, expectedExitCode = 0)
    public void translation_is_working() {
        assertThat(outContent.toString()).contains("Bonjour test!");
        System.setOut(null);
        System.setErr(null);
    }
}
