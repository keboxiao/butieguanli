package com.po;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Xiaofei entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "xiaofei", catalog = "butieguanli")
public class Xiaofei implements java.io.Serializable {

	// Fields

	private Long id;
	private Tuser tuserByShenqingid;
	private Firm firmByCaozuofirmid;
	private Tuser tuserByShenheid;
	private Firm firmByFirmid;
	private Tuser tuserByUserid;
	private String zhouqi;
	private String caozuo;
	private Double balance;
	private Timestamp shenqingriqi;
	private Timestamp shenheriqi;
	private Integer state;
	private Double amount;

	// Constructors

	/** default constructor */
	public Xiaofei() {
	}

	/** for upload */
	public Xiaofei(Tuser tuserByShenqingid, Firm firmByCaozuofirmid,
			Firm firmByFirmid, Tuser tuserByUserid, String zhouqi,
			String caozuo, Integer state, Double amount) {
		this.tuserByShenqingid = tuserByShenqingid;
		this.firmByCaozuofirmid = firmByCaozuofirmid;
		this.firmByFirmid = firmByFirmid;
		this.tuserByUserid = tuserByUserid;
		this.zhouqi = zhouqi;
		this.caozuo = caozuo;
		this.state = state;
		this.amount = amount;
	}

	/** full constructor */
	public Xiaofei(Tuser tuserByShenqingid, Firm firmByCaozuofirmid,
			Tuser tuserByShenheid, Firm firmByFirmid, Tuser tuserByUserid,
			String zhouqi, String caozuo, Double balance,
			Timestamp shenqingriqi, Timestamp shenheriqi, Integer state,
			Double amount) {
		this.tuserByShenqingid = tuserByShenqingid;
		this.firmByCaozuofirmid = firmByCaozuofirmid;
		this.tuserByShenheid = tuserByShenheid;
		this.firmByFirmid = firmByFirmid;
		this.tuserByUserid = tuserByUserid;
		this.zhouqi = zhouqi;
		this.caozuo = caozuo;
		this.balance = balance;
		this.shenqingriqi = shenqingriqi;
		this.shenheriqi = shenheriqi;
		this.state = state;
		this.amount = amount;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "shenqingid")
	public Tuser getTuserByShenqingid() {
		return this.tuserByShenqingid;
	}

	public void setTuserByShenqingid(Tuser tuserByShenqingid) {
		this.tuserByShenqingid = tuserByShenqingid;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "caozuofirmid")
	public Firm getFirmByCaozuofirmid() {
		return this.firmByCaozuofirmid;
	}

	public void setFirmByCaozuofirmid(Firm firmByCaozuofirmid) {
		this.firmByCaozuofirmid = firmByCaozuofirmid;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "shenheid")
	public Tuser getTuserByShenheid() {
		return this.tuserByShenheid;
	}

	public void setTuserByShenheid(Tuser tuserByShenheid) {
		this.tuserByShenheid = tuserByShenheid;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "firmid")
	public Firm getFirmByFirmid() {
		return this.firmByFirmid;
	}

	public void setFirmByFirmid(Firm firmByFirmid) {
		this.firmByFirmid = firmByFirmid;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userid")
	public Tuser getTuserByUserid() {
		return this.tuserByUserid;
	}

	public void setTuserByUserid(Tuser tuserByUserid) {
		this.tuserByUserid = tuserByUserid;
	}

	@Column(name = "zhouqi", length = 6)
	public String getZhouqi() {
		return this.zhouqi;
	}

	public void setZhouqi(String zhouqi) {
		this.zhouqi = zhouqi;
	}

	@Column(name = "caozuo", length = 20)
	public String getCaozuo() {
		return this.caozuo;
	}

	public void setCaozuo(String caozuo) {
		this.caozuo = caozuo;
	}

	@Column(name = "balance", precision = 22, scale = 0)
	public Double getBalance() {
		return this.balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	@Column(name = "shenqingriqi", length = 19)
	public Timestamp getShenqingriqi() {
		return this.shenqingriqi;
	}

	public void setShenqingriqi(Timestamp shenqingriqi) {
		this.shenqingriqi = shenqingriqi;
	}

	@Column(name = "shenheriqi", length = 19)
	public Timestamp getShenheriqi() {
		return this.shenheriqi;
	}

	public void setShenheriqi(Timestamp shenheriqi) {
		this.shenheriqi = shenheriqi;
	}

	@Column(name = "state")
	public Integer getState() {
		return this.state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	@Column(name = "amount", precision = 22, scale = 0)
	public Double getAmount() {
		return this.amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

}