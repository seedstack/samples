define([
    'require',
    'jquery',
    '{angular}/angular',

    '{ecommerce}/modules/services'

], function (require, $, angular) {
    'use strict';

    var module = angular.module('products', []);

    module.controller('ProductsController', ['$scope', 'DataService', '$location', 'HomeService', 'ErrorService', function ($scope, dataService, $location, hypermedia, error) {

        var setup = function (resource) {
            $scope.resource = resource;
            $scope.products = $scope.resource.$embedded('products');
            $scope.next = $scope.resource.$links('next');
            $scope.previous = $scope.resource.$links('prev');

            angular.forEach($scope.products, function (product) {
                 product.$links('tags').get(function (tags) {
                     product.tags = tags.$embedded('tags');
                });
            });
        };

        $scope.nextPage = function () {
            $scope.next.get(setup, error);
        };

        $scope.previousPage = function () {
            $scope.previous.get(setup, error);
        };

        $scope.search = function (query) {
            if (query) {
                $scope.resource.$links('find', { q: query }).get(setup, error);
            } else {
                hypermedia('ecommerce').enter('catalog', { page: 1 }).get(setup, error);
            }
        };

        $scope.select = function (product) {
            product.$links('self').get(function (selectedProduct) {
                dataService.selectedProduct(selectedProduct);
                $location.path('/ecommerce/product/' + selectedProduct.name);
            });

        };

        hypermedia('ecommerce').enter('catalog', { page: 1 }).get(setup, error);

    }]);

    return {
        angularModules: [ 'products' ]
    };
});
