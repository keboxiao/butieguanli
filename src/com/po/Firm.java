package com.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Firm entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "firm", catalog = "butieguanli")
public class Firm implements java.io.Serializable {

	// Fields

	private Integer id;
	private String mingcheng;
	private String dianhua;

	// Constructors

	/** default constructor */
	public Firm() {
	}
	
	public Firm(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public Firm(String mingcheng, String dianhua) {
		this.mingcheng = mingcheng;
		this.dianhua = dianhua;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "mingcheng", length = 50)
	public String getMingcheng() {
		return this.mingcheng;
	}

	public void setMingcheng(String mingcheng) {
		this.mingcheng = mingcheng;
	}

	@Column(name = "dianhua", length = 20)
	public String getDianhua() {
		return this.dianhua;
	}

	public void setDianhua(String dianhua) {
		this.dianhua = dianhua;
	}

}