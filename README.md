# Samples meta-repository

This meta-repository contains all SeedStack samples (with some as git submodules). They are intended to illustrate the various project types you can create with SeedStack and/or demonstrate specific features.

# Cloning

You can initialize all submodules to the tip of their master branch and checkout it with the following commands:

    git submodule update --remote --recursive --init && git submodule foreach --recursive git checkout master
    
Later, you may need to update all submodules to the tip of their master branch again:     

    git submodule foreach --recursive git pull --ff-only origin master:master

# List

| Sample | Directory | Demonstrated features |
|---|---|---|
| Batch job | [batch](https://github.com/seedstack/samples/tree/master/batch) | Spring Batch "Hello World" job |
| Business code | [business](https://github.com/seedstack/samples/tree/master/business) | Business framework usage |
| Catalog micro-service | [catalog-microservice](https://github.com/seedstack/catalog-microservice-sample/tree/master) | JAX-RS integration, hypermedia and JSON-home |
| Command-line | [cli](https://github.com/seedstack/samples/tree/master/cli) | CLI commands, options and arguments |
| Seed | [seed](https://github.com/seedstack/samples/tree/master/seed) | Security, configuration, diagnostic, Guice, custom plugin, CLI, REST, Web, Websocket, integration testing |
| Spring bridge | [spring-bridge](https://github.com/seedstack/samples/tree/master/spring-bridge) | Spring bridge (two-way injection, configuration) |
| Store Web application | [store-webapp](https://github.com/seedstack/store-webapp-sample/tree/master) | REST resources, JPA persistence, JavaMail, pagination, static resources serving, simple business code |
| W20 bridge | [w20-bridge](https://github.com/seedstack/samples/tree/master/w20-bridge) | Backend/frontend integration with W20 bridge |
| W20 hello world | [w20-hello-world](https://github.com/seedstack/samples/tree/master/w20-hello-world) | W20 initialization |
| W20 hypermedia | [w20-hypermedia](https://github.com/seedstack/w20-hypermedia-sample/tree/master) | W20 client-side hypermedia |
| W20 fragment | [w20-fragment](https://github.com/seedstack/samples/tree/master/w20-fragment) | Fragment definition and configuration, basic AngularJS, internationalization. |
| Web Services | [web-services](https://github.com/seedstack/web-services-sample/tree/master) | JAX-WS integration through Web-Services add-on |

# Issue tracking

All issues regarding SeedStack samples are aggregated on [this Waffle board](https://waffle.io/seedstack/samples).

# Copyright and license

This source code is copyrighted by [The SeedStack Authors](https://github.com/seedstack/seedstack/blob/master/AUTHORS) and released under the terms of the [Mozilla Public License 2.0](https://www.mozilla.org/MPL/2.0/).
