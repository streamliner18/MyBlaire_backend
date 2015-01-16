$.extend($.fn.validatebox.defaults.rules, {
			CHS : {
				validator : function(value, param) {
					return /^[\u0391-\uFFE5]+$/.test(value);
				},
				message : '请输入汉字'
			},
			mobile : {
				validator : function(value, param) {
					if ($.trim(value).length != 11) {
						return false;
					} else {
						 return /^\d{11}$/.test(value);
					}
				},
				message : '手机号码不正确'
			},
			// 身份证
			IdentityCard : {
				validator : function(value, param) {
					return myObj.idCardValidate(value);
				},
				message : '请输入有效的身份证号码'
			},
			email : {
				validator : function(value) {
					return /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/
							.test(value);
				},
				message : '请输入有效的电子邮件账号(例：abc@126.com)'
			},
			number: {
				validator : function(value) {
					return /^[0-9]*$/
							.test(value);
				},
				//外界传值
				message : '{0}'
			},
			lengthXY: {
				validator : function(value,param) {
					var len = $.trim(value).length;
					return len >= param[0] && len <= param[1];
				},
				message : '{2}'
			},
			uri: {
				validator : function(value) {
					return myObj.uri($.trim(value));
				},
				message : '请输入正确的URL地址'
			}
		});
//身份证验证
var myObj = (function() {
	var Wi = [7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1], // 加权因子
	ValideCode = [1, 0, 10, 9, 8, 7, 6, 5, 4, 3, 2]; // 身份证验证位值.10代表X
	var strRegex = "^((https|http|ftp|rtsp|mms)://)?[a-z0-9A-Z]{3}\.[a-z0-9A-Z][a-z0-9A-Z]{0,61}?[a-z0-9A-Z]\.com|net|cn|cc (:s[0-9]{1-4})?/$";
	var idCardValidateMethod = function(idCard) {
		idCard = trimMethod(idCard.replace(/ /g, "")); // 去掉字符串头尾空格
		if (idCard.length == 15) {
			return isValidityBrithBy15IdCardMethod(idCard); // 进行15位身份证的验证
		} else if (idCard.length == 18) {
			var a_idCard = idCard.split(""); // 得到身份证数组
			if (isValidityBrithBy18IdCardMethod(idCard)
					&& isTrueValidateCodeBy18IdCardMethod(a_idCard)) { // 进行18位身份证的基本验证和第18位的验证
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	var isTrueValidateCodeBy18IdCardMethod = function(a_idCard) {
		var sum = 0; // 声明加权求和变量
		if (a_idCard[17].toLowerCase() == 'x') {
			a_idCard[17] = 10; // 将最后位为x的验证码替换为10方便后续操作
		}
		for (var i = 0; i < 17; i++) {
			sum += Wi[i] * a_idCard[i]; // 加权求和
		}
		valCodePosition = sum % 11; // 得到验证码所位置
		if (a_idCard[17] == ValideCode[valCodePosition]) {
			return true;
		} else {
			return false;
		}
	}

	var isValidityBrithBy18IdCardMethod = function(idCard18) {
		var year = idCard18.substring(6, 10);
		var month = idCard18.substring(10, 12);
		var day = idCard18.substring(12, 14);
		var temp_date = new Date(year, parseFloat(month) - 1, parseFloat(day));
		if (temp_date.getFullYear() != parseFloat(year)
				|| temp_date.getMonth() != parseFloat(month) - 1
				|| temp_date.getDate() != parseFloat(day)) {
			return false;
		} else {
			return true;
		}
	}
	var trimMethod = function trim(str) {
		return str.replace(/(^\s*)|(\s*$)/g, "");
	}

	var isValidityBrithBy15IdCardMethod = function(idCard15) {
		var year = idCard15.substring(6, 8);
		var month = idCard15.substring(8, 10);
		var day = idCard15.substring(10, 12);
		var temp_date = new Date(year, parseFloat(month) - 1, parseFloat(day));
		// 对于老身份证中的你年龄则不需考虑千年虫问题而使用getYear()方法
		if (temp_date.getYear() != parseFloat(year)
				|| temp_date.getMonth() != parseFloat(month) - 1
				|| temp_date.getDate() != parseFloat(day)) {
			return false;
		} else {
			return true;
		}
	}
	var maleOrFemalByIdCardMethod = function(idCard) {
		idCard = trimMethod(idCard.replace(/ /g, "")); // 对身份证号码做处理。包括字符间有空格。
		if (idCard.length == 15) {
			if (idCard.substring(14, 15) % 2 == 0) {
				return 'female';
			} else {
				return 'male';
			}
		} else if (idCard.length == 18) {
			if (idCard.substring(14, 17) % 2 == 0) {
				return 'female';
			} else {
				return 'male';
			}
		} else {
			return null;
		}
	}
	
	var isURL = function (value) {
            var re = new RegExp(strRegex);
            if (re.test(value)) {
                return true;
            } else {
                 return false;
            }
        }
	
	
	return {
		//身份证验证方法
		idCardValidate : idCardValidateMethod,
		//男女验证,从身份证中获取验证
		maleOrFemalByIdCard : maleOrFemalByIdCardMethod,
		uri:isURL
		
	}

	
	
	
	
	
}())
