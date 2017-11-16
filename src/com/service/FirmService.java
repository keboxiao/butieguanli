package com.service;

import java.util.List;

import com.page.DataGrid;
import com.po.Firm;

public interface FirmService {

	public List<Firm> showAll();
	
	public DataGrid firmShow(Integer page, Integer rows, String mingcheng);

	public boolean addFirm(String mingcheng, String dianhua);

}
