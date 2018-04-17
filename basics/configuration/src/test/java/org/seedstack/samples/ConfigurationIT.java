/*
 * Copyright © 2013-2018, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package org.seedstack.samples;

import static org.assertj.core.api.Assertions.assertThat;

import javax.inject.Inject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.seedstack.samples.configuration.MyAppConfig;
import org.seedstack.seed.Application;
import org.seedstack.seed.Configuration;
import org.seedstack.seed.testing.junit4.internal.JUnit4Runner;

@RunWith(JUnit4Runner.class)
public class ConfigurationIT {
    @Configuration("myApp.key")
    private String key;
    @Configuration(value = "myApp.notExistingKey")
    private String notExistingKey = "defaultValue";
    @Configuration("myApp.macro")
    private String macro;
    @Configuration("myApp.environmentVariable")
    private String environmentVariable;
    @Configuration("myApp.systemProperty")
    private String systemProperty;
    @Configuration
    private MyAppConfig myAppConfig;
    @Inject
    private Application application;

    @Test
    public void testBasicConfiguration() {
        assertThat(key).isEqualTo("value");
        assertThat(macro).isEqualTo("*value*");
        assertThat(notExistingKey).isEqualTo("defaultValue");
    }

    @Test
    public void testEnvironmentVariable() {
        assertThat(environmentVariable).isEqualTo(System.getenv("JAVA_HOME"));
    }

    @Test
    public void testSystemProperty() {
        assertThat(systemProperty).isEqualTo(System.getProperty("java.version"));
    }

    @Test
    public void testProgrammaticAccess() {
        assertThat(application.getConfiguration().get(String.class, "myApp.key")).isEqualTo("value");
    }

    @Test
    public void testObjectMapping() {
        assertThat(key).isEqualTo(myAppConfig.getKey());
        assertThat(macro).isEqualTo(myAppConfig.getMacro());
        assertThat(environmentVariable).isEqualTo(myAppConfig.getEnvironmentVariable());
        assertThat(systemProperty).isEqualTo(myAppConfig.getSystemProperty());
    }
}
