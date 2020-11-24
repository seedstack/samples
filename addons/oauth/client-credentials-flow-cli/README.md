#OAuth addon - Authorization with Client Credentials Flow

##Sample use case

The client credential flow is used in a machine to machine exchange. In this sample, the client is a seedstack CLI application requesting an authentication token to the auth server.

Once the token recieved, the client makes a request to a distant protected resource authenticated with a "Authorization : Bearer [TOKEN]" header.

On the server side, there is a seedstack web application exposing a protected resource, the application recieves the request and validates the token to grant access to the resource.

###Client configuration

On the client side, in the application.yaml file, oauth addon if configured with :

*discoveryDocument : The auth server openID discovery document
*clientId : Client identifier as registered in the auth server as allowing service account (client credential flow) usage
*clientSecret : The clientSecret registered in the auth server

###Server configuration

On server side, the oauth addon is configured in the application.yaml file with :

*discoveryDocument : The auth server openID discovery document
*allowedAudiences : The audiences concerned
*treatScopesAsPermissions : Set with "true" the account scope can be used as permissions in seedstack security usage. 

##Running the sample

###Prerequisite

In order to run this sample, the provided authentication and access management server must be running. (Please see [this page](https://github.com/seedstack/samples/tree/master/addons/oauth) for more details)

This sample starts a server listening on the 8090 port. Make sure no other application is using this port.

###Run the sample

First, you need to start the server-side application of this sample.

On the [client-credentials-flow-server](https://github.com/seedstack/samples/tree/master/addons/oauth/client-credentials-flow-server) maven project, run the main class **org.seedstack.samples.oauth.StartMeFirst** .

Then, in parallel, run the **org.seedstack.samples.oauth.StartInSecond** of this maven project.

As it is machine to machine there is no user interaction, in the client logs, you should see the different steps of the process and finaly the account ID displayed after the log "Secured call response".