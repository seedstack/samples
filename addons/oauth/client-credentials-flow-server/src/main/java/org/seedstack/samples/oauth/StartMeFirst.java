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
 * Starts the web server, enabling the api/accountInfo secured request
 */
public class StartMeFirst {
    public static void main(String[] args) throws Exception {
        Seed.getLauncher().launch(args);
    }
}
