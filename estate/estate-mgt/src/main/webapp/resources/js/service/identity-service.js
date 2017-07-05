define(contextPath + '/js/service/identity-service.js',
    ['main-app', contextPath + '/js/service/util-service.js', contextPath + '/js/service/request-service.js'],
    function (mainApp, utilService, requestService) {

        var IdentityService = {};
        IdentityService.findByClaim = function (params) {
            var data = {type: 'MOBILE', cid: params.cid};
            var header = {
                'X-VALUE': encodeURIComponent(params.username)
            };
            return requestService.get('/auth/identities/', data, header);
        };

        IdentityService.login = function (params, header) {
            var opts = {};
            var password = utilService.generateSignature(utilService.generateSignature(params.password, params.salt), params.sugar);
            opts.password= password;
            opts.mobile= params.mobile;
            return requestService.get('/api/auth/login', opts, header||{});
        };

        IdentityService.logout = function () {
            var defer =$.Deferred();
            requestService.get('/api/auth/logout').done(function(response){
                localStorage.removeItem('userInfo');
                defer.resolve(response);
            }).fail(function(response){
                defer.reject(response);
            });
            return defer.promise();
        };
        IdentityService.getSalt = function(param){
          return requestService.get('/api/auth/salt',param);
        };
        IdentityService.loginAction = function (params, header) {
            var defer =$.Deferred();
            IdentityService.getSalt({mobile: params.mobile}).then(function (response) {
                if(response.salt && response.sugar){
                    var opts = {
                        sugar:response.sugar,
                        salt: response.salt,
                        password:params.password,
                        mobile: params.mobile
                    };

                    IdentityService.login(opts, {'X-CAPTCHA': "id=" + header.id +"&clientId="+ header.clientId +"&code="+ header.code}).done(function(response){
                        var userInfo = {
                            tokenSecret: response.token,
                            id: response.user &&response.user.id,
                            name: response.user &&response.user.name,
                            company_id: response.user &&response.user.company_id,
                        };
                        localStorage.setItem("userInfo", JSON.stringify(userInfo));
                         defer.resolve(response);
                    }).fail(function(response){
                         defer.reject(response);
                    });
                }else{
                    defer.reject(response);
                }
            }).fail(function (response) {
                defer.reject(response);
            });
            return defer.promise();
        };

        IdentityService.sendSMS = function (params) {
            var header = {
                'X-CAPTCHA': "id=" + params.id +"&clientId="+ params.clientId +"&code="+ params.code,
                'Content-Type':'application/json;charset=utf-8'
            };
            var data ={
                mobile: params.mobile, type: params.type
            }
            return requestService.post('/sms', JSON.stringify(data), header);
        };

        IdentityService.activate = function (params, header) {

            return requestService.post('/api/auth/active', params, header);
        };
        IdentityService.unActivated = function (params, header) {
            return requestService.get('/api/auth/unActivated', params, header);
        };

        return IdentityService;
    });
