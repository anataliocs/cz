'use strict';

angular.module('canataliozapposApp')
    .controller('MainController', function ($scope, $http, Principal) {
        Principal.identity().then(function(account) {
            $scope.account = account;
            $scope.isAuthenticated = Principal.isAuthenticated;

        $scope.productSearchResults = {};
        $scope.productSearchResultsList = [];
        $scope.totalMaxCost;
        $scope.productCount;
        $scope.productSearchResultsLoading = false;
        $scope.firstSearchPerformed = false;

        $scope.getProductsBySearchTerm = function() {
            $scope.firstSearchPerformed = true;
            $scope.productSearchResultsLoading = true;

            var paramsString = '?searchTerm=boots&totalPrice=' + $scope.totalMaxCost + '&numOfItems='+ $scope.productCount;

            $http({
                method: 'GET',
                url: 'http://127.0.0.1:8080/api/products'+ paramsString,
                headers: {}
            }).success(function(data){
                $scope.productSearchResultsList = data.productCombosList;
                $scope.productSearchResultsTotalPriceList = data.totalPriceList;

                $scope.productSearchResultsLoading = false;
            }).error(function(){
                console.log("Error get products by term.");
                $scope.productSearchResultsLoading = false;
            });
        };

        $scope.getTotalPriceForCombo = function() {

        };

        });
    });
