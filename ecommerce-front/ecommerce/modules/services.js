define([
    'require',
    'jquery',
    '{angular}/angular',

    '{w20-core}/modules/hypermedia',
    '{angular-resource}/angular-resource'

], function (require, $, angular) {
    'use strict';

    var module = angular.module('services', ['ngResource']);

    module.factory('ErrorService', ['$log', function ($log) {

      return function (err) {
          $log.error('Could not get resource, status: ' + err.status);
      };
    }]);

    module.factory('DataService', [function () {

        var selectedProduct;

        return {
            selectedProduct: function (product) {
                if (product) {
                    selectedProduct = product;
                }
                return selectedProduct;
            }
        };
    }]);

    return {
        angularModules: [ 'services' ]
    };
});
