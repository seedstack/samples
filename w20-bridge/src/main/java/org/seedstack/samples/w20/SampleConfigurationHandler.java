package org.seedstack.samples.w20;

import org.seedstack.seed.Configuration;
import org.seedstack.w20.spi.FragmentConfigurationHandler;

import java.util.Map;

public class SampleConfigurationHandler implements FragmentConfigurationHandler {
    @Configuration(value = "sample.defaultName", mandatory = false)
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
