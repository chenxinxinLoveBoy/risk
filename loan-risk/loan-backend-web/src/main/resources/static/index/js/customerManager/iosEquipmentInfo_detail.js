   layui.config({
				base: '../../js/' 
			});
 
	// 回显
	$(function(){
		//我们强烈推荐你在代码最外层把需要用到的模块先加载
		layui.use(['layer', 'form', 'element'], function(){
			 var layer = layui.layer
			  ,form = layui.form()
			  ,element = layui.element();
			  form.render(); //刷新全部
			  
			  var equipmentId = getQueryString("id");//获取页面传过来的平台用户编号  
		  		$.ajax({
					url : getCtxPath() + "/backend/iosEquipmentInfo/findEntityById.do?v=" + new Date().getTime(),// 请求服务器的url
					method: "post",
					dataType:"json",
					data: {
						equipmentId: equipmentId
					},
					success: function(data){
						if(data.code == 200){ 
							var ios=data.list.data;
							var appName='';
							$("#customerId").val(ios.customerId);
							if(ios.appName=='1') appName='洪澄';
							if(ios.appName=='2') appName='速贷';
							$("#appName").val(appName);
							$("#sdkEdition").val(ios.sdkEdition);
							$("#fingerprintTokenId").val(ios.fingerprintTokenId);
							$("#bootTime").val(ios.bootTime.substring(0,19));
							$("#sumRamStorage").val(ios.sumRamStorage);
							$("#availableRamStorage").val(ios.availableRamStorage);
							$("#sumRomStorage").val(ios.sumRomStorage);
							$("#availableRomStorage").val(ios.availableRomStorage);
							$("#screenBrightness").val(ios.screenBrightness);
							$("#batteryState").val(ios.batteryState);
							$("#cellularNetworkIp").val(ios.cellularNetworkIp);
							$("#wifiIp").val(ios.wifiIp);
							$("#wifiMask").val(ios.wifiMask);
							$("#vpnAddress").val(ios.vpnAddress);
							$("#vpnMask").val(ios.vpnMask);
							$("#macAddress").val(ios.macAddress);
							$("#networkType").val(ios.networkType);
							$("#wifiName").val(ios.wifiName);
							$("#wifiBssid").val(ios.wifiBssid);
							$("#agentType").val(ios.agentType);
							$("#agentAddress").val(ios.agentAddress);
							$("#dnsAddress").val(ios.dnsAddress);
							$("#isRoot").val(ios.isRoot);
							$("#systemEdition").val(ios.systemEdition);
							$("#equipmentType").val(ios.equipmentType);
							$("#equipmentName").val(ios.equipmentName);
							$("#operator").val(ios.operator);
							$("#countryCode").val(ios.countryCode);
							$("#moveCountryCode").val(ios.moveCountryCode);
							$("#moveNetworkCode").val(ios.moveNetworkCode);
							$("#language").val(ios.language);
							$("#realIp").val(ios.realIp);
							$("#ipPosition").val(ios.ipPosition);
							$("#cpuNum").val(ios.cpuNum);
							$("#cpuSubType").val(ios.cpuSubType);
							$("#ipAddress").val(ios.ipAddress);
							$("#cpuModel").val(ios.cpuModel);
							$("#createTime").val(ios.createTime.substring(0,19));
							$("#modifyTime").val(ios.modifyTime.substring(0,19));
						}
					},
					error: function(){  
 						layer.msg("网络请求异常,请尝试重新登录" , {icon: 2});//提示信息
					},
					complete: function(){
						form.render(); //刷新全部
					} 
				});	
	   });
	});
	

// 关闭
function closeIfrname(){
	var  frameindex= parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
	parent.layer.close(frameindex);
}
