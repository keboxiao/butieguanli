package com.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;

import com.opensymphony.xwork2.ActionContext;
import com.page.Json;
import com.page.TuserDTO;
import com.service.TuserService;

public class TuserAction extends BaseAction {

	private String username;
	private String emcardid;
	private Integer yuangongbianhao;
	private Integer page;
	private Integer rows;
	private String xingming;
	private String psw;
	private File upload;
	private String uploadFileName;
	private String savePath;
	private String shouji;
	private Integer firmid;
	private Integer id;
	private Integer role;
	private String wpswd1;
	private InputStream excelFile;
	private String fileName;
	private TuserService tuserService;

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

	public String getWpswd1() {
		return wpswd1;
	}

	public void setWpswd1(String wpswd1) {
		this.wpswd1 = wpswd1;
	}

	public Integer getRole() {
		return role;
	}

	public void setRole(Integer role) {
		this.role = role;
	}

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

	public String getEmcardid() {
		return emcardid;
	}

	public void setEmcardid(String emcardid) {
		this.emcardid = emcardid;
	}

	public Integer getYuangongbianhao() {
		return yuangongbianhao;
	}

	public void setYuangongbianhao(Integer yuangongbianhao) {
		this.yuangongbianhao = yuangongbianhao;
	}

	public String getPsw() {
		return psw;
	}

	public void setPsw(String psw) {
		this.psw = psw;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public String getXingming() {
		return xingming;
	}

	public void setXingming(String xingming) {
		this.xingming = xingming;
	}

	public String getShouji() {
		return shouji;
	}

	public void setShouji(String shouji) {
		this.shouji = shouji;
	}

	public Integer getFirmid() {
		return firmid;
	}

	public void setFirmid(Integer firmid) {
		this.firmid = firmid;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public TuserService getTuserService() {
		return tuserService;
	}

	public void setTuserService(TuserService tuserService) {
		this.tuserService = tuserService;
	}

	public void getById() {
		super.writeJson(tuserService.getById(emcardid));
	}

	public void getBalance() {
		Map session = ActionContext.getContext().getSession();
		TuserDTO u = (TuserDTO) session.get("yuangong");
		super.writeJson(tuserService.getById(u.getEmcardid()));
	}

	public String login() {
		TuserDTO u = tuserService.login(username, psw);
		if (u != null) {
			Map session = ActionContext.getContext().getSession();
			session.put("yuangong", u);
			return SUCCESS;
		} else {
			return LOGIN;
		}
	}

	public void userShow() {
		Map session = ActionContext.getContext().getSession();
		TuserDTO u = (TuserDTO) session.get("yuangong");
		if (u != null && u.getRole() < 3) {
			super.writeJson(tuserService.userShow(page, rows, yuangongbianhao,
					xingming));
		}
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
				tuserService.excelToDB(target, u.getId());
			} catch (IOException e) {
				e.printStackTrace();
			}
			return SUCCESS;
		} else {
			return ERROR;
		}
	}

	public void edit() throws Exception {
		Map session = ActionContext.getContext().getSession();
		TuserDTO u = (TuserDTO) session.get("yuangong");
		Json j = new Json();
		if (u != null && u.getRole() < 3) {
			tuserService.edit(u.getRole(), id, xingming, emcardid, shouji,
					firmid, psw, role);
			String msg = "修改成功";
			j.setSuccess(true);
			j.setMsg(msg);
		} else {
			j.setSuccess(false);
			j.setMsg("修改失败！");
		}
		super.writeJson(j);
	}
	
	public String updatepass() {
		Map session = ActionContext.getContext().getSession();
		TuserDTO u = (TuserDTO) session.get("yuangong");
		int res = 0;
		if (u != null && u.getPsw().equals(DigestUtils.md5Hex(psw))) {
			res = tuserService.updatepass(u.getId(), wpswd1);
		}
		if (res > 0) {
			return SUCCESS;
		} else {
			return ERROR;
		}
	}

	public String daochu() throws Exception {
		Map session = ActionContext.getContext().getSession();
		TuserDTO u = (TuserDTO) session.get("yuangong");
		if (u != null && u.getRole()<3) {
			File f = new File(savePath);
			f.createNewFile();
			this.setFileName("userlist.xls");
			tuserService.dbToExcel(new FileOutputStream(f), yuangongbianhao, xingming);
			excelFile = new FileInputStream(savePath);
		}
		return "excel";
	}

	public String logout() {
		Map session = ActionContext.getContext().getSession();
		session.clear();
		return SUCCESS;
	}
}
