/**
 * Created by yanghong on 1/23/17.
 */
require(['main-app',contextPath + '/js/service/company-service.js',
        contextPath + '/js/plugins/pagination/pagingPlugin.js',
        contextPath + '/js/utils/dataTableHelp.js',
    'jqPaginator','datepicker.zh-cn'],
    function (mainApp, CompanyService, pagingPlugin, dataTableHelp) {

        var header = {};
        var companyAllDataRaw = {};

        var pageConfig = {
            limit: 8,
            init: false
        };
        var tableConfig ={
            init: false,
            target:null
        };


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

        var displayTable = function (data) {
            companyAllDataRaw = data.items;
            var dataSet =data.items.map(function(item,index){
                return {
                    companyName: {attr: {class: 'lookCompanyBtn btn'}, data: {id: item.id}, text: item.name},
                    secretKey: item.secretKey,
                    startDate: item.startDate,
                    endDate: item.endDate,
                    operation: [
                        {attr: {class: 'btn', id: 'hehe'}, data: {id: item.id}, text: '编辑'},
                        {attr: {class: 'btn'}, data: {id: item.id}, text: '续约'},
                        {attr: {class: 'btn'}, data: {id: item.id}, text: '冻结'}]
                }
            });

            if(!tableConfig.target){
                tableConfig.target = $('#companyList').DataTable( {
                    data: dataSet,
                    paging: false,
                    searching: false,
                    info: false,
                    ordering: false,
                    autoWidth: false,
                    columnDefs: [
                        { className: "text-right", "targets": [ 4 ] } /*添加class*/
                    ],
                    columns: [
                        { title: "公司名称",data:'companyName',defaultContent: "", "render": dataTableHelp.operationFormat()},
                        { title: "公司授权号" ,data:'secretKey', defaultContent: ""},
                        { title: "加盟有效起始日期" ,data:'startDate', defaultContent: ""},
                        { title: "加盟有效截止日期" ,data:'endDate', defaultContent: ""},
                        { title: "操作", data:'operation', "render": dataTableHelp.operationFormat()}
                    ]
                });
            }else{
                tableConfig.target.clear();
                tableConfig.target.rows.add(dataSet).draw();
            }
            /*var table = $('#companyList').DataTable({
             "bProcessing": true,
             "bServerSide": true,
             "sAjaxDataProp": "",
             "ajax":{
             url: contextPath+'/api/company/query',
             type: "get",
             data: null,
             headers: header,
             dataType: 'json'
             },
             "aoColumns": [
             { "mData": "name" },
             { "mData": "boss.name" },
             { "mData": "address" }
             ],
             "paging": true,
             "lengthChange": true,
             "searching": false,
             "ordering": false,
             "info": false,
             "autoWidth": false
             });*/
        };

        var pagination = function(dataTotal) {
            if(pageConfig.init){
                return;
            }
            pageConfig.init = true;
            var config = {
                pagingId:'#companyList_paging',
                totalCounts:dataTotal,
                pageSize: pageConfig.limit,
                onChange: function (num, type) {
                    if(type === 'init'){
                        return;
                    }
                    getCompany((num-1)*pageConfig.limit, pageConfig.limit);
                }
            };
            pagingPlugin.init(config);

        };


        //get data from server and display data
        function getCompany(offset, limit) {
            CompanyService.getCompany({'x-paging': 'total=true&offset='+offset+'&limit=' + limit})
                .done(function(data){
                    displayTable(data);
                    pagination(data.total);
                })
                .fail(function () {
                    $('#companyList>tbody').append('<tr><td colspan="4">无法获取数据</td></tr>');
                });
        }
        //initialize table when page loading
         getCompany(0, pageConfig.limit);



        //open new window for company details
        $('#companyList').on('click','#lookCompanyBtn',function(e){
            var companyId = $(e.target).data('id');
            window.location.href="/mgt/franchisee/companyDetail.ftl?"+companyId;
        });

        //open new window for Company details
        $('#companyList').on('click','#editCompanyBtn',function(e){
            var companyId = $(e.target).data('id');
            window.location.href="/mgt/franchisee/editCompanyDetail.ftl?"+companyId;
        });

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
            $('#companyEndDateRenew').val(company["endDate"]);
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