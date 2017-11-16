package com.service;

import java.io.File;
import java.io.OutputStream;

import com.page.DataGrid;
import com.page.TuserDTO;


public interface TuserService {

	public TuserDTO getById(String emcardid);
	
	public TuserDTO login(String username, String psw);
	
	public DataGrid userShow(int page, int rows, Integer yuangongbianhao,
			String xingming);
	
	public int excelToDB(File f, Integer yuangongbianhao);
	
	public boolean edit(Integer selfrole, Integer id, String xingming,
			String emcardid, String shouji, Integer firmid, String psw,
			Integer role);

	public int updatepass(Integer id, String password);
	
	public int dbToExcel(OutputStream os, Integer yuangongbianhao,
			String xingming);

}
