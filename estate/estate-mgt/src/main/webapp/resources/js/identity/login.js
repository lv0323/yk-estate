require(['main-app', contextPath + '/js/service/identity-service.js', contextPath + '/js/service/util-service.js'],
    function (mainApp, IdentityService, UtilService) {
        function loadCaptcha() {
            var imageId = UtilService.generateSalt(8);
            localStorage.setItem("imageId", imageId);
            $('#captcha-image').attr('src', '/api/captcha?clientId=1000&id=100&width=120&height=30&iid=' + imageId)
        }
        $('#captcha-image').on('click', loadCaptcha);
        loadCaptcha();
        $('#loginForm').on('submit',function(){
            return false;
        })
    });
/*根据浏览器特性判断是否支持*/
if(!+[1,]){
    document.getElementById("submit").disabled=true;
    document.getElementById("ie_error").style.display='block';
}