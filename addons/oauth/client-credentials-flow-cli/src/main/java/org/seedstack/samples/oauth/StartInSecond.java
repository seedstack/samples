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
