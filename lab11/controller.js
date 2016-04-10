var app = angular.module('library', ['ngRoute']);

app.config(['$routeProvider', '$locationProvider', function($routeProvider, $locationProvider) {
        $routeProvider

            .when('/', {
                templateUrl : 'home.html',
                controller  : 'controller'
            })

            .when('/library', {
                templateUrl : 'library.html',
                controller  : 'controller'
            })

            .when('/shelf', {
                templateUrl : 'shelf.html',
                controller  : 'controller'
            });

            $locationProvider.html5Mode({
			  enabled: true,
			  requireBase: false
			});
}]);

app.controller('controller', ['$scope', function($scope) {

	$scope.library = new Library();

	//populate library with preselected books
	$scope.library.premade();

}]);