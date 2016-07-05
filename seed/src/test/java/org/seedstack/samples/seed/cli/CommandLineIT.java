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
