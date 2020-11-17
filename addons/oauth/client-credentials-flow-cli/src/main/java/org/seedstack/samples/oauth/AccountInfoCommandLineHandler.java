package org.seedstack.samples.oauth;

import org.seedstack.oauth.spi.OAuthAuthenticationToken;
import org.seedstack.oauth.spi.OAuthService;
import org.seedstack.samples.oauth.api.AccountInfo;
import org.seedstack.samples.oauth.api.SecuredApi;
import org.seedstack.samples.oauth.error.OAuthCredentialErrorCode;
import org.seedstack.samples.oauth.error.OAuthSampleException;
import org.seedstack.seed.Logging;
import org.seedstack.seed.SeedException;
import org.seedstack.seed.cli.CliCommand;
import org.seedstack.seed.cli.CommandLineHandler;
import org.slf4j.Logger;

import javax.inject.Inject;

@CliCommand("accountInfo")
public class AccountInfoCommandLineHandler implements CommandLineHandler {

    /**URL of the secured API to call - Run class client-credentials-flow-server/../StartMeFirst*/
    private static final String MAIN_URI="http://localhost:8090/api";

    @Logging
    private Logger logger;

    @Inject
    private OAuthService oAuthService;

    @Inject
    private SecuredApi securedApi;

    @Override
    public Integer call() throws Exception {
        logger.info("OAuth samples - Client credential flow - Client started");

        //First step - Request for authentication token
        OAuthAuthenticationToken token = requestAuthenticationToken();

        //Step 2 - Request secured api with bearer token
        String accountId=callSecuredApi(token).getAccountId();

        logger.info("Secured call response : {}", accountId);
        return 0;
    }

    /**
     * Request the Authentication token from the Auth server
     * Uses the Oauth configuration, more more information to set here.
     * @return OAuthAuthenticationToken Authentication token
     */
    private OAuthAuthenticationToken requestAuthenticationToken(){
        logger.info("Requesting authentication token.");
        OAuthAuthenticationToken token = oAuthService.requestTokensWithClientCredentials();
        if(token==null){
            throw SeedException.wrap(new OAuthSampleException("Authentication token is null"),OAuthCredentialErrorCode.TOKEN_NOT_RECEIVED);
        }
        logger.info("Authentication token received.");
        return token;
    }

    /**
     * Call the secured API with an authentication token
     * @param token The authentication token previously requested from Auth server
     * @return AccountInfo the account information response
     */
    private AccountInfo callSecuredApi(OAuthAuthenticationToken token){
        logger.info("Calling secured web service");
        return securedApi.accountInfo(token.getAccessToken());
    }
}