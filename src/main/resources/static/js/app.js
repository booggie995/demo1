var app = angular.module('userregistrationsystem', [ 'ngRoute', 'ngResource' ]);

app.config(function($routeProvider) {

	$routeProvider.when('/', {
		templateUrl : 'template/home.html',
		controller : 'homeController'
	}).when('/list-all-users', {
		templateUrl : 'template/userList.html',
		controller : 'listUserController'
	}).when('/register-new-user', {
		templateUrl : 'template/userRegistration.html',
		controller : 'registerUserController'
	}).when('/update-user/:id', {
		templateUrl : 'template/userEdit.html',
		controller : 'usersDetailsController'
	}).when('/login', {
		templateUrl : 'template/login.html',
		controller : 'loginController'
	}).when('/logout', {
		templateUrl : 'template/login.html',
		controller : 'logoutController'
	}).otherwise({
		redirectTo : '/login'
	});
	;
});

app.config(['$httpProvider', function($httpProvider) {
	$httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
	}]);

/*app.config([ '$httpProvider', function($httpProvider) {
	$httpProvider.interceptors.push('AuthInterceptor');
} ]);*/