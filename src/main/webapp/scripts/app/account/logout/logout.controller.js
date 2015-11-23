'use strict';

angular.module('canataliozapposApp')
    .controller('LogoutController', function (Auth) {
        Auth.logout();
    });
