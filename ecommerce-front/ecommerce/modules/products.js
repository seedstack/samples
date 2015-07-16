define([
    'require',
    'jquery',
    '{angular}/angular',

    '{ecommerce}/modules/services'

], function (require, $, angular) {
    'use strict';

    var module = angular.module('products', []);

    module.controller('ProductsController', ['Products', '$location', function (Products, $location) {
        var self = this;

        function set (embedded) {
            self.products = embedded;
        }

        self.search = function (query) {
            if (query) {
                self.products.$links('find', { q: query }).query(set);
            } else {
                Products.query(set);
            }
        };

        self.select = function (product) {
            Products.select(product, function (selectedProduct) {
                $location.path('/ecommerce/product/' + selectedProduct.name);
            });
        };

        Products.query(set);

    }]);

    return {
        angularModules: [ 'products' ]
    };
});
