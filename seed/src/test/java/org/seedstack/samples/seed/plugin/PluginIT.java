package org.seedstack.samples.seed.plugin;

import com.google.inject.Inject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.seedstack.samples.seed.plugin.fixtures.SomeService;
import org.seedstack.seed.it.SeedITRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SeedITRunner.class)
public class PluginIT {
    @Inject
    private SomeService someService;

    @Test
    public void servicesAreInjectable() {
        assertThat(someService).isNotNull();
        assertThat(someService.getSomeOtherService()).isNotNull();
    }
}
