define([
    'require',
    'jquery',
    '{angular}/angular',

    '{angular-resource}/angular-resource',
    '{ecommerce}/modules/services'

], function (require, $, angular) {
    'use strict';

    var module = angular.module('product', []);

    module.controller('ProductController', ['Products', '$location', function (Products, $location) {
        var self = this;

        function set(selected) {
            self.product = selected;
            self.relatedProducts = self.product.$embedded('related');
        }

        self.select = function (product) {
            Products.select(product, function(selectedProduct) {
                $location.path('/ecommerce/product/' + selectedProduct.name);
            });
        };

        Products.selected(set);

    }]);

    return {
        angularModules: [ 'product' ]
    };
});
