package com.service.impl;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.BaseDaoI;
import com.page.DataGrid;
import com.po.Firm;
import com.po.Tuser;
import com.service.FirmService;

@Service("firmService")
public class FirmServiceImpl implements FirmService {

	private BaseDaoI<Firm> baseDao;

	public BaseDaoI getBaseDao() {
		return baseDao;
	}

	@Autowired
	public void setBaseDao(BaseDaoI baseDao) {
		this.baseDao = baseDao;
	}

	@Transactional
	public List<Firm> showAll() {
		String hql = "from Firm";
		return baseDao.find(hql);
	}

	@Transactional
	public DataGrid firmShow(Integer page, Integer rows, String mingcheng) {
		String hql = "from Firm a";
		Map<String, Object> params = new HashMap<String, Object>();

		if (mingcheng != null && !mingcheng.equals("")) {
			hql += " where a.mingcheng like :mingcheng";
			params.put("mingcheng", "%%" + mingcheng + "%%");
		}
		DataGrid datagrid = new DataGrid();
		datagrid.setRows(baseDao.find(hql, params, page, rows));
		String totalhql = "select count(*) " + hql;
		datagrid.setTotal((Long) baseDao.count(totalhql, params));
		return datagrid;
	}

	@Transactional
	public boolean addFirm(String mingcheng, String dianhua) {
		Firm firm = new Firm();
		firm.setDianhua(dianhua);
		firm.setMingcheng(mingcheng);
		baseDao.save(firm);
		return true;

	}
}
