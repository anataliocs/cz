'use strict';

angular.module('canataliozapposApp')
    .factory('Register', function ($resource) {
        return $resource('api/register', {}, {
        });
    });


