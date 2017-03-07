/*
 * Copyright (c) 2013-2016, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
define([
    'module',
    '{angular}/angular',
    '{angular-resource}/angular-resource'
], function (module, angular) {
    var config = module && module.config() || {},
        sampleModule = angular.module('sample', ['ngResource']);

    sampleModule.controller('SampleController', ['$scope', '$resource', function ($scope, $resource) {
        var greeterResource = $resource(config.apiBase + "greeter");

        $scope.name = config.defaultName || '';

        $scope.computeMessage = function () {
            greeterResource.get({
                name: $scope.name
            }, function (data) {
                $scope.message = data.message;
            })
        };
    }]);

    return {
        angularModules: ['sample']
    }
});