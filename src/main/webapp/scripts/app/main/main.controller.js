'use strict';

angular.module('canataliozapposApp')
    .controller('MainController', function ($scope, $http, Principal) {
        Principal.identity().then(function(account) {
            $scope.account = account;
            $scope.isAuthenticated = Principal.isAuthenticated;

        $scope.productSearchResults = {};
        $scope.productSearchResultsList = [];

        $scope.getProductsBySearchTerm = function() {
            $http({
                method: 'GET',
                url: 'http://127.0.0.1:8080/api/products',
                params: '',
                headers: {}
            }).success(function(data){
                console.log(data);
                $scope.productSearchResultsList = data.results;

                console.log("results " + data.results);
                console.log("productSearchResultsList " + $scope.productSearchResultsList[1].productId);
            }).error(function(){
                console.log("Error get products by term.");
            });
        };

        });
    });
