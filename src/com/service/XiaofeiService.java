package com.service;

import java.io.File;
import java.io.OutputStream;

import com.page.DataGrid;
import com.page.TuserDTO;

public interface XiaofeiService {

	public boolean xiaofei(Integer yuangongbianhao, TuserDTO caozuoyuan,
			Double expenses, String psw);

	public DataGrid find(Integer selfid, Integer findid, Integer role,
			Integer firmid, String begintime, String endtime, Integer page,
			Integer rows);
	
	public int excelToDB(File f, Integer yuangongbianhao);
	
	public int submit(String ids);
	
	public DataGrid shenqingshow(int page, int rows, Integer yuangongbianhao);
	
	public DataGrid shenheshow(int page, int rows);
	
	public int rollback(String ids);
	
	public int remove(String ids);
	
	public int shenhe(String ids, Integer yuangongbianhao);
	
	public int dbToExcel(OutputStream os, Integer selfid, Integer findid,
			Integer role, Integer firmid, String begintime, String endtime);

}
