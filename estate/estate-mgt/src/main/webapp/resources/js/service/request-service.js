define(contextPath+'/js/service/request-service.js', ['main-app'], function(mainApp) {

    var RequestService = {
    };

   var sendRequest = function (url, method, data, header) {
       header['Cache-Control'] = 'no-cache';
        return $.ajax(
            {
                url: contextPath+url,
                type: method,
                data: data,
                headers: header,
                dataType: 'json'
            });
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

    return RequestService;
});

