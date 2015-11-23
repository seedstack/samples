package org.seedstack.samples.plugin.fixtures;

import com.google.inject.Inject;
import org.seedstack.samples.plugin.Service;

@Service
public class SomeService {
    private final SomeOtherService someOtherService;

    @Inject
    public SomeService(SomeOtherService someOtherService) {
        this.someOtherService = someOtherService;
    }

    public SomeOtherService getSomeOtherService() {
        return someOtherService;
    }
}
