define([
    'require',
    'jquery',
    '{angular}/angular',

    '{ecommerce}/modules/services'

], function (require, $, angular) {
    'use strict';

    var module = angular.module('products', []);

    module.controller('ProductsController', ['$scope', 'Products', '$location', function ($scope, Products, $location) {
        var self = this;

        function set(resource) {
            self.resource = resource;
            self.products = self.resource.$embedded('products');
            self.next = self.resource.$links('next');
            self.previous = self.resource.$links('previous');
        }

        self.nextPage = function () {
            self.next.get(set);
        };

        self.previousPage = function () {
            self.previous.get(set);
        };

        self.search = function (query) {
            if (query) {
                self.resource.$links('find', { q: query }).get(set);
            } else {
                Products.list(1, set);
            }
        };

        self.select = function (product) {
            Products.select(product, function (selectedProduct) {
                $location.path('/ecommerce/product/' + selectedProduct.name);
            });
        };

        self.tags = function (product) {
            product.tags = product.$links('tags').query();
        };

        Products.list(1, set);

    }]);

    return {
        angularModules: [ 'products' ]
    };
});
