/*
 This file contains validations that are too specific to be part of the core
 Please reference the file AFTER the translation file or the rules will be overwritten
 Use at your own risk. We can't provide support for most of the validations
*/
(function($){
    if($.validationEngineLanguage == undefined || $.validationEngineLanguage.allRules == undefined )
        alert("Please include other-validations.js AFTER the translation file");
    else {
        $.validationEngineLanguage.allRules["postcodeUK"] = {
            // UK zip codes
            "regex": /^([A-PR-UWYZa-pr-uwyz]([0-9]{1,2}|([A-HK-Ya-hk-y][0-9]|[A-HK-Ya-hk-y][0-9]([0-9]|[ABEHMNPRV-Yabehmnprv-y]))|[0-9][A-HJKS-UWa-hjks-uw])\ {0,1}[0-9][ABD-HJLNP-UW-Zabd-hjlnp-uw-z]{2}|([Gg][Ii][Rr]\ 0[Aa][Aa])|([Ss][Aa][Nn]\ {0,1}[Tt][Aa]1)|([Bb][Ff][Pp][Oo]\ {0,1}([Cc]\/[Oo]\ )?[0-9]{1,4})|(([Aa][Ss][Cc][Nn]|[Bb][Bb][Nn][Dd]|[BFSbfs][Ii][Qq][Qq]|[Pp][Cc][Rr][Nn]|[Ss][Tt][Hh][Ll]|[Tt][Dd][Cc][Uu]|[Tt][Kk][Cc][Aa])\ {0,1}1[Zz][Zz]))$/,
            "alertText": "* Invalid postcode"
        };
        $.validationEngineLanguage.allRules["postcodeNL"] = {
            // NL zip codes |  Accepts 1234AA format zipcodes
            "regex": /^\d{4}[a-zA-Z]{2}?$/,
            "alertText": "* Ongeldige postcode, formaat moet 1234AA zijn"
        };
        $.validationEngineLanguage.allRules["postcodeUS"] = {
            // US zip codes | Accepts 12345 and 12345-1234 format zipcodes
            "regex": /^\d{5}(-\d{4})?$/,
            "alertText": "* Invalid zipcode"
        };
        $.validationEngineLanguage.allRules["postcodeDE"] = {
            // Germany zip codes | Accepts 12345 format zipcodes
            "regex": /^\d{5}?$/,
            "alertText": "* Invalid zipcode"
        };
        $.validationEngineLanguage.allRules["postcodeAT"] = {
            // Austrian zip codes | Accepts 1234 format zipcodes
            "regex": /^\d{4}?$/,
            "alertText": "* Invalid zipcode"
        };
        $.validationEngineLanguage.allRules["postcodePL"] = {
            // Polish zip codes | Accepts 80-000 format zipcodes
            "regex": /^\d{2}-\d{3}$/,
            "alertText": "* Niepoprawny kod pocztowy, poprawny format to: 12-345"
        };
        $.validationEngineLanguage.allRules["postcodeJP"] = {
            // JP zip codes | Accepts 123 and 123-1234 format zipcodes
            "regex": /^\d{3}(-\d{4})?$/,
            "alertText": "* 郵便番号が正しくありません"
        };
        $.validationEngineLanguage.allRules["postcodeBR"] = {
            // BR zip codes | Accepts 12345-123 format zipcodes
            "regex": /^\d{5}(-\d{3})?$/,
            "alertText": "* CEP inválido"
        };
        $.validationEngineLanguage.allRules["onlyLetNumSpec"] = {
            // Good for database fields
            "regex": /^[0-9a-zA-Z_-]+$/,
            "alertText": "* Only Letters, Numbers, hyphen(-) and underscore(_) allowed"
        };
        /**验证公里标**/
        $.validationEngineLanguage.allRules["mileage"] = {
            // Good for database fields
            "regex": /(^[kK]?\d+$)|(^[kK]?\d+[+-]\d+$)|(^[kK]?\d+[+-]\d+.\d+$)/ig,
            "alertText": "公里标格式不正确"
        };
        //判断日期类型是否为: yyyy-mm-dd HH:mm:ss
        $.validationEngineLanguage.allRules["DateTimeFormat2"] = {
                // Good for database fields
        		"regex": /^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2}) (\d{1,2}):(\d{1,2}):(\d{1,2})$/,
                "alertText": "* 无效的日期或时间格式"
        };
        
        //判断日期类型是否为 hh:mm:ss格式的类型
        $.validationEngineLanguage.allRules["isTime"] = {
        		"regex": /^((20|21|22|23|[0-1]\d)\:[0-5][0-9])(\:[0-5][0-9])?$/,
                "alertText": "* 无效的时间格式"
        };
        
        //判断输入的字符是否为英文字母
        $.validationEngineLanguage.allRules["isLetter"] = {
        		"regex": /^[a-zA-Z]+$/,
                "alertText": "* 不是正确的英文字母类型"
        };
        
        //双精度类型格式不正确
        $.validationEngineLanguage.allRules["isInteger"] = {
        		"regex": /^[-+]?\d*$/,
                "alertText": "* 整数类型格式不正确"
        };
        
        //双精度类型格式不正确
        $.validationEngineLanguage.allRules["isDouble"] = {
        		"regex": /^[-\+]?\d+(\.\d+)?$/,
                "alertText": "* 双精度类型格式不正确"
        };
        
      //数字格式, 与上述双精度类型验证一致,只不过提示为数字格式不正确
        $.validationEngineLanguage.allRules["isDoubleNum"] = {
        		"regex": /^[-\+]?\d+(\.\d+)?$/,
                "alertText": "* 数字格式不正确"
        };
        
        //金额类型, 与上述双精度类型验证一致,只不过提示为金额格式不正确
        $.validationEngineLanguage.allRules["isMoney"] = {
        		"regex": /^[-\+]?\d+(\.\d+)?$/,
                "alertText": "* 金额格式不正确"
        };
        
        //判断输入的字符是否为:a-z,A-Z,0-9    
        $.validationEngineLanguage.allRules["isString"] = {
        		"regex": /^[a-zA-Z0-9_]+$/,
                "alertText": "* 字符串类型格式不正确"
        };
        
        //判断输入的字符是否为中文    
        $.validationEngineLanguage.allRules["isChinese"] = {
        		"regex": /^[\u0391-\uFFE5]+$/,
                "alertText": "* 字符串类型格式不正确"
        };
        
        
        //判断输入的邮编(只能为六位)是否正确  
        $.validationEngineLanguage.allRules["isZIP"] = {
        		"regex": /^\d{6}$/,
                "alertText": "* 邮编类型格式不正确"
        };
        //判断输入的字符是否为:电话号码 
        $.validationEngineLanguage.allRules["phone"] = {
        		"regex": /^((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/,
                "alertText": "* 电话号码格式不正确"
        };
        //判断输入的字符是否为:手机号码
        $.validationEngineLanguage.allRules["mobile"] = {
        		"regex": /^((\(\d{2,3}\))|(\d{3}\-))?13\d{9}$/,
                "alertText": "* 手机号码格式不正确"
        };
        //判断输入的字符是否为:身份证号码  
        $.validationEngineLanguage.allRules["idCard"] = {
        		"regex": /^\d{15}(\d{2}[A-Za-z0-9])?$/,
                "alertText": "* 身份证号码格式不正确"
        };
        //判断输入的字符是否为:QQ
        $.validationEngineLanguage.allRules["isQQ"] = {
        		"regex": /^[1-9]\d{4,8}$/,
                "alertText": "* QQ号码格式不正确"
        };
        
        //  # more validations may be added after this point
    }
})(jQuery);
