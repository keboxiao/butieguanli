package com.service.impl;

import java.io.File;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.component.TuserXLS;
import com.dao.BaseDaoI;
import com.page.DataGrid;
import com.page.TuserDTO;
import com.po.Firm;
import com.po.Tuser;
import com.service.TuserService;

@Service("tuserService")
public class TuserServiceImpl implements TuserService {

	private BaseDaoI baseDao;
	private TuserXLS tuserXLS;

	public TuserXLS getTuserXLS() {
		return tuserXLS;
	}

	@Autowired
	public void setTuserXLS(TuserXLS tuserXLS) {
		this.tuserXLS = tuserXLS;
	}

	public BaseDaoI getBaseDao() {
		return baseDao;
	}

	@Autowired
	public void setBaseDao(BaseDaoI baseDao) {
		this.baseDao = baseDao;
	}

	@Transactional
	public TuserDTO getById(String emcardid) {
		String hql = "select new com.page.TuserDTO(a.id,b.id,b.mingcheng,b.dianhua,a.username,a.emcardid,a.xingming,a.shenfenzheng,a.shouji,a.balance,a.role,a.editid) from Tuser a inner join a.firm b where a.emcardid='"
				+ emcardid + "'";
		return (TuserDTO) baseDao.get(hql);
	}

	@Transactional
	public TuserDTO login(String username, String psw) {
		String hql = "select new com.page.TuserDTO(a.id,b.id,b.mingcheng,b.dianhua,a.username,a.emcardid,a.xingming,a.shenfenzheng,a.shouji,a.balance,a.role,a.psw,a.editid) from Tuser a inner join a.firm b where a.username='"
				+ username + "'";
		TuserDTO yuangong = (TuserDTO) baseDao.get(hql);
		if (yuangong != null
				&& yuangong.getPsw().equals(DigestUtils.md5Hex(psw))) {
			return yuangong;
		} else {
			return null;
		}
	}

	@Transactional
	public DataGrid userShow(int page, int rows, Integer yuangongbianhao,
			String xingming) {
		String hql = "select new com.page.TuserDTO(a.id,b.id,b.mingcheng,b.dianhua,a.username,a.emcardid,a.xingming,a.shenfenzheng,a.shouji,a.balance,a.role,a.editid) from Tuser a inner join a.firm b";
		Map<String, Object> params = new HashMap<String, Object>();
		boolean flag = false;
		if (yuangongbianhao != null) {
			hql = hql + " where a.id= :yuangongbianhao";
			params.put("yuangongbianhao", yuangongbianhao);
			flag = true;
		}

		if (xingming != null && !xingming.equals("")) {
			if (flag) {
				hql = hql + " and a.xingming= :xingming";
			} else {
				hql += " where a.xingming= :xingming";
			}
			params.put("xingming", xingming);
		}
		DataGrid datagrid = new DataGrid();
		datagrid.setRows(baseDao.find(hql, params, page, rows));
		String totalhql = "select count(*)"
				+ hql.subSequence(144, hql.length());
		datagrid.setTotal((Long) baseDao.count(totalhql, params));
		return datagrid;
	}

	@Transactional
	public boolean edit(Integer selfrole, Integer id, String xingming,
			String emcardid, String shouji, Integer firmid, String psw,
			Integer role) {
		Tuser user = (Tuser) baseDao.get(Tuser.class, id);
		user.setXingming(xingming);
		user.setEmcardid(emcardid);
		user.setShouji(shouji);
		user.setFirm(new Firm(firmid));
		if (!psw.equals("")) {
			user.setPsw(DigestUtils.md5Hex(psw));
		}
		if (selfrole == 1 && role != null) {
			user.setRole(role);
		}
		return true;
	}

	@Transactional
	public int excelToDB(File f, Integer yuangongbianhao) {
		int rowsum = 0;
		try {
			Workbook rwb = Workbook.getWorkbook(f);
			Sheet sheet = rwb.getSheet(0);
			int columns = sheet.getColumns();
			int c = sheet.getRows();
			int row = 0;
			for (row = 0; row <= c; row++) {
				LinkedList<String> list = new LinkedList<String>();
				for (int i = 0; i < columns; i++) {
					Cell cel = sheet.getCell(i, row + 1);
					String strc = cel.getContents().toString();
					list.add(strc);
				}
				Tuser tuser = new Tuser(Integer.parseInt(list.get(0)),
						new Firm(Integer.parseInt(list.get(8))), list.get(1),
						list.get(2), list.get(3), list.get(4), list.get(5),
						0.0, null, null, yuangongbianhao);
				if (Integer.parseInt(list.get(7)) <= 3) {
					tuser.setRole(3);
				} else {
					tuser.setRole(4);
				}
				tuser.setPsw(DigestUtils.md5Hex(list.get(6)));
				baseDao.save(tuser);
				list.clear();
				rowsum++;
			}
			return rowsum;
		} catch (Exception e) {
			e.getStackTrace();
			return rowsum;
		} finally {
			f.delete();
		}
	}

	@Transactional
	public int dbToExcel(OutputStream os, Integer yuangongbianhao,
			String xingming) {
		String hql = "select new com.page.TuserDTO(a.id,b.id,b.mingcheng,b.dianhua,a.username,a.emcardid,a.xingming,a.shenfenzheng,a.shouji,a.balance,a.role,a.editid) from Tuser a inner join a.firm b";
		Map<String, Object> params = new HashMap<String, Object>();
		boolean flag = false;
		if (yuangongbianhao != null) {
			hql = hql + " where a.id= :yuangongbianhao";
			params.put("yuangongbianhao", yuangongbianhao);
			flag = true;
		}

		if (xingming != null && !xingming.equals("")) {
			if (flag) {
				hql = hql + " and a.xingming= :xingming";
			} else {
				hql += " where a.xingming= :xingming";
			}
			params.put("xingming", xingming);
		}
		String totalhql = "select count(*)"
				+ hql.subSequence(144, hql.length());
		Long total = baseDao.count(totalhql, params);
		if (total > 6000) {
			return 0;
		}
		return tuserXLS.writeExcelFile(os, baseDao.find(hql, params));
	}

	@Transactional
	public int updatepass(Integer id, String password) {
		String tmp = DigestUtils.md5Hex(password);
		String hql = "update Tuser set psw='" + tmp + "' where id=" + id;
		return baseDao.executeHql(hql);
	}

}
