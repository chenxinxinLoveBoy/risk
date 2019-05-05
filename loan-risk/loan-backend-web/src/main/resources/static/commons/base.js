
//限制文本框只能输入数字和1个小数点， 前置条件： onclick="historys();"
function clearNoNum(obj)
{
 obj.value = obj.value.replace(/[^\d.]/g,"");  //清除“数字”和“.”以外的字符
// obj.value = obj.value.replace(/[^/%/\d.]/g,"");  //清除“数字”“.” “%”以外的字符
 obj.value = obj.value.replace(/^\./g,"");  //验证第一个字符是数字而不是.
 obj.value = obj.value.replace(/\.{2,}/g,"."); //只保留第一个. 清除多余的.
 obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");
}


/// 以键值对的方式读取URL字符串
/// 例如 http://192.168.1.211/JcySoft6.0_changzhi/Vacation.htm?id=1&action=add
function getQueryString(key) {
    var value = "";
    ///获取当前页面的URL
    var sURL = window.document.URL;
    ///URL中是否包含查询字符串
    if (sURL.indexOf("?") > 0) {
        //分解URL,第二的元素为完整的查询字符串
        //即arrayParams[1]的值为【id=1&action=2】
        var arrayParams = sURL.split("?");
        //分解查询字符串
        //arrayURLParams[0]的值为【id=1 】
        //arrayURLParams[2]的值为【action=add】
        var arrayURLParams = arrayParams[1].split("&");

        //遍历分解后的键值对
        for (var i = 0; i < arrayURLParams.length; i++) {
            //分解一个键值对
            var sParam = arrayURLParams[i].split("=");
            if ((sParam[0] == key) && (sParam[1] != "")) {
                //找到匹配的的键,且值不为空
                value = sParam[1];
                break;
            }
        }
    }
    return value;
}

//删除url参数
function delParam(url,paramKey){
    var urlParam = url.substr(url.indexOf("?")+1);
    var beforeUrl = url.substr(0,url.indexOf("?"));
    var nextUrl = "";
     
    var arr = new Array();
    if(urlParam!=""){
        var urlParamArr = urlParam.split("&");
      
        for(var i=0;i<urlParamArr.length;i++){
            var paramArr = urlParamArr[i].split("=");
            if(paramArr[0]!=paramKey){
                arr.push(urlParamArr[i]);
            }
        }
    }
     
    if(arr.length>0){
        nextUrl = "?"+arr.join("&");
    }
    url = beforeUrl+nextUrl;
    return url;
}

function getFormatDateStr(_date) {
    var date = _date;
    var seperator1 = "-";
    var seperator2 = ":";
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
//    var dataStr = date.getFullYear() + seperator1 + month + seperator1 + strDate + " " + date.getHours() + seperator2 + date.getMinutes() + seperator2 + date.getSeconds();
    var dataStr = date.getFullYear() + seperator1 + month + seperator1 + strDate;
    return dataStr;
}

//格式化金额
function fmoney(s, n) {
    /*
     * 参数说明：
     * s：要格式化的数字
     * n：保留几位小数
     * */
    n = n > 0 && n <= 20 ? n : 2;
    s = parseFloat((s + "").replace(/[^\d\.-]/g, "")).toFixed(n) + "";
    var l = s.split(".")[0].split("").reverse(),
        r = s.split(".")[1];
    t = "";
    for (i = 0; i < l.length; i++) {
        t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "," : "");
    }
    return t.split("").reverse().join("") + "." + r;
}

function getCtxPath(){  
    var curWwwPath=window.document.location.href;  
    var pathName=window.document.location.pathname;  
    var pos=curWwwPath.indexOf(pathName);  
    var localhostPaht=curWwwPath.substring(0,pos);  
    var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
    return(localhostPaht);
}  

// 请求到后台Controller
var hostIp= getCtxPath() + "/backend";
// 页面的跳转
var hostIpHtml = getCtxPath();

//var str = "";

/**
 * 加載数据字典的缓存
 * @param dicBigCode 大类编号
 * @param dicSmallCode 小类编号
 */

/*
var arrayList  = {};
var clsCodes = {"clsCodes" :  
    ["BOOL",  
     "STATUS",  
     "USER_TYPE",  
     "REPORT_STATUS"  
 ]  
}; 

	*//**
	 * 用户枚举型，如男，女，  是，否
	 * @param dicBigCode 大类的编号
	 *//*
	function getRedisScDictSmall(dicBigCode){
		
		
		$.ajax({
			url: hostIp + "/scDicSmallist/getRedisScDictSmall.do",
			method: "post",
			dataType: "json",
			async: false, //同步
			data: {
				dicBigCode: dicBigCode 
			},
			success: function(data){
				if(data == '' || data == null || data== undefined){
					return ;
				}
				arrayList=data;
				// 循环返回的结果集
//				$.each(data, function(k, v){
					
					// 返回值匹配参数小类编号 
//					if(v.dicSmallCode == dicSmallCode){
////						str =  v.dicSmallValue;
//						arrayList.push(v.dicSmallValue);
//					}
//				});
			},
			error: function(){
				alert("数据字段数据加载异常，请退出登录再试！");
			}
			
			
		});
	}
	
	 
*/


//str = getRedisScDictSmall("026", '3');
//alert(str.length);

/*
var dicMap={};

*/
/**
 * 数得数据字典文本
 * @param code
 *//*
function getDicText(code,fun){
    $.ajax({
        type:"post",
        url:  "http://localhost:8080/loan-backend-web/backend/scDicSmallist/getRedisScDictSmall.do",
        data:{dicBigCode: code},
        dataType:"json",
        async:false,
        success:function(data){
            fun(data);
        }
    });
}
 
*//**
 * 缓数数据字典
 * @param value
 * @param rowData
 *//*
function cacheDic(value,code){
    if(dicMap[code]==undefined){
        getDicText(code,function(data){
            if((typeof data=='object')&&data.constructor==Array){
                var obj={};
                $(data).each(function(i,item){
                    if(item.dicSmallValue!="!"){
                        obj[item.dicSmallCode]=item.dicSmallValue;
                    }
                });
                dicMap["code"]=obj;
            }
        });
    }
    if(dicMap[code][value]==undefined){
        return "";
    }
    return dicMap[code][value];
}
 
*//**
 * 格式化数据字典
 * @param value
 * @param rowData
 * @param rowIndex
 *//*
function formartDic(value,code){
    return cacheDic(value,code);
}*/
 