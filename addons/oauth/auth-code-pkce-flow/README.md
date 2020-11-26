# OAuth addon - Authorization Code Flow with Proof Key for Code Exchange (PKCE) sample

## Sample use case

This samples shows how to configure Oauth addon in your seedstack application for an Authorization Code Flow with Proof Key for Code Exchange usage.

This sample runs a seedstack application exposing a protected resource.

The Oauth configuration is both on frontend and backend sides.

The Frontend client proceeds to authentication with the auth server and then requests the backend with an auth token. The Backend validates the token an gives access (or not ) to the protected resource.

### Frontend configuration

Not part of seedstack, the frontend oauth configuration depends on the used technology.

In this sample, the frontend configuration set is :

*client_id : client identifier as registered in the auth server
*redirect_uri : the redirection uri
*authorization_endpoint : Auth server endpoint for authentication
*token_endpoint : Auth server end point for tokens
*requested_scopes : The user's requested scopes
*audience : the request audience
*code_challenge_method: The crypting method for the code challenge verification

### Backend configuration

The application.yaml configuration file show how the addon is configured for this use case.

The resource if protected using the oauth security filter. As the authentication is made frontend side, there is no need for a oauth callback filter on backend side.

The OAuth addon parameters are set :
*discoveryDocument : the auth server openId discovery document
*allowedAudiences : the concerned audiences for this resource
*scopes : the user's requested scopes for this sample pkceScope is required

## Running the sample

### Prerequisite

In order to run this sample, the provided authentication and access management server must be running. (Please see [this page](https://github.com/seedstack/samples/tree/master/addons/oauth) for more details)

This sample starts a server listening on the 8090 port. Make sure no other application is using this port.


### Run the sample

Run this maven project main class : org.seedstack.samples.oauth.Demo

The seedStack application starts listening ton the 8090 port.

From your browser, make a request to http://localhost:8090 and click on "Click to Sign In"

At this point, the auth server should request you for authentication.

Enter the following credentials :
 
* **User "John Doe" password : "P4$$word"** : Pre-configured user with accurate scope. The displayed page should provide information on the user.
* **User "Jane Smith" password : "P4$$word"** : Pre-configured user not associated to the required scope. The displayed page should be an access denied page.