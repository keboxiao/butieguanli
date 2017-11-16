package com.page;

public class TuserDTO {
	private Integer id;
	private Integer firmid;
	private String firm;
	private String firmdianhua;
	private String username;
	private String emcardid;
	private String xingming;
	private String shenfenzheng;
	private String shouji;
	private Double balance;
	private Integer role;
	private String psw;
	private Integer editid;

	// Constructors

	/** default constructor */
	public TuserDTO() {
	}

	public TuserDTO(Integer id) {
		this.id = id;
	}

	/** for login */
	public TuserDTO(Integer id, Integer firmid, String firm,
			String firmdianhua, String username, String emcardid,
			String xingming, String shenfenzheng, String shouji,
			Double balance, Integer role, String psw, Integer editid) {
		this.id = id;
		this.firmid = firmid;
		this.firm = firm;
		this.firmdianhua = firmdianhua;
		this.username = username;
		this.emcardid = emcardid;
		this.xingming = xingming;
		this.shenfenzheng = shenfenzheng;
		this.shouji = shouji;
		this.balance = balance;
		this.role = role;
		this.editid = editid;
		this.psw = psw;
	}

	/** full constructor */
	public TuserDTO(Integer id, Integer firmid, String firm,
			String firmdianhua, String username, String emcardid,
			String xingming, String shenfenzheng, String shouji,
			Double balance, Integer role, Integer editid) {
		this.id = id;
		this.firmid = firmid;
		this.firm = firm;
		this.firmdianhua = firmdianhua;
		this.username = username;
		this.emcardid = emcardid;
		this.xingming = xingming;
		this.shenfenzheng = shenfenzheng;
		this.shouji = shouji;
		this.balance = balance;
		this.role = role;
		this.editid = editid;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getFirmid() {
		return firmid;
	}

	public void setFirmid(Integer firmid) {
		this.firmid = firmid;
	}

	public String getFirm() {
		return firm;
	}

	public void setFirm(String firm) {
		this.firm = firm;
	}

	public String getFirmdianhua() {
		return firmdianhua;
	}

	public void setFirmdianhua(String firmdianhua) {
		this.firmdianhua = firmdianhua;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmcardid() {
		return emcardid;
	}

	public void setEmcardid(String emcardid) {
		this.emcardid = emcardid;
	}

	public String getXingming() {
		return xingming;
	}

	public void setXingming(String xingming) {
		this.xingming = xingming;
	}

	public String getShenfenzheng() {
		return shenfenzheng;
	}

	public void setShenfenzheng(String shenfenzheng) {
		this.shenfenzheng = shenfenzheng;
	}

	public String getShouji() {
		return shouji;
	}

	public void setShouji(String shouji) {
		this.shouji = shouji;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public Integer getRole() {
		return role;
	}

	public void setRole(Integer role) {
		this.role = role;
	}

	public String getPsw() {
		return psw;
	}

	public void setPsw(String psw) {
		this.psw = psw;
	}

	public Integer getEditid() {
		return editid;
	}

	public void setEditid(Integer editid) {
		this.editid = editid;
	}

}
