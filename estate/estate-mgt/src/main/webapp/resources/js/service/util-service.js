define(contextPath + '/js/service/util-service.js',
    ['main-app', 'cryptojs.hmac', 'cryptojs.md5', contextPath + '/js/service/i18n-service.js'],
    function (mainApp) {

    var UtilService = {};

    UtilService.generateSalt = function (length) {
        return randomStr(length);
    };

    UtilService.generateSignature = function () {
        var message = '';
        var args = Array.prototype.slice.call(arguments);
        args.forEach(function (element, index, array) {
            message = message + element;
        });
        return CryptoJS.HmacMD5(arguments[0],arguments[1]).toString(CryptoJS.enc.Hex);
    };

    UtilService.goTo = function (url) {
        window.location.href = contextPath + url;
    };

    function randomStr(length) {
        var charset = '0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ-_.';
        var result = '';
        for (var i = length; i > 0; --i) result += charset[Math.round(Math.random() * (charset.length - 1))];
        return result;
    }

    function b64url(input, b64pad) {
        var tab = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_',
            output = '',
            len = input.length,
            i, j, triplet;
        b64pad = b64pad || '=';
        for (i = 0; i < len; i += 3) {
            triplet = (input.charCodeAt(i) << 16) | (i + 1 < len ? input.charCodeAt(i + 1) << 8 : 0) | (i + 2 < len ? input.charCodeAt(i + 2) : 0);
            for (j = 0; j < 4; j += 1) {
                if (i * 8 + j * 6 > input.length * 8) {
                    output += b64pad;
                } else {
                    output += tab.charAt((triplet >>> 6 * (3 - j)) & 0x3F);
                }
            }
        }
        return output;
    }

    UtilService.timeStamp2Datetime = function (time) {
        var datetime = new Date();
        datetime.setTime(time);
        var year = datetime.getFullYear();
        var month = datetime.getMonth() + 1 < 10 ? "0" + (datetime.getMonth() + 1) : datetime.getMonth() + 1;
        var date = datetime.getDate() < 10 ? "0" + datetime.getDate() : datetime.getDate();
        var hour = datetime.getHours() < 10 ? "0" + datetime.getHours() : datetime.getHours();
        var minute = datetime.getMinutes() < 10 ? "0" + datetime.getMinutes() : datetime.getMinutes();
        var second = datetime.getSeconds() < 10 ? "0" + datetime.getSeconds() : datetime.getSeconds();
        return year + "-" + month + "-" + date + " " + hour + ":" + minute + ":" + second;
    }

    UtilService.timeStamp2Date = function (time) {
        var datetime = new Date();
        datetime.setTime(time);
        var year = datetime.getFullYear();
        var month = datetime.getMonth() + 1 < 10 ? "0" + (datetime.getMonth() + 1) : datetime.getMonth() + 1;
        var date = datetime.getDate() < 10 ? "0" + datetime.getDate() : datetime.getDate();
        return year + "-" + month + "-" + date;
    }

    UtilService.moneyFormat = function (amount, decimal) {
        decimal = decimal > 0 && decimal <= 20 ? decimal : 2;
        amount = parseFloat((amount + "").replace(/[^\d\.-]/g, "")).toFixed(decimal) + "";
        var l = amount.split(".")[0].split("").reverse(),
            r = amount.split(".")[1];
        t = "";
        for (i = 0; i < l.length; i++) {
            t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "," : "");
        }
        return t.split("").reverse().join("") + "." + r;
    }

    UtilService.moneyToCny = function (n) {
        if (!/^(0|[1-9]\d*)(\.\d+)?$/.test(n))
            return "金额格式错误";
        var unit = "千百十亿千百十万千百十元角分", str = "";
        n += "00";
        var p = n.indexOf('.');
        if (p >= 0)
            n = n.substring(0, p) + n.substr(p + 1, 2);
        unit = unit.substr(unit.length - n.length);
        for (var i = 0; i < n.length; i++)
            str += '零一二三四五六七八九'.charAt(n.charAt(i)) + unit.charAt(i);
        return str.replace(/零(千|百|十|角)/g, "零").replace(/(零)+/g, "零").replace(/零(万|亿|元)/g, "$1").replace(/(亿)万|一(十)/g, "$1$2").replace(/^元零?|零分/g, "").replace(/元$/g, "元整");
    };

    UtilService.typeListWrap = function (types) {
        if(!types){
            return '';
        }
        var result = [];
        for (var i=0;i<types.length;i++) {
            result.push(i18nService.translateType(types[i]));
        }
        return result.join(",");
    };

    UtilService.checkMobile = function (mobile) {
        if(!(/^1[0-9]{10}$/.test(mobile))){
            return false;
        }else{
            return true;
        }
    }
    UtilService.characterTransformation=function (str) {
        var result="";
        if(str==null||str==""){
            return "";
        }
        for(var i=0;i<str.length;i++)
        {
            code = str.charCodeAt(i)
            if (code >= 65281 && code <= 65373)
            {
                var d=str.charCodeAt(i)-65248;
                result += String.fromCharCode(d);
            }
            else if (code == 12288)
            {
                var d=32;
                result += String.fromCharCode(d);
            }
            else
            {
                result += str.charAt(i);
            }
        }
        return result; 
        

    }
    return UtilService;
});
