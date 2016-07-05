package org.seedstack.samples.seed.diagnostic;

import org.seedstack.seed.spi.diagnostic.DiagnosticDomain;
import org.seedstack.seed.spi.diagnostic.DiagnosticInfoCollector;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

@DiagnosticDomain("my-diag")
public class MyDiagnosticCollector implements DiagnosticInfoCollector {
    @Inject

    @Override
    public Map<String, Object> collect() {
        HashMap<String, Object> info = new HashMap<>();
        info.put("hello", "world");
        return info;
    }
}
