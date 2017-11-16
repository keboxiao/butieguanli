package com.component;

import java.io.OutputStream;
import java.util.List;

import com.page.TuserDTO;

public interface TuserXLS {

	public int writeExcelFile(OutputStream os, List<TuserDTO> list);

}
