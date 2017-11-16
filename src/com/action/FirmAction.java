package com.action;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.page.Json;
import com.page.TuserDTO;
import com.service.FirmService;

public class FirmAction extends BaseAction {

	private Integer page;
	private Integer rows;
	private String mingcheng;
	private String dianhua;
	private FirmService firmService;
	
	public String getDianhua() {
		return dianhua;
	}

	public void setDianhua(String dianhua) {
		this.dianhua = dianhua;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public String getMingcheng() {
		return mingcheng;
	}

	public void setMingcheng(String mingcheng) {
		this.mingcheng = mingcheng;
	}

	public FirmService getFirmService() {
		return firmService;
	}

	public void setFirmService(FirmService firmService) {
		this.firmService = firmService;
	}

	public void showAll() {
		Map session = ActionContext.getContext().getSession();
		TuserDTO u = (TuserDTO) session.get("yuangong");
		if (u != null && u.getRole() < 3) {
			super.writeJson(firmService.showAll());
		}
	}
	
	public void firmShow() {
		Map session = ActionContext.getContext().getSession();
		TuserDTO u = (TuserDTO) session.get("yuangong");
		if (u != null && u.getRole() < 3) {
			super.writeJson(firmService.firmShow(page, rows, mingcheng));
		}
	}
	
	public void add() throws Exception {
		Map session = ActionContext.getContext().getSession();
		TuserDTO u = (TuserDTO) session.get("yuangong");
		Json j = new Json();
		if (u != null && u.getRole() < 3) {
			firmService.addFirm(mingcheng, dianhua);
			String msg = "增加成功";
			j.setSuccess(true);
			j.setMsg(msg);
		} else {
			j.setSuccess(false);
			j.setMsg("增加失败！");
		}
		super.writeJson(j);
	}
}
