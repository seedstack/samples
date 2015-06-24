define([
    'require',
    'jquery',
    '{angular}/angular',
    '{angular-resource}/angular-resource'

], function (require, $, angular) {
    'use strict';

    var module = angular.module('product', ['ngResource', 'ngRoute']);

    module.controller('ProductController', [ '$rootScope', '$scope', '$resource', '$routeParams',
        function ($rootScope, $scope, $resource, $routeParams) {

            $scope.product = $rootScope.selectedProduct;

        }]);

    return {
        angularModules: [ 'product' ]
    };
});
