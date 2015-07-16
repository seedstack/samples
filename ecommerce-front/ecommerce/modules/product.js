define([
    'require',
    'jquery',
    '{angular}/angular',

    '{angular-resource}/angular-resource',
    '{ecommerce}/modules/services'

], function (require, $, angular) {
    'use strict';

    var module = angular.module('product', []);

    module.controller('ProductController', ['Products', function (Products) {

            this.product = Products.selected();

        }]);

    return {
        angularModules: [ 'product' ]
    };
});
