package com.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Tuser entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "tuser", catalog = "butieguanli")
public class Tuser implements java.io.Serializable {

	// Fields

	private Integer id;
	private Firm firm;
	private String username;
	private String emcardid;
	private String xingming;
	private String shenfenzheng;
	private String shouji;
	private Double balance;
	private String psw;
	private Integer role;
	private Integer editid;

	// Constructors

	/** default constructor */
	public Tuser() {
	}

	/** minimal constructor */
	public Tuser(Integer id) {
		this.id = id;
	}

	/** for upload */
	public Tuser(Integer id, Firm firm, String username, String emcardid,
			String xingming, String shenfenzheng, String shouji,
			Double balance, String psw, Integer role, Integer editid) {
		this.id = id;
		this.firm = firm;
		this.username = username;
		this.emcardid = emcardid;
		this.xingming = xingming;
		this.shenfenzheng = shenfenzheng;
		this.shouji = shouji;
		this.balance = balance;
		this.psw = psw;
		this.role = role;
		this.editid = editid;
	}

	// Property accessors
	@Id
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "firmid")
	public Firm getFirm() {
		return this.firm;
	}

	public void setFirm(Firm firm) {
		this.firm = firm;
	}

	@Column(name = "username", length = 20)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "emcardid", length = 20)
	public String getEmcardid() {
		return this.emcardid;
	}

	public void setEmcardid(String emcardid) {
		this.emcardid = emcardid;
	}

	@Column(name = "xingming", length = 20)
	public String getXingming() {
		return this.xingming;
	}

	public void setXingming(String xingming) {
		this.xingming = xingming;
	}

	@Column(name = "shenfenzheng", length = 20)
	public String getShenfenzheng() {
		return this.shenfenzheng;
	}

	public void setShenfenzheng(String shenfenzheng) {
		this.shenfenzheng = shenfenzheng;
	}

	@Column(name = "shouji", length = 16)
	public String getShouji() {
		return this.shouji;
	}

	public void setShouji(String shouji) {
		this.shouji = shouji;
	}

	@Column(name = "balance", precision = 22, scale = 0)
	public Double getBalance() {
		return this.balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	@Column(name = "psw", length = 32)
	public String getPsw() {
		return this.psw;
	}

	public void setPsw(String psw) {
		this.psw = psw;
	}

	@Column(name = "role")
	public Integer getRole() {
		return this.role;
	}

	public void setRole(Integer role) {
		this.role = role;
	}

	@Column(name = "editid")
	public Integer getEditid() {
		return this.editid;
	}

	public void setEditid(Integer editid) {
		this.editid = editid;
	}

}