# Samples meta-repository

This meta-repository contains all SeedStack samples as Git submodules. They are intended to illustrate the various project types you can create with SeedStack and/or demonstrate specific features.

# Usage

You can initialize all submodules to the tip of their master branch and checkout it with the following commands:

    git submodule update --remote --recursive --init && git submodule foreach --recursive git checkout master
    
Later, you may need to update all submodules to the tip of their master branch again:     

    git submodule foreach --recursive git pull --ff-only origin master:master

# Samples list

* [E-commerce domain](https://github.com/seedstack/ecommerce-domain-sample). A reusable model of a very simple e-commerce domain.
* [Store management](https://github.com/seedstack/store-webapp-sample). A classic Java Web application based on the e-commerce domain model.
* [Catalog](https://github.com/seedstack/catalog-microservice-sample). A REST microservice exposing the API of a product catalog, based on the e-commerce domain model.
* [E-commerce web client](https://github.com/seedstack/ecommerce-client-sample). A W20 client for the catalog microservice.
* [Simple WS](https://github.com/seedstack/simple-ws-sample). A simple Web-Service exposing product information, based on the e-commerce domain model.

# Issue tracking

All issues regarding SeedStack samples are aggregated on [this Waffle board](https://waffle.io/seedstack/samples).

# Copyright and license

This source code is copyrighted by [The SeedStack Authors](https://github.com/seedstack/seedstack/blob/master/AUTHORS) and released under the terms of the [Mozilla Public License 2.0](https://www.mozilla.org/MPL/2.0/).
