/**
 * Created by yanghong on 1/23/17.
 */
require(['main-app',contextPath + '/js/service/company-service.js','datatables','datatablesBootstrap','datepicker.zh-cn'],
    function (mainApp,CompanyService) {

        var header = {};
        var companyAllDataRaw = {};

        $.fn.datepicker.defaults.language = "zh-CN";
        $('#addCompanyStartDate').datepicker({
            todayHighlight:true,
            startDate: '+1d',
            autoclose: true
        });
        $('#addCompanyEndDate').datepicker({
            todayHighlight:true,
            startDate: '+1d',
            autoclose: true
        });
        $('#companyEndDateRenew').datepicker({
            todayHighlight:true,
            startDate: '+1d',
            autoclose: true
        });

        //get data from server and display data
        CompanyService.getCompany(header)
            .done(function (data) {
                companyAllDataRaw = data;
                var appendHtml = '';
                $.each(data,function (index, companyRaw) {
                    appendHtml = '<tr>' +
                        '<td><a class="btn" id="lookCompanyBtn" href="/mgt/franchisee/companyDetail.ftl" data-id="'+companyRaw["id"]+'">'+companyRaw["name"]+'</a></td>' +
                        '<td>'+companyRaw.boss["name"]+'</td>' +
                        '<td>'+companyRaw["address"]+'</td>' +
                        '<td class="text-right">' +
                        '<a class="btn" id="editCompanyBtn" data-index="'+index+'" data-id="'+companyRaw["id"]+'" data-toggle="modal" data-target="#editCompanyDialog">编辑</a>' +
                        '<span class="opt-gap"></span>'+
                        '<a class="btn" id="renewCompanyBtn" data-index="'+index+'" data-id="'+companyRaw["id"]+'" data-toggle="modal" data-target="#renewCompanyDialog">续约</a>'+
                        '<span class="opt-gap"></span>';

                    if(companyRaw["locked"]){
                        appendHtml += '<a class="btn" id="toggleLockCompanyBtn" data-index="'+index+'" data-id="'+companyRaw["id"]+'" data-toggle="modal" data-target="#toggleLockCompanyDialog">解冻</a></td>'+
                            '</tr>';
                    }else {
                        appendHtml += '<a class="btn" id="toggleLockCompanyBtn" data-index="'+index+'" data-id="'+companyRaw["id"]+'" data-toggle="modal" data-target="#toggleLockCompanyDialog">冻结</a></td>'+
                            '</tr>';
                    }
                    $('#companyList>tbody').append(appendHtml);

                });

                $('#companyList').DataTable({
                    "paging": true,
                    "lengthChange": false,
                    "searching": false,
                    "ordering": false,
                    "info": false,
                    "autoWidth": false
                });
            })
            .fail(function () {
                $('#companyList>tbody').append('<tr><td colspan="4">无法获取数据</td></tr>');
            });

        $('.fadeInRight').on('click','#lookCompanyBtn',function (e) {
            var id = $(e.target).data('id');
        });

       //initialize title in add Company dialog
       //  $('.fadeInRight').on('click','#addCompanyBtn',function(){
       //      $('#addCompanyDialog #addCompanyLabel').text('创建公司');
       //  });

        //get checked gender
        $('#companyRepGender').on('click','input',function(){
            $(this).attr('checked','checked');
            $(this).siblings().removeAttr('checked','checked');
        });

        //action for added Company
        $('#addCompanyDialog').on('click','#confirmAddCompanyBtn',function(){
            var toAddData = {
                'company.name': $('#addCompanyName').val(),
                'company.shortName': $('#addCompanySpell').val(),
                'company.license': $('#addCompanyLicense').val(),
                'company.address': $('#addCompanyAddress').val(),
                'company.introduction': $('#addCompanyNote').val(),
                'company.startDate': $('#addCompanyStartDate').val(),
                'company.endDate': $('#addCompanyEndDate').val(),
                'boss.name': $('#companyRepName').val(),
                'boss.gender':$('#companyRepGender input:checked').val(),
                'boss.mobile':$('#companyRepMobile').val(),
                'boss.idcardNumber': $('#companyRepID').val(),
                'boss.wechat':$('#companyRepWechat').val()
            };

            CompanyService.addCompany({data:toAddData},header)
                .done(function(){
                    // location.reload(true);
                    window.location.href="/mgt/franchisee/company.ftl";
                })
                .fail(function () {
                    alert("请填写所有必填字段，内容不宜过长，加盟日期需为将来日期");
                });
        });


        //initialize title and default value in edit Company dialog
        $('#companyList').on('click','#editCompanyBtn',function(e) {
            var index = $(e.target).data('index');
            var company = companyAllDataRaw[index];
            $('#editCompanyLabel').text('编辑公司');
            $('#editCompanyName').val(company["name"]);
            $('#editCompanyId').val(company["id"]);
            $('#editCompanySpell').val(company["short_name"]);
            $('#editCompanyLicense').val(company["license"]);
            $('#editCompanyAddress').val(company["address"]);
            $('#editCompanyNote').val(company["introduction"]);
        });

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
                    location.reload(true);
                })
                .fail(function () {
                    alert("请填写所有必填字段，内容不宜过长");
                });

        });


        //initialize title in toggle lock Company dialog
        $('.fadeInRight').on('click','#toggleLockCompanyBtn',function(e){
            var index = $(e.target).data('index');
            var company = companyAllDataRaw[index];
            $('#toggleLockCompanyId').val(company["id"]);
            $('#toggleLockCompanyLocked').val(company["locked"]);
            if(company["locked"]){
                $('#toggleLockCompanyDialog .modal-body').html('确定要解冻吗？');
            }else {
                $('#toggleLockCompanyDialog .modal-body').html('确定要冻结吗？');
            }
        });

        //action for toggle lock Company
        $('#toggleLockCompanyDialog').on('click','#confirmToggleLockCompanyBtn',function(){
            var company_id = $('#toggleLockCompanyId').val();
            var company_locked_string = $('#toggleLockCompanyLocked').val();
            var toUpdateData ={};
            if(company_locked_string === 'true'){
                toUpdateData = {
                    id: company_id,
                    locked: 'false'
                }
            }else {
                toUpdateData = {
                    id: company_id,
                    locked: 'true'
                }
            }
            CompanyService.toggleLockCompany({data:toUpdateData},header)
                .done(function () {
                    location.reload(true);
                })
                .fail(function () {

                });
        });


        //initialize title in toggle lock Company dialog
        $('.fadeInRight').on('click','#renewCompanyBtn',function(e){
            var index = $(e.target).data('index');
            var company = companyAllDataRaw[index];
            $('#renewCompanyId').val(company["id"]);
            $('#companyEndDateRenew').val(company["end_date"]);
        });

        //action for toggle lock Company
        $('#renewCompanyDialog').on('click','#confirmRenewCompanyBtn',function(){
            var toUpdateData ={
                id: $('#renewCompanyId').val(),
                endDate: $('#companyEndDateRenew').val()
            };

            CompanyService.renewCompany({data:toUpdateData},header)
                .done(function () {
                    location.reload(true);
                })
        });



    });