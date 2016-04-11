var app = angular.module('library', ['ngRoute', 'ngSanitize']);

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

            .when('/shelves/:shelfId', {
                templateUrl: 'shelf.html',
                controller: 'controller'
            })

            .when('/shelves', {
                templateUrl : 'shelves.html',
                controller  : 'controller'
            })

            .when('/shelves/:shelfId/books/:bookId', {
                templateUrl: 'book.html',
                controller: 'controller'
            });

            $locationProvider.html5Mode({
			  enabled: true,
			  requireBase: false
			});
}]);

app.filter('html', ['$sce', function ($sce) { 
    return function (code) {
        return $sce.trustAsHtml(code);
    };    
}])

app.controller('controller', ['$scope', '$routeParams', function($scope, $routeParams) {
    $scope.shelfId = $routeParams.shelfId;      
    $scope.bookId = $routeParams.bookId;

	$scope.library = new Library();

	//populate library with preselected books
	$scope.library.premade();

    $scope.getItem = function(shelf, row){
        if($scope.library.shelves[shelf].books.length > row){
            return "<a href='/shelves/" + shelf + "/books/" + row + "'>" + $scope.library.shelves[shelf].books[row].title + "</a>";
        }
        else{
            return "";
        }
    };

    $scope.bookIndex = $scope.library.bookIndex();
    $scope.shelfIndex = $scope.library.shelfIndex();

}]);