var exec = require('cordova/exec');

exports.alert = function (arg0, success, error) {
    exec(success, error, 'OpenSimpleDialog', 'alert', [arg0]);
};

exports.openDateTimePicker = function (arg0, success, error) {
    exec(success, error, 'OpenSimpleDialog', 'openDateTimePicker', [arg0]);
};