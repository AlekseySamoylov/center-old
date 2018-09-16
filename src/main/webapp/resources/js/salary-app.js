/**
 * Created by alekseysamoylov on 8/6/16.
 */
(function () {
    var app = angular.module("SalaryModule", []);


    app.controller("SalaryController", function ($scope, $http) {
        $scope.hello = "Hello!!!";
        $scope.salaryPercent = 50;
        $scope.returnSum = function (employee) {
            var sum = 0;
            employee.works.forEach(function (work) {
                sum += work.price;
            });
            return sum;
        };
        // $scope.employees = [
        //     {
        //         name: "Vasiliy",
        //         works: [
        //             {workName: "Repair Engine", workPrice: 400.0},
        //             {workName: "Repair Wheel", workPrice: 200.0},
        //             {workName: "Repair Transmission", workPrice: 2000.0}]
        //     },
        //     {
        //         name: "Petr",
        //         works: [
        //             {workName: "Repair Engine", workPrice: 500},
        //             {workName: "Repair Wheel", workPrice: 800},
        //             {workName: "Repair Transmission", workPrice: 1000}]
        //     },
        //     {
        //         name: "Dmitriy",
        //         works: [
        //             {workName: "Repair Engine", workPrice: 500},
        //             {workName: "Repair Wheel", workPrice: 100},
        //             {workName: "Repair Transmission", workPrice: 4000}]
        //     }
        //
        // ];

        $http.get('http://alekseysamoylov.com:8080/center-1.1/service-panel/salary')
            .then(function (response) {
                console.log(response.data);
                $scope.employees = response.data;
            });
        // $http({
        //     method: 'GET',
        //     url: 'http://localhost:18090/sss/service-panel/salary'
        // }).then(function(response) {
        //     $scope.employees = response.data;
        // });


    });
})();
