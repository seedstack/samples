/*
 * Copyright © 2013-2020, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.seedstack.samples.oauth;

import org.seedstack.seed.core.Seed;

/**
 * OAuth sample - client credential flow
 * Run this class once the Authentication server is running and the client-credential-flow-server secured web service has been started.
 * (Class StartMeFirst)
 */
public class StartInSecond {

    public static void main(String[] args) throws Exception {
        Seed.getLauncher().launch(new String[]{"accountInfo"});
    }
}
