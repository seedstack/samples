#OAuth addon - Authorization code flow sample

##Sample use case

This samples shows how to configure Oauth addon in your seedstack application for an Authorization code flow usage.

This sample runs a seedstack application exposing a protected resource.

The OAuth configuration is backend side. When the request is made to a protected resource, the backend sends an authenticaton request to the identity provider and gets the response sending a http error code if user is not authenticated or has a denied access.

The application.yaml configuration file show how the addon is configured for this use case.

* url patterns are secured with oauth and oauthCallback filters
* the addon configuration shows the required items :
    * discoveryDocument : auth server OpenID end-points
    * redirect : callback redirect
    * scopes : Required scopes, for this sample CodeFlowScope is required
    * clientId : The auth-server registered client identifier related to this this sample
    * clientSecret : The auth-server registered client secret
    * allowedAudiences : Authorizes the auth-server "account" audience    
    * autoFetchUserInfo : Set to true, the application retrieves user information automatically.
    
The org.seedstack.samples.oauth.ProfileResource class is the secured end-point of this sample.


##Running the sample

###Prerequisite

In order to run this sample, the provided authentication and access management server must be running. (Please see [this page](https://github.com/seedstack/samples/tree/master/addons/oauth) for more details)

This sample starts a server listening on the 8090 port. Make sure no other application is using this port.

###Run the sample

Run this maven project main class : org.seedstack.samples.oauth.Demo

The seedstack application starts listening ton the 8090 port.

From your browser, make a browse to http://localhost:8090 and click on *Click here to log in.*

At this point, the auth server should request you for authentication.

Enter the following credentials :
 
* **User "John Doe" password : "P4$$word"** : Pre-configured user with agreement on the required "CodeFlowScope" scope. The displayed page should provide information on the user.
* **User "Jane Smith" password : "P4$$word"** : pre-configured user with no agreement on the required "CodeFlowScope" scope, **refuse the scope**, you should see an error page.