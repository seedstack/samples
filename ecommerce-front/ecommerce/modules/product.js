define([
    'require',
    'jquery',
    '{angular}/angular',
    '{angular-resource}/angular-resource',
    '{ecommerce}/modules/services'

], function (require, $, angular) {
    'use strict';

    var module = angular.module('product', ['ngResource', 'ngRoute']);

    module.controller('ProductController', [ '$rootScope', '$scope', '$resource', '$routeParams', 'Products',
        function ($rootScope, $scope, $resource, $routeParams, Products) {

            $scope.product = Products.selected();

        }]);

    return {
        angularModules: [ 'product' ]
    };
});
