package org.seedstack.samples.seed.configuration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seedstack.seed.Application;
import org.seedstack.seed.Configuration;
import org.seedstack.seed.it.SeedITRunner;

import javax.inject.Inject;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SeedITRunner.class)
public class DiagnosticIT {
    @Configuration("my-app.key")
    private String key;
    @Configuration(value = "my-app.not-existing-key", defaultValue = "defaultValue")
    private String notExistingKey;
    @Configuration("my-app.macro")
    private String macro;
    @Configuration("my-app.environment-variable")
    private String environmentVariable;
    @Configuration("my-app.system-property")
    private String systemProperty;

    @Inject
    Application application;

    @Test
    public void testBasicConfiguration() throws Exception {
        assertThat(key).isEqualTo("value");
        assertThat(macro).isEqualTo("*value*");
        assertThat(notExistingKey).isEqualTo("defaultValue");
    }

    @Test
    public void testEnvironmentVariable() throws Exception {
        assertThat(environmentVariable).isEqualTo(System.getenv("JAVA_HOME"));
    }

    @Test
    public void testSystemProperty() throws Exception {
        assertThat(systemProperty).isEqualTo(System.getProperty("java.version"));
    }

    @Test
    public void testProgrammaticAccess() throws Exception {
        assertThat(application.getConfiguration().getString("my-app.key")).isEqualTo("value");
    }
}
