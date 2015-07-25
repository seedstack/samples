define([
    'require',
    'jquery',
    '{angular}/angular',

    '{angular-resource}/angular-resource',
    '{ecommerce}/modules/services'

], function (require, $, angular) {
    'use strict';

    var module = angular.module('product', []);

    module.controller('ProductController', ['$scope', 'DataService', 'HomeService', '$routeParams', '$location', 'ErrorService', function ($scope, dataService, hypermedia, $routeParams, $location, error) {

        var selectedProduct;

        function setup(selected) {
            $scope.product = selected;
            // $scope.relatedProducts = $scope.product.$embedded('related');
        }

        $scope.select = function (product) {
            product.$links('self').get(function (selectedProduct) {
                dataService.selectedProduct(selectedProduct);
                $location.path('/ecommerce/product/' + selectedProduct.name);
            });
        };

        selectedProduct = dataService.selectedProduct();

        if (selectedProduct) {
            setup(selectedProduct);
        } else {
            hypermedia('ecommerce').enter('product', { title: $routeParams.name }).get(setup, error);
        }

    }]);

    return {
        angularModules: [ 'product' ]
    };
});
