define([
    'require',
    'jquery',
    '{angular}/angular',
    '{angular-resource}/angular-resource',
    '{traverson-angular}/traverson-angular'

], function (require, $, angular) {
    'use strict';

    var module = angular.module('products', ['ngResource', 'traverson' ]);

    module.factory('hapi', ['traverson', function (traverson) {
      return function(path) {
          return traverson.from(path).useAngularHttp();
      };
    }]);

    module.controller('ProductsController', [ '$scope', '$resource', 'hapi', '$location',
        function ($scope, $resource, hapi, $location) {

            hapi('/rest/products').newRequest()
                .getResource()
                .result
                .then(function (data) {
                    $scope.products = data;
                }, function (error) {
                    throw new Error(error);
                });

        }]);

    return {
        angularModules: [ 'products' ]
    };
});
