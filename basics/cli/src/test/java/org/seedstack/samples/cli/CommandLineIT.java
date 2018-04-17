/*
 * Copyright © 2013-2018, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package org.seedstack.samples.cli;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.seedstack.seed.cli.WithCliCommand;
import org.seedstack.seed.testing.Arguments;
import org.seedstack.seed.testing.junit4.internal.JUnit4Runner;

@RunWith(JUnit4Runner.class)
public class CommandLineIT {
    private static final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private static final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private static PrintStream savedOut;
    private static PrintStream savedErr;

    @BeforeClass
    public static void setUpStreams() {
        savedOut = System.out;
        savedErr = System.err;
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void clearStreams() {
        outContent.reset();
        errContent.reset();
    }

    @AfterClass
    public static void tearDownStreams() {
        System.setOut(savedOut);
        System.setErr(savedErr);
    }

    @Test
    @WithCliCommand(command = "hello")
    @Arguments({"test"})
    public void basic_operation() {
        assertThat(outContent.toString()).contains("Hello test!");
    }

    @Test
    @WithCliCommand(command = "hello")
    @Arguments({"test", "-t", "fr=Bonjour", "-l", "fr"})
    public void translation_is_working() {
        assertThat(outContent.toString()).contains("Bonjour test!");
    }
}
