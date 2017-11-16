package com.component.impl;

import java.io.OutputStream;
import java.util.List;
import jxl.Workbook;
import jxl.format.UnderlineStyle;
import jxl.write.WritableFont;
import org.springframework.stereotype.Component;
import com.component.XiaofeiXLS;
import com.page.XiaofeiDTO;

@Component("xiaofeiXLS")
public class XiaofeiXLSImpl implements XiaofeiXLS {

	public synchronized int writeExcelFile(OutputStream os, List<XiaofeiDTO> list) {
		int rowsum = 0;
		try {
			jxl.write.Label labelC;
			jxl.write.WritableWorkbook wwb = Workbook.createWorkbook(os);

			jxl.write.WritableFont wfc = new jxl.write.WritableFont(
					WritableFont.ARIAL, 10, WritableFont.BOLD, false,
					UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.BLUE);
			jxl.write.WritableCellFormat wcfFC = new jxl.write.WritableCellFormat(
					wfc);
			wcfFC.setBackground(jxl.format.Colour.YELLOW);
			wcfFC.setBorder(jxl.format.Border.ALL,
					jxl.format.BorderLineStyle.THIN, jxl.format.Colour.BLACK);
			wcfFC.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
			wcfFC.setAlignment(jxl.format.Alignment.CENTRE);

			jxl.write.WritableFont wfc1 = new jxl.write.WritableFont(
					WritableFont.TIMES, 10, WritableFont.NO_BOLD, false,
					UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.BLACK);
			jxl.write.WritableCellFormat wcfFC1 = new jxl.write.WritableCellFormat(
					wfc1);
			wcfFC1.setBorder(jxl.format.Border.ALL,
					jxl.format.BorderLineStyle.THIN, jxl.format.Colour.BLACK);
			wcfFC1.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);

			jxl.write.WritableSheet ws = wwb.createSheet("导出信息", 0);
			ws.setRowView(0, 500);
			labelC = new jxl.write.Label(0, 0, "周期", wcfFC);
			ws.addCell(labelC);

			labelC = new jxl.write.Label(1, 0, "员工编号", wcfFC);
			ws.addCell(labelC);

			labelC = new jxl.write.Label(2, 0, "姓名", wcfFC);
			ws.addCell(labelC);

			labelC = new jxl.write.Label(3, 0, "区域", wcfFC);
			ws.addCell(labelC);

			labelC = new jxl.write.Label(4, 0, "操作类型", wcfFC);
			ws.addCell(labelC);

			labelC = new jxl.write.Label(5, 0, "额度", wcfFC);
			ws.addCell(labelC);

			labelC = new jxl.write.Label(6, 0, "余额", wcfFC);
			ws.addCell(labelC);

			labelC = new jxl.write.Label(7, 0, "操作人", wcfFC);
			ws.addCell(labelC);

			labelC = new jxl.write.Label(8, 0, "操作单位", wcfFC);
			ws.addCell(labelC);

			labelC = new jxl.write.Label(9, 0, "完成时间", wcfFC);
			ws.addCell(labelC);

			for (int i = 0; i < list.size(); i++) {
				rowsum = i + 1;
				labelC = new jxl.write.Label(0, rowsum, list.get(i).getZhouqi(),
						wcfFC1);
				ws.addCell(labelC);
				labelC = new jxl.write.Label(1, rowsum, list.get(i).getYuangongbianhao().toString(), wcfFC1);
				ws.addCell(labelC);
				labelC = new jxl.write.Label(2, rowsum, list.get(i)
						.getXingming(), wcfFC1);
				ws.addCell(labelC);
				labelC = new jxl.write.Label(3, rowsum, list.get(i).getQuyu(),
						wcfFC1);
				ws.addCell(labelC);

				labelC = new jxl.write.Label(4, rowsum, list.get(i).getCaozuo(), wcfFC1);
				ws.addCell(labelC);
				labelC = new jxl.write.Label(5, rowsum, list.get(i).getAmount().toString(),
						wcfFC1);
				ws.addCell(labelC);
				labelC = new jxl.write.Label(6, rowsum, list.get(i).getBalance().toString(), wcfFC1);
				ws.addCell(labelC);
				labelC = new jxl.write.Label(7, rowsum,
						list.get(i).getShenqingren(), wcfFC1);
				ws.addCell(labelC);
				labelC = new jxl.write.Label(8, rowsum, list.get(i).getCaozuoquyu(),
						wcfFC1);
				ws.addCell(labelC);
				labelC = new jxl.write.Label(9, rowsum, list.get(i).getShenheriqi(),
						wcfFC1);
				ws.addCell(labelC);
			}
			// 写入Exel工作表
			wwb.write();
			// 关闭Excel工作薄对象
			wwb.close();
			os.close();

		} catch (Exception e) {
			e.getStackTrace();
		}
		return rowsum;
	}

}
