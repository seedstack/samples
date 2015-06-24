define([
    'require',
    'jquery',
    '{angular}/angular',
    '{angular-resource}/angular-resource',
    '{ecommerce}/modules/lib/hypermedia'

], function (require, $, angular) {
    'use strict';

    var module = angular.module('products', ['ngResource', 'w20Hypermedia']);

    module.config(['HypermediaRestInterceptorProvider', function (HypermediaRestInterceptorProvider) {
        HypermediaRestInterceptorProvider.apply();
    }]);

    module.controller('ProductsController', [ '$rootScope', '$scope', '$resource', '$location',
        function ($rootScope, $scope, $resource, $location) {

            var Products = $resource('/rest/products');

            Products.query(function (products) {
                $scope.products = products;
            });

            $scope.view = function (product) {
                product.$links('self').get(function (product) {
                    $rootScope.selectedProduct = product;
                    $location.path('ecommerce/product/' + product.name);
                });
            };

        }]);

    return {
        angularModules: [ 'products' ]
    };
});
