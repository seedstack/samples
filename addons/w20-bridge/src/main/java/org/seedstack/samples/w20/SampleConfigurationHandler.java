/*
 * Copyright © 2013-2018, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package org.seedstack.samples.w20;

import java.util.Map;
import org.seedstack.seed.Configuration;
import org.seedstack.w20.spi.FragmentConfigurationHandler;

public class SampleConfigurationHandler implements FragmentConfigurationHandler {
    @Configuration(value = "sample.defaultName")
    private String defaultName;

    @Override
    public Boolean overrideFragmentStatus(String fragmentName) {
        return null;
    }

    @Override
    public Boolean overrideModuleStatus(String fragmentName, String moduleName) {
        return null;
    }

    @Override
    public void overrideConfiguration(String fragmentName, String moduleName, Map<String, Object> sourceConfiguration) {
        if (fragmentName.equals("sample") & moduleName.equals("sample") && defaultName != null) {
            sourceConfiguration.put("defaultName", defaultName);
        }
    }

    @Override
    public void overrideVariables(String fragmentName, Map<String, String> variables) {

    }
}
