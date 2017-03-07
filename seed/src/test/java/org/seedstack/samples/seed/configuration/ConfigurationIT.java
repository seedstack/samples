/**
 * Copyright (c) 2013-2016, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.seedstack.samples.seed.configuration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seedstack.seed.Application;
import org.seedstack.seed.Configuration;
import org.seedstack.seed.it.SeedITRunner;

import javax.inject.Inject;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SeedITRunner.class)
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

    @Inject
    private Application application;

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
        assertThat(application.getConfiguration().get(String.class, "myApp.key")).isEqualTo("value");
    }
}
