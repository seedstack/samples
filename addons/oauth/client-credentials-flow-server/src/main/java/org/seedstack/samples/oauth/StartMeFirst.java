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
