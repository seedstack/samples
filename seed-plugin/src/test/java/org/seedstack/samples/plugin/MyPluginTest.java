package org.seedstack.samples.plugin;

import com.google.inject.Inject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.seedstack.samples.plugin.fixtures.SomeService;
import org.seedstack.seed.Configuration;
import org.seedstack.seed.it.SeedITRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SeedITRunner.class)
public class MyPluginTest {
    @Inject
    private SomeService someService;

    @Configuration("com.github.adrienlauer.poss.key")
    private String configurationKey;

    @Test
    public void servicesAreInjectable() {
        assertThat(someService).isNotNull();
        assertThat(someService.getSomeOtherService()).isNotNull();
    }

    @Test
    public void configurationIsLoaded() {
        assertThat(configurationKey).isEqualTo("value");
    }
}
