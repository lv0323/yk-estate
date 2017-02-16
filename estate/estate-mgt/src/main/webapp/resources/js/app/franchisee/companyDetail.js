/**
 * Created by yanghong on 2/10/17.
 */
require(['main-app',contextPath + '/js/service/company-service.js','datepicker.zh-cn'],
    function (mainApp, CompanyService) {
        var header = {};
        var companyId = window.location.href.split('?')[1];

        CompanyService.getSpecifiedCompany(companyId, header)
            .done(function (company) {
                $('#editCompanyName').val(company["name"]);
                $('#editCompanyId').val(company["id"]);
                $('#editCompanySpell').val(company["shortName"]);
                $('#editCompanyLicense').val(company["license"]);
                $('#editCompanyAddress').val(company["address"]);
                $('#editCompanyNote').val(company["introduction"]);
                $('#editCompanyStartDate').val(company["startDate"]);
                $('#editCompanyEndDate').val(company["endDate"]);
                if(company["locked"]){
                    $('#editCompanyLocked').text("已冻结");
                }else {
                    $('#editCompanyLocked').text("未冻结");
                }
                /*$('#companyRepName').val();
                $('#companyRepGender input:checked').val();
                $('#companyRepMobile').val();
                $('#companyRepID').val();
                $('#companyRepWechat').val();*/
            });

        //get checked gender
        /*$('#companyRepGender').on('click','input',function(){
            $(this).attr('checked','checked');
            $(this).siblings().removeAttr('checked','checked');
        });*/

        //initialize title and default value in edit Company dialog
        /*$('#companyList').on('click','#editCompanyBtn',function(e) {
            var index = $(e.target).data('index');
            var company = companyAllDataRaw[index];
            $('#editCompanyLabel').text('编辑公司');
            $('#editCompanyName').val(company["name"]);
            $('#editCompanyId').val(company["id"]);
            $('#editCompanySpell').val(company["shortName"]);
            $('#editCompanyLicense').val(company["license"]);
            $('#editCompanyAddress').val(company["address"]);
            $('#editCompanyNote').val(company["introduction"]);
        });
*/
        //action for updated Company
        $('#editCompanyDialog').on('click','#confirmEditCompanyBtn',function(){
            var toEditCompany = {
                id: $('#editCompanyId').val(),
                name: $('#editCompanyName').val(),
                shortName: $('#editCompanySpell').val(),
                license: $('#editCompanyLicense').val(),
                address: $('#editCompanyAddress').val(),
                introduction: $('#editCompanyNote').val()
            };

            CompanyService.editCompany({data:toEditCompany},header)
                .done(function(){
                    //location.reload(true);
                    window.location.href="/mgt/franchisee/company.ftl";
                })
                .fail(function () {
                    alert("请填写所有必填字段，内容不宜过长");
                });

        });


    });