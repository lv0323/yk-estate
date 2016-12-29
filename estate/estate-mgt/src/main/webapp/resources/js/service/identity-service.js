define(contextPath + '/js/service/identity-service.js',
    ['main-app', contextPath + '/js/service/util-service.js', contextPath + '/js/service/request-service.js'],
    function (mainApp, utilService, requestService) {

        var IdentityService = {};
/*
        IdentityService.findByClaim = function (params) {
            var data = {type: 'MOBILE', cid: params.cid};
            var header = {
                'X-VALUE': encodeURIComponent(params.username)
            };
            return requestService.get('/auth/identities/', data, header);
        };

        IdentityService.login = function (params) {
            var opts = {};
            var nonce = utilService.generateSalt(16);
            var ts = Math.floor(Date.now() / 1000) - parseInt(localStorage.getItem("localTime")) + parseInt(localStorage.getItem("serverTime"));
            var signature = utilService.generateSignature(nonce, ts, params.cid, params.uid, utilService.hashNoPadding(params.salt + ' ' + params.secret));
            var imageId = localStorage.getItem("imageId");
            opts.hash = signature;
            opts.nonce = nonce;
            opts.cid = params.cid;
            opts.uid = params.uid;
            opts.ts = ts;
            opts.imageId = imageId;
            opts.a = params.a;
            return requestService.post('/auth/login', opts, {});
        };

        IdentityService.logout = function () {
            return requestService.get('/auth/logout');
        };*/

        return IdentityService;
    });
