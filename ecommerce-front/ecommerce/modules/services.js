define([
    'require',
    'jquery',
    '{angular}/angular',

    '{w20-core}/modules/hypermedia',
    '{angular-resource}/angular-resource'

], function (require, $, angular) {
    'use strict';

    var module = angular.module('services', ['ngResource']);

    module.factory('Products', ['HomeService', '$location', '$routeParams', function (homeService, $location, $routeParams) {

        var Products = {
            all: function () { return homeService('ecommerce').resource('products'); },
            one: function (name) { return homeService('ecommerce').resource('product', { name: name }); }
        };

        var selectedProduct;

        return {
            query: function (c) {
                return Products.all().query(function (products) {
                    if (c) {
                        c(products);
                    }
                });
            },
            get: function (name, c) {
                return Products.one(name).get(function (product) {
                    if (c) {
                        c(product);
                    }
                });
            },
            select: function (product, c) {
                product.$links('self').get(function (product) {
                    selectedProduct = product;
                    if (c) {
                        c(selectedProduct);
                    }
                });
            },
            selected: function (c) {
                if (selectedProduct) {
                    return c ? c(selectedProduct) : selectedProduct;
                } else {
                    this.get($routeParams.name, c);
                }
            }
        };
    }]);

    return {
        angularModules: [ 'services' ]
    };
});
