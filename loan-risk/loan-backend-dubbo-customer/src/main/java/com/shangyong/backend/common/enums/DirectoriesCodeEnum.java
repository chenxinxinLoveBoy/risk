package com.shangyong.backend.common.enums;

  
/**
* 异常号码
* @author hxf
*/
public enum DirectoriesCodeEnum {
	
	DAI("贷"),//姓名中包含贷
	XINYONGKA("信用卡"),//姓名中包含信用卡
	BANKA("办卡"),//姓名中包含办卡
	TIE("提额"),//姓名中包含提额
	HEIHU("黑户"),//姓名中包含黑户
	KOUZI("口子"),//姓名中包含口子
	DU("赌"),//姓名中包含赌
	JIEDAIBAO("借贷宝"),//姓名中包含借贷宝
	TAOXIAN("套现"),//姓名中包含套现
	TIXIAN("提现"),//姓名中包含提现
	POSX("POS"),//姓名中包含POS
	POSY("pos"),//姓名中包含pos
	POSXY("Pos"),//姓名中包含Pos
	CUISHOU("催收"),//姓名中包含催收
	QIAN("欠"),//姓名中包含欠
	JIAZHENG("假证"),//姓名中包含假证
	XIANJIN("现金");//姓名中包含现金
	
	private String message;//错误消息
	
	private DirectoriesCodeEnum(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
	
}
