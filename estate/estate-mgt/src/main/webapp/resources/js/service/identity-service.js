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

        IdentityService.login = function (params) {
            var opts = {};
            var password = utilService.generateSignature(utilService.generateSignature(params.password, params.salt), params.sugar);
            opts.password= password;
            opts.mobile= params.mobile;
            return requestService.get('/api/employee/login', opts, {});
        };

        IdentityService.logout = function () {
            return requestService.get('/auth/logout');
        };
        IdentityService.getSalt = function(param){
          return requestService.get('/api/employee/salt',param);
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
                    IdentityService.login(opts, header).done(function(response){
                        var userInfo = {
                            tokenSecret: response.token
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

        return IdentityService;
    });
