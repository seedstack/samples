package org.seedstack.samples.seed.diagnostic;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seedstack.seed.DiagnosticManager;
import org.seedstack.seed.it.SeedITRunner;

import javax.inject.Inject;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SeedITRunner.class)
public class DiagnosticIT {
    @Inject
    private DiagnosticManager diagnosticManager;

    @Test
    public void testDiagnosticInfoIsPresent() throws Exception {
        Map<String, Object> diagnosticInfo = diagnosticManager.getDiagnosticInfo(null);
        assertThat(((Map) diagnosticInfo.get("my-diag")).get("hello")).isEqualTo("world");
    }
}
