/**
 * Copyright (c) 2013-2016, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.seedstack.samples.seed.diagnostic;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seedstack.seed.diagnostic.DiagnosticManager;
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
