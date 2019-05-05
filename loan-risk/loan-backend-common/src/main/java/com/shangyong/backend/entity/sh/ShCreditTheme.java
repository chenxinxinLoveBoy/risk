package com.shangyong.backend.entity.sh;

import java.util.List;

/**
 * 上海咨询云个人报告表
 * @author Smk
 *
 */
public class ShCreditTheme {
	/**上海资信主表**/
	private ShCredit shCredit;
	/**上海资信用户地址表**/
	private List<ShCreditAddress> address;
	/**上海资信用户工作表**/
	private List<ShCreditWork> works;
	/**上海资信用户联系人信息表**/
	private List<ShCreditContacts> contacts;
	/**上海资信贷款申请信息表**/
	private List<ShCreditLoansMessage> saveLoansMessage;
	/**上海资信贷款交易信息表**/
	private ShCreditLoansDeal saveLoansDeal;
	/**上海资信贷款表**/
	private List<ShCreditLoans> savaLoans;
	/**上海资信为他人担保信息表**/
	private List<ShCreditGuarantee> saveGuarantee;
	/**上海资信特殊交易信息表**/
	private List<ShCreditSpecialDeal> saveSpecialDeal;
	/**上海资信查询信息表**/
	private List<ShCreditSearchInformation> saveSearchInformation;
	
	private List<ShCreditPrompt> saveShCreditPrompt;
	public ShCredit getShCredit() {
		return shCredit;
	}
	public void setShCredit(ShCredit shCredit) {
		this.shCredit = shCredit;
	}
	public List<ShCreditAddress> getAddress() {
		return address;
	}
	public void setAddress(List<ShCreditAddress> address) {
		this.address = address;
	}
	public List<ShCreditWork> getWorks() {
		return works;
	}
	public void setWorks(List<ShCreditWork> works) {
		this.works = works;
	}
	public List<ShCreditContacts> getContacts() {
		return contacts;
	}
	public void setContacts(List<ShCreditContacts> contacts) {
		this.contacts = contacts;
	}
	public ShCreditLoansDeal getSaveLoansDeal() {
		return saveLoansDeal;
	}
	public void setSaveLoansDeal(ShCreditLoansDeal saveLoansDeal) {
		this.saveLoansDeal = saveLoansDeal;
	}
	public List<ShCreditLoans> getSavaLoans() {
		return savaLoans;
	}
	public void setSavaLoans(List<ShCreditLoans> savaLoans) {
		this.savaLoans = savaLoans;
	}
	public List<ShCreditGuarantee> getSaveGuarantee() {
		return saveGuarantee;
	}
	public void setSaveGuarantee(List<ShCreditGuarantee> saveGuarantee) {
		this.saveGuarantee = saveGuarantee;
	}
	public List<ShCreditSpecialDeal> getSaveSpecialDeal() {
		return saveSpecialDeal;
	}
	public void setSaveSpecialDeal(List<ShCreditSpecialDeal> saveSpecialDeal) {
		this.saveSpecialDeal = saveSpecialDeal;
	}
	public List<ShCreditSearchInformation> getSaveSearchInformation() {
		return saveSearchInformation;
	}
	public void setSaveSearchInformation(List<ShCreditSearchInformation> saveSearchInformation) {
		this.saveSearchInformation = saveSearchInformation;
	}
	public List<ShCreditLoansMessage> getSaveLoansMessage() {
		return saveLoansMessage;
	}
	public void setSaveLoansMessage(List<ShCreditLoansMessage> saveLoansMessage) {
		this.saveLoansMessage = saveLoansMessage;
	}
	public List<ShCreditPrompt> getSaveShCreditPrompt() {
		return saveShCreditPrompt;
	}
	public void setSaveShCreditPrompt(List<ShCreditPrompt> saveShCreditPrompt) {
		this.saveShCreditPrompt = saveShCreditPrompt;
	}
	@Override
	public String toString() {
		return "ShCreditTheme [shCredit=" + shCredit + ", address=" + address + ", works=" + works + ", contacts="
				+ contacts + ", saveLoansMessage=" + saveLoansMessage + ", saveLoansDeal=" + saveLoansDeal
				+ ", saveLoans=" + savaLoans + ", saveGuarantee=" + saveGuarantee + ", saveSpecialDeal="
				+ saveSpecialDeal + ", saveSearchInformation=" + saveSearchInformation + ", saveShCreditPrompt="
				+ saveShCreditPrompt + "]";
	}

	
	
	
}
