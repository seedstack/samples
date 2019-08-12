/*
 * Copyright © 2013-2018, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package org.seedstack.samples.store.infrastructure.data;

import java.io.IOException;
import java.io.InputStream;
import javax.inject.Inject;
import org.seedstack.business.data.DataManager;
import org.seedstack.jpa.JpaUnit;
import org.seedstack.seed.LifecycleListener;
import org.seedstack.seed.transaction.Transactional;

public class DataLifecycleListener implements LifecycleListener {
    @Inject
    private DataManager dataManager;

    @Override
    @Transactional
    @JpaUnit("store")
    public void started() {
        try (InputStream is = DataLifecycleListener.class.getResourceAsStream("/data.json")) {
            if (is == null) {
                throw new RuntimeException("Cannot find data.json at classpath root");
            }
            dataManager.importData(is);
        } catch (IOException e) {
            throw new RuntimeException("Unable to import data", e);
        }
    }

    @Override
    public void stopping() {

    }
}
