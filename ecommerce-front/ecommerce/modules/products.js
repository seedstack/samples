define([
    'require',
    'jquery',
    '{angular}/angular',
    '{angular-resource}/angular-resource',
    '{ecommerce}/modules/services'

], function (require, $, angular) {
    'use strict';

    var module = angular.module('products', ['ngResource', 'w20Hypermedia']);

    module.controller('ProductsController', [ '$rootScope', '$scope', '$resource', '$location', 'Products',
        function ($rootScope, $scope, $resource, $location, Products) {

            $scope.products = Products.query();

            $scope.select = function (product) {
                Products.select(product);
            };

        }]);

    return {
        angularModules: [ 'products' ]
    };
});
