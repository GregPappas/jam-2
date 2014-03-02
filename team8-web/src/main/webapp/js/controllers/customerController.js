"use strict";

var team8App = angular.module("team8App", []);

team8App.controller("CustomerController", function ($scope, $http) {
    $scope.sortField = "";

    $http.get("/rest/customer/customers").success(function(data) {
        $scope.customers = data;
    });
});