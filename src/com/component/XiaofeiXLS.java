package com.component;

import java.io.OutputStream;
import java.util.List;

import com.page.XiaofeiDTO;


public interface XiaofeiXLS {

	public int writeExcelFile(OutputStream os, List<XiaofeiDTO> list);

}
