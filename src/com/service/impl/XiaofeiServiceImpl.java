package com.service.impl;

import java.io.File;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

import com.component.XiaofeiXLS;
import com.dao.BaseDaoI;
import com.page.DataGrid;
import com.page.TuserDTO;
import com.po.Firm;
import com.po.Tuser;
import com.po.Xiaofei;
import com.service.XiaofeiService;

@Service("xiaofeiService")
public class XiaofeiServiceImpl implements XiaofeiService {

	private BaseDaoI baseDao;
	private XiaofeiXLS xiaofeiXLS;

	public BaseDaoI getBaseDao() {
		return baseDao;
	}

	@Autowired
	public void setBaseDao(BaseDaoI baseDao) {
		this.baseDao = baseDao;
	}

	public XiaofeiXLS getXiaofeiXLS() {
		return xiaofeiXLS;
	}

	@Autowired
	public void setXiaofeiXLS(XiaofeiXLS xiaofeiXLS) {
		this.xiaofeiXLS = xiaofeiXLS;
	}

	@Transactional
	public int excelToDB(File f, Integer yuangongbianhao) {
		int rowsum = 0;
		Tuser user = (Tuser) baseDao.get(Tuser.class, yuangongbianhao);
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
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Xiaofei xiaofei = new Xiaofei(user, user.getFirm(), null,
						new Tuser(Integer.parseInt(list.get(1))), list.get(0),
						"收入", 0, Double.parseDouble(list.get(2)));
				baseDao.save(xiaofei);
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
	public int dbToExcel(OutputStream os, Integer selfid, Integer findid,
			Integer role, Integer firmid, String begintime, String endtime) {
		Map<String, Object> params = new HashMap<String, Object>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String hql = "select new com.page.XiaofeiDTO(a.id,b.id,b.xingming,b.shenfenzheng,a.caozuo,a.amount,d.xingming,a.zhouqi,c.mingcheng,a.state,e.mingcheng,a.shenqingriqi,a.balance,a.shenheriqi) from Xiaofei a inner join a.tuserByUserid b inner join a.firmByFirmid c inner join a.tuserByShenqingid d inner join a.firmByCaozuofirmid e where a.state= :state";
		Integer state = 2;
		params.put("state", state);
		if (role < 3 && findid != null) {
			hql = hql + " and b.id= :findid";
			params.put("findid", findid);
		} else if (role == 3) {
			hql = hql + " and b.id= :selfid";
			params.put("selfid", selfid);
		}

		if (begintime != null && !begintime.equals("")) {
			hql = hql + " and a.shenheriqi >= :begintime";
			try {
				begintime += " 00:00:00";
				Date dbegintime = sdf.parse(begintime);
				params.put("begintime", dbegintime);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		if (endtime != null && !endtime.equals("")) {
			hql = hql + " and a.shenheriqi <= :endtime";
			try {
				endtime += " 23:59:59";
				Date dendtime = sdf.parse(endtime);
				params.put("endtime", dendtime);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		if (firmid >= 2 && firmid <= 6 && role != 3) {
			hql += " and c.id= :ufirmid";
			params.put("ufirmid", firmid);
		}

		if (role == 4) {
			hql += " and e.id= :cfirmid and a.caozuo= :caozuo";
			params.put("cfirmid", firmid);
			params.put("caozuo", "支出");
		}
		String totalhql = "select count(*)" + hql.substring(176, hql.length());
		Long total = (Long) baseDao.count(totalhql, params);
		if (total > 5000) {
			return 0;
		}
		return xiaofeiXLS.writeExcelFile(os, baseDao.find(hql, params));

	}

	@Transactional
	public DataGrid shenqingshow(int page, int rows, Integer yuangongbianhao) {
		Map<String, Object> params = new HashMap<String, Object>();
		Integer state = 0;
		params.put("state", state);
		params.put("yuangongbianhao", yuangongbianhao);
		String hql = "select new com.page.XiaofeiDTO(a.id,b.id,b.xingming,b.shenfenzheng,a.caozuo,a.amount,d.xingming,a.zhouqi,c.mingcheng,a.state,e.mingcheng) from Xiaofei a inner join a.tuserByUserid b inner join b.firm c inner join a.tuserByShenqingid d inner join a.firmByCaozuofirmid e where a.state= :state and d.id= :yuangongbianhao";
		DataGrid datagrid = new DataGrid();
		datagrid.setRows(baseDao.find(hql, params, page, rows));
		String totalhql = "select count(*)"
				+ hql.subSequence(137, hql.length());
		datagrid.setTotal((Long) baseDao.count(totalhql, params));
		return datagrid;
	}

	@Transactional
	public DataGrid shenheshow(int page, int rows) {
		Map<String, Object> params = new HashMap<String, Object>();
		Integer state = 1;
		params.put("state", state);
		String hql = "select new com.page.XiaofeiDTO(a.id,b.id,b.xingming,b.shenfenzheng,a.caozuo,a.amount,d.xingming,a.zhouqi,c.mingcheng,a.state,e.mingcheng,a.shenqingriqi) from Xiaofei a inner join a.tuserByUserid b inner join b.firm c inner join a.tuserByShenqingid d inner join a.firmByCaozuofirmid e where a.state= :state";
		DataGrid datagrid = new DataGrid();
		datagrid.setRows(baseDao.find(hql, params, page, rows));
		String totalhql = "select count(*)"
				+ hql.subSequence(153, hql.length());
		datagrid.setTotal((Long) baseDao.count(totalhql, params));
		return datagrid;
	}

	@Transactional
	public DataGrid xiaofeishow(int page, int rows, Integer yuangongbianhao,
			Integer role) {
		Map<String, Object> params = new HashMap<String, Object>();
		Integer state = 2;
		params.put("state", state);
		String hql = "select new com.page.XiaofeiDTO(a.id,b.id,b.xingming,a.caozuo,a.amount,d.xingming,a.zhouqi,c.mingcheng,a.state,e.mingcheng,a.shenqingriqi,a.shenheriqi) from Xiaofei a inner join a.tuserByUserid b inner join a.firmByFirmid c inner join a.tuserByShenqingid d inner join a.firmByCaozuofirmid e where a.state= :state";
		DataGrid datagrid = new DataGrid();
		datagrid.setRows(baseDao.find(hql, params, page, rows));
		String totalhql = "select count(*)"
				+ hql.subSequence(151, hql.length());
		datagrid.setTotal((Long) baseDao.count(totalhql, params));
		return datagrid;
	}

	@Transactional
	public int submit(String ids) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d = new Date();
		String hql = "update Xiaofei t set t.state=1 " + " , t.shenqingriqi='"
				+ sdf.format(d) + "' where t.id in (" + ids + ")";
		return baseDao.executeHql(hql);
	}

	@Transactional
	public int shenhe(String ids, Integer yuangongbianhao) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d = new Date();
		String sql = "update tuser a,xiaofei b"
				+ " set a.balance=a.balance+b.amount,b.balance=a.balance+b.amount,b.firmid=a.firmid where a.id=b.userid and b.id in ("
				+ ids + ")";
		baseDao.executeSql(sql);
		String hql = "update Xiaofei t set t.state=2 " + " , t.shenheriqi='"
				+ sdf.format(d) + "',t.tuserByShenheid=" + yuangongbianhao
				+ " where t.id in (" + ids + ")";
		return baseDao.executeHql(hql);
	}

	@Transactional
	public int remove(String ids) {
		String hql = "delete Xiaofei t where t.id in (" + ids + ")";
		return baseDao.executeHql(hql);
	}

	@Transactional
	public int rollback(String ids) {
		String hql = "update Xiaofei t set t.state=0 where t.id in (" + ids
				+ ")";
		return baseDao.executeHql(hql);
	}

	@Transactional
	public boolean xiaofei(Integer yuangongbianhao, TuserDTO caozuoyuan,
			Double expenses, String psw) {
		// String hql = "from Tuser where id=" + yuangongbianhao;
		Tuser yuangong = (Tuser) baseDao.get(Tuser.class, yuangongbianhao);
		if (!yuangong.getPsw().equals(DigestUtils.md5Hex(psw))) {
			return false;
		}
		if (yuangong.getBalance() < expenses) {
			return false;
		}
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		Integer state = 2;
		Xiaofei xiaofei = new Xiaofei(new Tuser(caozuoyuan.getId()), new Firm(
				caozuoyuan.getFirmid()), new Tuser(yuangong.getId()),
				yuangong.getFirm(), new Tuser(yuangong.getId()),
				sdf.format(date), "支出", yuangong.getBalance() - expenses,
				new Timestamp(date.getTime()), new Timestamp(date.getTime()),
				state, expenses);
		yuangong.setBalance(yuangong.getBalance() - expenses);
		baseDao.saveOrUpdate(yuangong);
		baseDao.save(xiaofei);
		return true;
	}

	@Transactional
	public DataGrid find(Integer selfid, Integer findid, Integer role,
			Integer firmid, String begintime, String endtime, Integer page,
			Integer rows) {
		Map<String, Object> params = new HashMap<String, Object>();
		boolean flag = false;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String hql = "select new com.page.XiaofeiDTO(a.id,b.id,b.xingming,b.shenfenzheng,a.caozuo,a.amount,d.xingming,a.zhouqi,c.mingcheng,a.state,e.mingcheng,a.shenqingriqi,a.balance,a.shenheriqi) from Xiaofei a inner join a.tuserByUserid b inner join a.firmByFirmid c inner join a.tuserByShenqingid d inner join a.firmByCaozuofirmid e where a.state= :state";
		Integer state = 2;
		params.put("state", state);
		if (role < 3 && findid != null) {
			hql = hql + " and b.id= :findid";
			params.put("findid", findid);
		} else if (role == 3) {
			hql = hql + " and b.id= :selfid";
			params.put("selfid", selfid);
		}

		if (begintime != null && !begintime.equals("")) {
			hql = hql + " and a.shenheriqi >= :begintime";
			try {
				begintime += " 00:00:00";
				Date dbegintime = sdf.parse(begintime);
				params.put("begintime", dbegintime);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		if (endtime != null && !endtime.equals("")) {
			hql = hql + " and a.shenheriqi <= :endtime";
			try {
				endtime += " 23:59:59";
				Date dendtime = sdf.parse(endtime);
				params.put("endtime", dendtime);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		if (firmid >= 2 && firmid <= 6 && role != 3) {
			hql += " and c.id= :ufirmid";
			params.put("ufirmid", firmid);
		}

		if (role == 4) {
			hql += " and e.id= :cfirmid and a.caozuo= :caozuo";
			params.put("cfirmid", firmid);
			params.put("caozuo", "支出");
		}
		DataGrid datagrid = new DataGrid();
		datagrid.setRows(baseDao.find(hql, params, page, rows));
		String totalhql = "select count(*)"
				+ hql.subSequence(176, hql.length());
		datagrid.setTotal((Long) baseDao.count(totalhql, params));
		return datagrid;
	}

}
