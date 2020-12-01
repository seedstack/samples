# SeedStack OAuth addon samples

The samples in this directory demonstrate how to configure and use SeedStack's OAuth addon on standard use cases.

## Prerequisites

The OAuth addon samples use a pre-configured authentication and access management demo server.

We use [docker](https://www.docker.com/) to build and run this authorization server from within a container, please make 
sure docker is installed on your workstation and docker daemon is running.

### Building the auth server docker image

In this directory stand the required files to build the auth server image :

* `Dockerfile`: a ready to use docker image building script.
* `SeedStackRealm.json`: file containing the necessary configuration for running the samples (Realm / clients / Users definition)

There is no need to build the image yourself as it is available on the **Docker Hub**, but you can with the following 
command:

```
docker build -t "seedstack/auth-server:1.0" .
```

### Running the auth server

The auth server is listening on the 8080 port. Before running it, ensure that no other application is listening on the 
same port. To enable the auth server, just start the container by running:

```
docker run -p 8080:8080 seedstack/auth-server:1.0
```

To check that the auth server is running correctly, browse http://localhost:8080/auth, you should see a Keycloack 
welcome page.

Ensure that the setting have been correctly imported by connecting to the administration console with the admin/admin 
credentials. You should see the "SeedSamplesRealm" configured realm. 

## Use cases

The following directories show different use cases of the OAuth addon usage :

* [auth-code-flow](https://github.com/seedstack/samples/tree/master/addons/oauth/auth-code-flow) : Use case sample for the **Authorization code flow** usage.
* [auth-code-pkce-flow](https://github.com/seedstack/samples/tree/master/addons/oauth/auth-code-pkce-flow) : use case for the **Authorization Code Flow with Proof Key for Code Exchange (PKCE)** usage.
* [client-credentials-flow-cli](https://github.com/seedstack/samples/tree/master/addons/oauth/client-credentials-flow-cli) and [client-credentials-flow-server](https://github.com/seedstack/samples/tree/master/addons/oauth/client-credentials-flow-server) : use case for the **Client Credentials Flow** usage.  