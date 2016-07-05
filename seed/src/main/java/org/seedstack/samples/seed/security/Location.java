package org.seedstack.samples.seed.security;

import org.seedstack.seed.security.SimpleScope;
import org.seedstack.seed.security.spi.SecurityScope;

@SecurityScope("location")
public class Location extends SimpleScope {
    public Location(String value) {
        super(value);
    }
}
