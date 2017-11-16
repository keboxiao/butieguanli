package com.page;

import java.util.Date;

public class XiaofeiDTO {

	private Long id;
	private Integer yuangongbianhao;
	private String xingming;
	private String shenfenzheng;
	private String caozuo;
	private Double amount;
	private String shenqingren;
	private String shenqingriqi;
	private String shenheren;
	private String shenheriqi;
	private String shenheyijian;
	private String zhouqi;
	private String quyu;
	private String caozuoquyu;
	private Integer state;
	private Double balance;

	/** for shenqingshow */
	public XiaofeiDTO(Long id, Integer yuangongbianhao, String xingming,
			String shenfenzheng, String caozuo, Double amount,
			String shenqingren, String zhouqi, String quyu, Integer state,
			String caozuoquyu) {
		this.id = id;
		this.yuangongbianhao = yuangongbianhao;
		this.xingming = xingming;
		this.shenfenzheng = shenfenzheng;
		this.amount = amount;
		this.shenqingren = shenqingren;
		this.zhouqi = zhouqi;
		this.quyu = quyu;
		this.caozuo = caozuo;
		this.state = state;
		this.amount = amount;
		this.caozuoquyu = caozuoquyu;
	}

	/** for shenheshow */
	public XiaofeiDTO(Long id, Integer yuangongbianhao, String xingming,
			String shenfenzheng, String caozuo, Double amount,
			String shenqingren, String zhouqi, String quyu, Integer state,
			String caozuoquyu, Date shenqingriqi) {
		this.id = id;
		this.yuangongbianhao = yuangongbianhao;
		this.xingming = xingming;
		this.shenfenzheng = shenfenzheng;
		this.amount = amount;
		this.shenqingren = shenqingren;
		this.zhouqi = zhouqi;
		this.quyu = quyu;
		this.caozuo = caozuo;
		this.state = state;
		this.amount = amount;
		this.caozuoquyu = caozuoquyu;
		this.shenqingriqi = shenqingriqi.toString();
	}

	/** for xiaofeishow */
	public XiaofeiDTO(Long id, Integer yuangongbianhao, String xingming,
			String shenfenzheng, String caozuo, Double amount,
			String shenqingren, String zhouqi, String quyu, Integer state,
			String caozuoquyu, Date shenqingriqi, Double balance, Date shenheriqi) {
		this.id = id;
		this.yuangongbianhao = yuangongbianhao;
		this.xingming = xingming;
		this.shenfenzheng = shenfenzheng;
		this.amount = amount;
		this.shenqingren = shenqingren;
		this.zhouqi = zhouqi;
		this.quyu = quyu;
		this.caozuo = caozuo;
		this.state = state;
		this.amount = amount;
		this.caozuoquyu = caozuoquyu;
		this.shenqingriqi = shenqingriqi.toString();
		this.balance = balance;
		this.shenheriqi = shenheriqi.toString();
	}
	
	/** for new xiaofeishow */
	public XiaofeiDTO(Long id, Integer yuangongbianhao, String xingming,
			String caozuo, Double amount,
			String shenqingren, String zhouqi, String quyu, Integer state,
			String caozuoquyu, Date shenqingriqi, Double balance, Date shenheriqi) {
		this.id = id;
		this.yuangongbianhao = yuangongbianhao;
		this.xingming = xingming;
		this.amount = amount;
		this.shenqingren = shenqingren;
		this.zhouqi = zhouqi;
		this.quyu = quyu;
		this.caozuo = caozuo;
		this.state = state;
		this.amount = amount;
		this.caozuoquyu = caozuoquyu;
		this.shenqingriqi = shenqingriqi.toString();
		this.balance = balance;
		this.shenheriqi = shenheriqi.toString();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getYuangongbianhao() {
		return yuangongbianhao;
	}

	public void setYuangongbianhao(Integer yuangongbianhao) {
		this.yuangongbianhao = yuangongbianhao;
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

	public String getCaozuo() {
		return caozuo;
	}

	public void setCaozuo(String caozuo) {
		this.caozuo = caozuo;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getShenqingren() {
		return shenqingren;
	}

	public void setShenqingren(String shenqingren) {
		this.shenqingren = shenqingren;
	}

	public String getShenqingriqi() {
		return shenqingriqi;
	}

	public void setShenqingriqi(String shenqingriqi) {
		this.shenqingriqi = shenqingriqi;
	}

	public String getShenheren() {
		return shenheren;
	}

	public void setShenheren(String shenheren) {
		this.shenheren = shenheren;
	}

	public String getShenheriqi() {
		return shenheriqi;
	}

	public void setShenheriqi(String shenheriqi) {
		this.shenheriqi = shenheriqi;
	}

	public String getShenheyijian() {
		return shenheyijian;
	}

	public void setShenheyijian(String shenheyijian) {
		this.shenheyijian = shenheyijian;
	}

	public String getZhouqi() {
		return zhouqi;
	}

	public void setZhouqi(String zhouqi) {
		this.zhouqi = zhouqi;
	}

	public String getQuyu() {
		return quyu;
	}

	public void setQuyu(String quyu) {
		this.quyu = quyu;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getCaozuoquyu() {
		return caozuoquyu;
	}

	public void setCaozuoquyu(String caozuoquyu) {
		this.caozuoquyu = caozuoquyu;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

}
