/*
 * Copyright © 2013-2018, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package org.seedstack.samples.configuration;

import org.seedstack.coffig.Config;

@Config("myApp")
public class MyAppConfig {
    private String key;
    private String macro;
    private String environmentVariable;
    private String systemProperty;
    private String country;

    public String getKey() {
        return key;
    }

    public String getMacro() {
        return macro;
    }

    public String getEnvironmentVariable() {
        return environmentVariable;
    }

    public String getSystemProperty() {
        return systemProperty;
    }

    public String getCountry() {
        return country;
    }
}
