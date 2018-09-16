/**
 * Created by alekseysamoylov on 8/13/16.
 */
(function () {
    'use strict';
    var app = angular.module("registrationModule", []);
    var check;
    var checkModel;

    app.controller("registrationController", function ($scope, $http) {
        $scope.user = {
            firstName : "",
            lastName : "",
            email : "",
            login : "",
            password : ""
        };
        $scope.loginList = [];
        $scope.noExistInList = true;
        $scope.allChecked = false;
        // $scope.user.firstName = "";
        // $scope.user.lastName = "";
        // $scope.user.login = "";
        // $scope.user.password = "";
        $scope.pass2 = "";
        $scope.checkPass1 = false;
        $scope.checkPass2 = false;
        $scope.checkPass3 = false;
        $scope.checkFN = false;
        $scope.checkLN = false;
        $scope.checkEM = false;

        $http.get("http://localhost:18090/sss/login-list")
            .then(function (response) {
                $scope.loginList = response.data;
                $scope.checkModel = function () {
                    $scope.noExistInList = !contains($scope.loginList, $scope.user.login);
                }
            });

        $scope.check1 = function () {
            $scope.checkFN = !($scope.user.firstName.length >= 2 && $scope.user.firstName.length < 20);
            check();
        };
        $scope.check2 = function () {
            $scope.checkLN = !($scope.user.lastName.length >= 2 && $scope.user.lastName.length < 20);
            check();
        };
        $scope.check3 = function () {
            $scope.checkLogin = !($scope.user.login.length > 3 && $scope.user.login.length < 20);
            check();
        };
        $scope.check4 = function () {
            $scope.checkPass1 = !($scope.user.password.length > 5 && $scope.user.password.length < 20);
            $scope.scheckPass3 = !($scope.user.password === $scope.pass2);
            check();
        };
        $scope.check5 = function () {
            $scope.checkPass2 = !($scope.pass2.length > 5 && $scope.pass2.length < 20);
            $scope.checkPass3 = !($scope.user.password === $scope.pass2);
            check();
        };

        check = function () {
            $scope.allChecked = (!$scope.checkFN && !$scope.checkLN
            && !$scope.checkLogin && !$scope.checkPass1
            && !$scope.checkPass2 && !$scope.checkPass3
            && $scope.user.firstName.length >= 2
            && $scope.user.lastName.length >= 2
            && $scope.user.login.length > 3
            && $scope.user.email.length > 3
            && $scope.user.password.length > 5
            && $scope.pass2.length > 5
            && $scope.user.password === $scope.pass2);
        };



        $scope.saveUser = function() {
            window.alert("send" + $scope.user);
            $http.post("http://localhost:18090/sss/login-list", $scope.user).
            success(function(data, status, headers, config) {
                // this callback will be called asynchronously
                // when the response is available
                console.log(data);
            }).
            error(function(data, status, headers, config) {
                // called asynchronously if an error occurs
                // or server returns response with an error status.
            });
        }

    });
    app.directive('myEnter', function () {
        return function (scope, element, attrs) {

            element.bind("keyup", function (event) {
                scope.check5();
                scope.$apply();
                // if(event.which === 13) {
                //     scope.$apply(function (){
                //
                //         scope.$eval(attrs.myEnter);
                //     });
                //     event.preventDefault();
                // }
            });
        };
    });

    app.directive('myLogin', function () {
        return function (scope, element, attrs) {
            element.bind("keyup", function (event) {
                scope.checkModel();
                    scope.$apply();
            });
        };
    });


})();
function contains(array, entry) {
    var i = array.length;
    while (i--) {
        if (array[i].toLowerCase() === entry.toLowerCase()) {
            return true;
        }
    }
    return false;
}