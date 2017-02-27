define(contextPath+'/js/service/validation-service.js', ['main-app'], function(mainApp) {

    var ValidationService = {
    };
    ValidationService.isValidPhoneNumber = function(value) {
        var reg = /^1\d{10}$|^\d{3}-\d{8}$|^\d{4}-\d{7}$|^0\d{11}$|^\d{4}-\d{8}$/;
        return (reg.test(value));
    }

    ValidationService.isValidMoneyAmount =function(value) {
        var reg = /^(([0-9]+[\.]?[0-9]+)|[0-9])$/;
        return (reg.test(value));
    }

    ValidationService.isValidMoneyAmountDecimal = function(value, decimalLength) {
        var reg = new RegExp('^(([0-9]+[\.]?[0-9]{0,' + decimalLength + '})|[0-9])$')
        return (reg.test(value));
    }

    ValidationService.isValidBankCard= function(value) {
        var reg = /^\d{10,25}$/;
        return (reg.test(value));
    }

    ValidationService.isValidIdCard = function(value) {
        var reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
        return (reg.test(value));
    }

    ValidationService.isValidIntegerNum = function(value) {
        var reg = /^[1-9][0-9]*$/;
        return (reg.test(value));
    }
    return ValidationService;
});

