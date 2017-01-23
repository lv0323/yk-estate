require(['main-app', 
    contextPath + '/js/service/identity-service.js',
    contextPath + '/js/service/employee-service.js'],
    function (mainApp, IdentityService, EmployeeService) {
        /*显示右上角用户信息*/
        var userInfo = JSON.parse(localStorage.getItem('userInfo'));
        if(userInfo &&userInfo.id){
            $('.navbar-custom-menu .username').text(userInfo.name);
            //todo 获取用户头像--等待接口
            /*EmployeeService.getAvatar({id:userInfo.id}).then(function(response){
                console.log(response);
            });*/
        }
        $('body').on('click', '.btn-logout', function(){
            IdentityService.logout().done(function(response){
                window.location.replace('/mgt/index');
            });
        });
    });