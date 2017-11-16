package com.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import org.apache.commons.io.FileUtils;
import com.opensymphony.xwork2.ActionContext;
import com.page.Json;
import com.page.TuserDTO;
import com.service.XiaofeiService;

public class XiaofeiAction extends BaseAction {
	private File upload;
	private String uploadFileName;
	private String savePath;
	private String ids;
	private Integer yuangongbianhao;
	private String begintime;
	private String endtime;
	private Double expenses;
	private String psw;
	private int page;
	private int rows;
	private Integer id;
	private XiaofeiService xiaofeiService;
	private InputStream excelFile;
	private String fileName;

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String getSavePath() {
		return savePath;
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public XiaofeiService getXiaofeiService() {
		return xiaofeiService;
	}

	public void setXiaofeiService(XiaofeiService xiaofeiService) {
		this.xiaofeiService = xiaofeiService;
	}

	public Integer getYuangongbianhao() {
		return yuangongbianhao;
	}

	public void setYuangongbianhao(Integer yuangongbianhao) {
		this.yuangongbianhao = yuangongbianhao;
	}

	public String getBegintime() {
		return begintime;
	}

	public void setBegintime(String begintime) {
		this.begintime = begintime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public Double getExpenses() {
		return expenses;
	}

	public void setExpenses(Double expenses) {
		this.expenses = expenses;
	}

	public String getPsw() {
		return psw;
	}

	public void setPsw(String psw) {
		this.psw = psw;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public InputStream getExcelFile() {
		return excelFile;
	}

	public void setExcelFile(InputStream excelFile) {
		this.excelFile = excelFile;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String upload() throws Exception {
		Map session = ActionContext.getContext().getSession();
		TuserDTO u = (TuserDTO) session.get("yuangong");
		if (u != null && u.getRole() < 3) {
			// 生成上传的文件对象
			File target = new File(savePath, uploadFileName);
			// 如果文件已经存在，则删除原有文件
			if (target.exists()) {
				target.delete();
			}
			// 复制file对象，实现上传
			try {
				FileUtils.copyFile(upload, target);
				xiaofeiService.excelToDB(target, u.getId());
			} catch (IOException e) {
				e.printStackTrace();
			}
			return SUCCESS;
		} else {
			return "error";
		}
	}

	public void submit() throws Exception {
		Map session = ActionContext.getContext().getSession();
		TuserDTO u = (TuserDTO) session.get("yuangong");
		Json j = new Json();
		if (u != null && u.getRole() < 3) {
			int res = xiaofeiService.submit(ids);
			String msg = "成功提交" + res + "条记录";
			j.setSuccess(true);
			j.setMsg(msg);
		} else {
			j.setSuccess(false);
			j.setMsg("提交失败！");
		}
		super.writeJson(j);
	}

	public void shenqingshow() {
		Map session = ActionContext.getContext().getSession();
		TuserDTO u = (TuserDTO) session.get("yuangong");
		if (u != null && u.getRole() < 3) {
			super.writeJson(xiaofeiService.shenqingshow(page, rows, u.getId()));
		}
	}

	public void shenheshow() {
		Map session = ActionContext.getContext().getSession();
		TuserDTO u = (TuserDTO) session.get("yuangong");
		if (u != null && u.getRole() < 3) {
			super.writeJson(xiaofeiService.shenheshow(page, rows));
		}
	}

	public void xiaofeishow() {
		Map session = ActionContext.getContext().getSession();
		TuserDTO u = (TuserDTO) session.get("yuangong");
		if (u != null) {
			super.writeJson(xiaofeiService.find(u.getId(), yuangongbianhao,
					u.getRole(), u.getFirmid(), begintime, endtime, page, rows));
		}
	}

	public void rollback() {
		Map session = ActionContext.getContext().getSession();
		TuserDTO u = (TuserDTO) session.get("yuangong");
		Json j = new Json();
		int level = u.getRole();
		if (u != null && level < 2) {
			int res = xiaofeiService.rollback(ids);
			j.setSuccess(true);
			String msg = "成功回退" + res + "条记录";
			j.setMsg(msg);
		} else {
			j.setSuccess(false);
			j.setMsg("回退失败！");
		}
		writeJson(j);
	}

	public void remove() throws Exception {
		Map session = ActionContext.getContext().getSession();
		TuserDTO u = (TuserDTO) session.get("yuangong");
		Json j = new Json();
		if (u != null && u.getRole() < 3) {
			int res = xiaofeiService.remove(ids);
			String msg = "成功删除" + res + "条记录";
			j.setSuccess(true);
			j.setMsg(msg);
		} else {
			j.setSuccess(false);
			j.setMsg("删除失败！");
		}
		super.writeJson(j);
	}

	public void shenhe() throws Exception {
		Map session = ActionContext.getContext().getSession();
		TuserDTO u = (TuserDTO) session.get("yuangong");
		Json j = new Json();
		if (u != null && u.getRole() == 1) {
			int res = xiaofeiService.shenhe(ids, u.getId());
			String msg = "成功审核" + res + "条记录";
			j.setSuccess(true);
			j.setMsg(msg);
		} else {
			j.setSuccess(false);
			j.setMsg("审核失败！");
		}
		super.writeJson(j);
	}

	public void xiaofei() {
		Map session = ActionContext.getContext().getSession();
		TuserDTO xiaofeiyuan = (TuserDTO) session.get("yuangong");
		Json j = new Json();
		if (xiaofeiyuan != null
				&& xiaofeiService.xiaofei(id, xiaofeiyuan,
						expenses, psw)) {
			String msg = "消费成功";
			j.setSuccess(true);
			j.setMsg(msg);
		} else {
			j.setSuccess(false);
			j.setMsg("消费失败！");
		}
		super.writeJson(j);
	}

	public String daochu() throws Exception {
		//String path = "E://uploadfile//duizhangdan.xls";
		Map session = ActionContext.getContext().getSession();
		TuserDTO u = (TuserDTO) session.get("yuangong");
		if (u != null) {
			File f = new File(savePath);
			f.createNewFile();
			this.setFileName("duizhangdan.xls");
			xiaofeiService.dbToExcel(new FileOutputStream(f), u.getId(), yuangongbianhao, u.getRole(), u.getFirmid(), begintime, endtime);
			excelFile = new FileInputStream(savePath);
		}
		return "excel";
	}

}
