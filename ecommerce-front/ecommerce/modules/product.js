define([
    'require',
    'jquery',
    '{angular}/angular',
    '{angular-resource}/angular-resource'

], function (require, $, angular) {
    'use strict';

    var module = angular.module('product', ['ngResource', 'ngRoute']);

    module.controller('ProductController', [ '$scope', '$resource', 'hapi', '$routeParams',
        function ($scope, $resource, hapi, $routeParams) {

            hapi()

            var product = $resource('/rest/products/:name');

            product.get({name: $routeParams.name}, function (product) {
                $scope.product = product;
            });
        }]);

    return {
        angularModules: [ 'product' ]
    };
});
