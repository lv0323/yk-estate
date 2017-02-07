/**
 * Created by yanghong on 1/17/17.
 */
require(['main-app',contextPath + '/js/service/organization-service.js'],
    function (mainApp,OrganizationService) {
        var BaseUrl = "/api/employee/";

        var header = {};

        //initialize title in add Position dialog
        $('.fadeInRight').on('click','#addEmployeeBtn',function(){
            $('#addEmployeeDialog #addEmployeeLabel').text('增加员工');
        });
    });