/**
 * Created by yanghong on 1/26/17.
 */
define(contextPath+'/js/service/organization-service.js', ['main-app',contextPath + '/js/service/request-service.js','datatables','datatablesBootstrap'],
    function (mainApp,RequestService) {

        var OrganizationService = {};

        OrganizationService.queryElement = function (params) {
            return RequestService.get(params.url, null, params.header);
        };

        OrganizationService.updatePostDepartment = function (params) {
            return RequestService.post(params.url,params.data,params.header);
        };

        OrganizationService.getElement = function (params) {
            return RequestService.get(params.url,params.data,params.header);
        };

        OrganizationService.iniDropList = function(url,superior_id,header,jqueryElementOuter,jqueryElementInner){
            RequestService.get(url,{id:superior_id},header)
                .done(function (data) {
                    var appendOption = '';
                    $.each(data, function (index, element) {
                        appendOption += '<option id="' + element.id + '">' + element.name + '</option>';

                    });
                    $('#'+jqueryElementOuter+' #'+jqueryElementInner).html(appendOption);
                });
        };

        return OrganizationService;
    });