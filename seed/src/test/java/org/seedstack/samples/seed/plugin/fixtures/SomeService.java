package org.seedstack.samples.seed.plugin.fixtures;

import org.seedstack.samples.seed.plugin.Service;

import javax.inject.Inject;

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
