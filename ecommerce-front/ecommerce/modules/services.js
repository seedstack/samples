define([
    'require',
    'jquery',
    '{angular}/angular',
    '{angular-resource}/angular-resource',
    '{ecommerce}/modules/lib/hypermedia'

], function (require, $, angular) {
    'use strict';

    var module = angular.module('services', ['ngResource', 'w20Hypermedia']);

    module.config(['HypermediaRestInterceptorProvider', function (HypermediaRestInterceptorProvider) {
        HypermediaRestInterceptorProvider.apply();
    }]);

    module.factory('Products', ['$resource', '$location', '$routeParams', function ($resource, $location, $routeParams) {
        var Products = $resource('/rest/products/:name'),
            selectedProduct;

        return {
            query: function (callback) {
                return Products.query(function (products) {
                    if (callback) { callback(products); }
                });
            },
            select: function (product) {
                product.$links('self').get(function (product) { selectedProduct = product; });
                $location.path('ecommerce/product/' + product.name);
            },
            selected: function (callback) {
                if (selectedProduct) {
                    return selectedProduct;
                } else {
                    return Products.get({ name: $routeParams.name }, function (product) {
                        if (callback) { callback(product); }
                    });
                }
            }
        };
    }]);

    return {
        angularModules: [ 'services' ]
    };
});
