package com.ithome.web.Bean;

import java.util.List;

public class Banks {
	
    private int bankId;
    private int bankCode;
    private String bankName;
    private String bankAddress;
    private String bankPhoneNumber;
    private String bankfaxNumber;
    private String bankHQ;
    private int bankBranches;
    private int bankATMNumbers;
    private String bankWebSite;
    private String bankEmailAddress;
    private String bankSwiftCode;
    private String bankBigLogo;
    private String bankSmallLogo;
    private List<Banks> banksList;
    
	public Banks(int bankId, int bankCode, String bankName, String bankAddress, String bankPhoneNumber, String bankfaxNumber,
			String bankHQ, int bankBranches, int bankATMNumbers, String bankWebSite, String bankEmailAddress,
			String bankSwiftCode, String bankBigLogo, String bankSmallLogo, List<Banks> banksList) {
		super();
		this.bankId = bankId;
		this.bankCode = bankCode;
		this.bankName = bankName;
		this.bankAddress = bankAddress;
		this.bankPhoneNumber = bankPhoneNumber;
		this.bankfaxNumber = bankfaxNumber;
		this.bankHQ = bankHQ;
		this.bankBranches = bankBranches;
		this.bankATMNumbers = bankATMNumbers;
		this.bankWebSite = bankWebSite;
		this.bankEmailAddress = bankEmailAddress;
		this.bankSwiftCode = bankSwiftCode;
		this.bankBigLogo = bankBigLogo;
		this.bankSmallLogo = bankSmallLogo;
		this.banksList = banksList;
	}

	public Banks( String bankName,int bankCode, String bankAddress, String bankPhoneNumber, String bankfaxNumber, String bankHQ, int bankBranches, int bankATMNumbers, String bankWebSite, String bankEmailAddress, String bankSwiftCode) {
		this.bankName = bankName;
		this.bankCode = bankCode;
		this.bankAddress = bankAddress;
		this.bankPhoneNumber = bankPhoneNumber;
		this.bankfaxNumber = bankfaxNumber;
		this.bankHQ = bankHQ;
		this.bankBranches = bankBranches;
		this.bankATMNumbers = bankATMNumbers;
		this.bankWebSite = bankWebSite;
		this.bankEmailAddress = bankEmailAddress;
		this.bankSwiftCode = bankSwiftCode;
	}

	public Banks(String bankName, String bankAddress, String bankPhoneNumber, String bankfaxNumber, String bankHQ,
				 int bankBranches, int bankATMNumbers, String bankWebSite, String bankEmailAddress, String bankSwiftCode,
				 String bankBigLogo, String bankSmallLogo) {
		super();
		this.bankName = bankName;
		this.bankAddress = bankAddress;
		this.bankPhoneNumber = bankPhoneNumber;
		this.bankfaxNumber = bankfaxNumber;
		this.bankHQ = bankHQ;
		this.bankBranches = bankBranches;
		this.bankATMNumbers = bankATMNumbers;
		this.bankWebSite = bankWebSite;
		this.bankEmailAddress = bankEmailAddress;
		this.bankSwiftCode = bankSwiftCode;
		this.bankBigLogo = bankBigLogo;
		this.bankSmallLogo = bankSmallLogo;
	}

	public Banks(String bankBigLogo, String bankSmallLogo) {
		super();
		this.bankBigLogo = bankBigLogo;
		this.bankSmallLogo = bankSmallLogo;
	}

	public Banks(String bankBigLogo) {
		this.bankBigLogo = bankBigLogo;
	}


	public Banks() {
	
	}

	public Banks(int bankId, String bankName, String bankWebSite, String bankAddress, String bankEmailAddress, String bankfaxNumber, String bankHQ, String bankPhoneNumber, String bankSwiftCode) {
		this.bankId = bankId;
		this.bankName = bankName;
		this.bankAddress = bankAddress;
		this.bankPhoneNumber = bankPhoneNumber;
		this.bankfaxNumber = bankfaxNumber;
		this.bankHQ = bankHQ;
		this.bankWebSite = bankWebSite;
		this.bankEmailAddress = bankEmailAddress;
		this.bankSwiftCode = bankSwiftCode;
	}

	public int getBankCode() {
		return bankCode;
	}

	public void setBankCode(int bankCode) {
		this.bankCode = bankCode;
	}

	public int getBankId() {
		return bankId;
	}

	public void setBankId(int bankId) {
		this.bankId = bankId;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankAddress() {
		return bankAddress;
	}

	public void setBankAddress(String bankAddress) {
		this.bankAddress = bankAddress;
	}

	public String getBankPhoneNumber() {
		return bankPhoneNumber;
	}

	public void setBankPhoneNumber(String bankPhoneNumber) {
		this.bankPhoneNumber = bankPhoneNumber;
	}

	public String getBankfaxNumber() {
		return bankfaxNumber;
	}

	public void setBankfaxNumber(String bankfaxNumber) {
		this.bankfaxNumber = bankfaxNumber;
	}

	public String getBankHQ() {
		return bankHQ;
	}

	public void setBankHQ(String bankHQ) {
		this.bankHQ = bankHQ;
	}

	public int getBankBranches() {
		return bankBranches;
	}

	public void setBankBranches(int bankBranches) {
		this.bankBranches = bankBranches;
	}

	public int getBankATMNumbers() {
		return bankATMNumbers;
	}

	public void setBankATMNumbers(int bankATMNumbers) {
		this.bankATMNumbers = bankATMNumbers;
	}

	public String getBankWebSite() {
		return bankWebSite;
	}

	public void setBankWebSite(String bankWebSite) {
		this.bankWebSite = bankWebSite;
	}

	public String getBankEmailAddress() {
		return bankEmailAddress;
	}

	public void setBankEmailAddress(String bankEmailAddress) {
		this.bankEmailAddress = bankEmailAddress;
	}

	public String getBankSwiftCode() {
		return bankSwiftCode;
	}

	public void setBankSwiftCode(String bankSwiftCode) {
		this.bankSwiftCode = bankSwiftCode;
	}

	public String getBankBigLogo() {
		return bankBigLogo;
	}

	public void setBankBigLogo(String bankBigLogo) {
		this.bankBigLogo = bankBigLogo;
	}

	public String getBankSmallLogo() {
		return bankSmallLogo;
	}

	public void setBankSmallLogo(String bankSmallLogo) {
		this.bankSmallLogo = bankSmallLogo;
	}

	public List<Banks> getBanksList() {
		return banksList;
	}

	public void setBanksList(List<Banks> banksList) {
		this.banksList = banksList;
	}
}