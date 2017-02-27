define(contextPath+'/js/service/request-service.js', ['main-app'], function(mainApp) {

    var RequestService = {
    };

   var sendRequest = function (url, method, data, header) {
       header['Cache-Control'] = 'no-cache';
       header['x-auth-token'] = getToken();
       var defer =$.Deferred();
        $.ajax(
            {
                url: contextPath+url,
                type: method,
                data: data,
                headers: header,
                dataType: 'json',
                traditional: true
            }).done(function(response){
            defer.resolve(response);
        }).fail(function(jqXHR, textStatus, errorThrown){
            if(jqXHR.responseText){
                var reponse = JSON.parse(jqXHR.responseText);
                defer.reject(reponse);
            }else{
                defer.reject({ex_code:'request-service.js',message:'未知错误'});
            }
        });
       return defer.promise();
    };

    RequestService.get = function (url, data, header) {
        if (!header) {
            header = {};
        }
        return sendRequest(url, 'GET', data, header);
    };

    RequestService.post = function (url, data, header) {
        if (!header) {
            header = {};
            header['Content-Type'] = 'application/x-www-form-urlencoded;charset=UTF-8';
        }
        return sendRequest(url, 'POST', data, header);
    };

    RequestService.put = function (url, data, header) {
        if (!header) {
            header = {};
            header['Content-Type'] = 'application/x-www-form-urlencoded;charset=UTF-8';
        }
        return sendRequest(url, 'PUT', data, header);
    };

    RequestService.deleteRequest = function (url, data, header) {
        if (!header) {
            header = {};
            header['Content-Type'] = 'application/x-www-form-urlencoded;charset=UTF-8';
        }
        return sendRequest(url, 'DELETE', data, header);
    };

    var sendFileRequest = function (url, method, data) {
        return $.ajax(
                {
                    url: contextPath+url,
                    type: method,
                    data: data,
                    processData: false,
                    contentType: false
                });
    };

    RequestService.postMultipart = function (url,data) {
        return sendFileRequest(url,'POST',data);
    };
    function updateToken(token) {
        if (localStorage.getItem("userInfo")) {
            var userInfo = JSON.parse(localStorage.getItem("userInfo"));
            userInfo.tokenSecret = token;
            localStorage.setItem("userInfo", JSON.stringify(userInfo));
        }
    }
    function getToken() {
        if (localStorage.getItem("userInfo")) {
            var userInfo = JSON.parse(localStorage.getItem("userInfo"));
            return userInfo && userInfo.tokenSecret;
        }
    }
    return RequestService;
});

