/*
 * Copyright © 2013-2018, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package org.seedstack.samples.diagnostic;

import java.util.HashMap;
import java.util.Map;
import javax.inject.Inject;
import org.seedstack.seed.diagnostic.spi.DiagnosticDomain;
import org.seedstack.seed.diagnostic.spi.DiagnosticInfoCollector;

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
