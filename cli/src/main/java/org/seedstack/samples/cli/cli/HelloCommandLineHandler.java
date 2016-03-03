package org.seedstack.samples.cli.cli;

import org.seedstack.seed.cli.CliArgs;
import org.seedstack.seed.cli.CliCommand;
import org.seedstack.seed.cli.CliOption;
import org.seedstack.seed.cli.CommandLineHandler;

import java.util.Map;

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

    public Integer call() throws Exception {
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
            return  "Hello";
        }
    }
}
