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
					url : getCtxPath() + "/backend/androidEquipmentInfo/findEntityById.do?v=" + new Date().getTime(),// 请求服务器的url
					method: "post",
					dataType:"json",
					data: {
						equipmentId: equipmentId
					},
					success: function(data){
						if(data.code == 200){ 
							var android=data.list.data;
							var appName='';
							$("#customerId").val(android.customerId);
							if(android.appName=='1') appName='洪澄';
							if(android.appName=='2') appName='速贷';
							$("#appName").val(appName);
							$("#baseStationInfo").val(android.baseStationInfo);
							$("#gateway").val(android.gateway);
							$("#bootTime").val(android.bootTime);
							$("#sumRamStorage").val(android.sumRamStorage);
							$("#availableRamStorage").val(android.availableRamStorage);
							$("#sumRomStorage").val(android.sumRomStorage);
							$("#availableRomStorage").val(android.availableRomStorage);
							$("#cpuBogoMips").val(android.cpuBogoMips);
							$("#gprsIp").val(android.gprsIp);
							$("#wifiMac").val(android.wifiMac);
							$("#phoneNum").val(android.phoneNum);
							$("#timeZone").val(android.timeZone);
							$("#proxyAdressPort").val(android.proxyAdressPort);
							$("#sdkEdition").val(android.sdkEdition);
							$("#fmEdition").val(android.fmEdition);
							$("#electricity").val(android.electricity);
							$("#musicHash").val(android.musicHash);
							$("#networkProviderCode").val(android.networkProviderCode);
							$("#isoCountryCode").val(android.isoCountryCode);
							$("#firmEdition").val(android.firmEdition);
							$("#cpuSerialNum").val(android.cpuSerialNum);
							$("#cpuHardware").val(android.cpuHardware);
							$("#phoneModel").val(android.phoneModel);
							$("#bluetoothMac").val(android.bluetoothMac);
							$("#voiceMailboxNum").val(android.voiceMailboxNum);
							$("#deviceParam").val(android.deviceParam);
							$("#batteryStatus").val(android.batteryStatus);
							$("#equipmentSerialNum").val(android.equipmentSerialNum);
							$("#basebandEdition").val(android.basebandEdition);
							$("#browserUa").val(android.browserUa);
							$("#electricityAvailable").val(android.electricityAvailable);
							$("#bootReachCurrent").val(android.bootReachCurrent);
							$("#bootNoDormancy").val(android.bootNoDormancy);
							$("#screenLong").val(android.screenLong);
							$("#screenWide").val(android.screenWide);
							$("#screenLong").val(android.screenLong);
							$("#wifiIp").val(android.wifiIp);
							$("#vpnAddress").val(android.vpnAddress);
							$("#dnsAddress").val(android.dnsAddress);
							$("#isRoot").val(android.isRoot);
							$("#moveCountryCode").val(android.moveCountryCode);
							$("#moveNetworkCode").val(android.moveNetworkCode);
							$("#language").val(android.language);
							$("#realIp").val(android.realIp);
							$("#ipPosition").val(android.ipPosition);
							$("#cpuModel").val(android.cpuModel);
							$("#createTime").val(android.createTime.substring(0,19));
							$("#modifyTime").val(android.modifyTime.substring(0,19));
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
