define([
    'require',
    'jquery',
    '{angular}/angular',

    '{ecommerce}/modules/services'

], function (require, $, angular) {
    'use strict';

    var module = angular.module('products', []);

    module.controller('ProductsController', ['$scope', 'Products', '$location', 'HypermediaRestAdapter', function ($scope, Products, $location, HypermediaRestAdapter) {
        var self = this;

        function setup(resource) {
            self.resource = resource;
            self.products = self.resource.$embedded('products');

            angular.forEach(self.products, function (product) {
                product.tags = product.$links('tags').query();
            });

            self.next = self.resource.$links('next');
            self.previous = self.resource.$links('previous');
        }

        self.nextPage = function () {
            self.next.get(setup);
        };

        self.previousPage = function () {
            self.previous.get(setup);
        };

        self.search = function (query) {
            if (query) {
                self.resource.$links('find', { q: query }).get(setup);
            } else {
                Products.list(1, setup);
            }
        };

        self.select = function (product) {
            Products.select(product, function (selectedProduct) {
                $location.path('/ecommerce/product/' + selectedProduct.name);
            });
        };

        Products.list(1, setup);

    }]);

    return {
        angularModules: [ 'products' ]
    };
});
