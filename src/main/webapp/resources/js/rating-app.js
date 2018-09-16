/**
 * Created by alekseysamoylov on 7/31/16.
 */
(function () {
    angular.module("ratingModule", [])
        .controller("RatingController", function ($scope) {
           $scope.stars = 5;
        });
})();