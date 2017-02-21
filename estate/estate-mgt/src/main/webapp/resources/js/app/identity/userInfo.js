require(['main-app', 
    contextPath + '/js/service/identity-service.js',
    contextPath + '/js/service/employee-service.js',
    contextPath + '/js/service/util-service.js'],
    function (mainApp, IdentityService, EmployeeService, UtilService) {
        /*显示右上角用户信息*/
        var userInfo = JSON.parse(localStorage.getItem('userInfo'));
        var user = $('.navbar-custom-menu .username');


        if(userInfo && userInfo.id && user.length>0){
            $('.navbar-custom-menu .username').text(userInfo.name);
            $('.user-panel .info .username').text(userInfo.name);
            EmployeeService.getAvatar({id:userInfo.id}).then(function(response){
                if(response && response.url){
                 $('.user-menu .user-image,.user-menu .img-circle').attr('src',response.url).show(0);
                 $('.user-panel .user-panelImg >i').css('display','none');
                    $('.user-panel .user-panelImg >img').attr('src',response.url).show(0);
                }
            });
            $('body').on('click', '.btn-logout', function(){
                IdentityService.logout().done(function(response){
                    window.location.replace('/mgt/index');
                });
            });
        }
        /*根据url自动展示对应的导航*/
        var target =UtilService.getUrlParam('target');
        if(target){
            $('.treeview').filter(target).find('>a').click();
        }

    });