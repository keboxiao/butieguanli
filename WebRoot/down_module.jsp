<%@ page 
   contentType="text/html; charset=gb2312"
   import="java.sql.*,java.lang.*,javax.sql.*,javax.naming.*,java.util.*,java.io.*"
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>无标题文档</title>
</head>

<body>

<%
            byte[] bytes=new byte[4096];
            int length = -1;
            File f2=new File("D:/butiedownload/补贴发放导入模版.xls");
            FileInputStream f1 = new FileInputStream(f2);
            //byte[] fb = new byte[f1.available()];
            int i=f1.available();
            response.reset();
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Accept-Ranges","bytes");
            response.setHeader("Accept-Length",(new Integer(i)).toString());
            response.setHeader("Content-disposition", "attachment; filename="+new String("补贴发放导入模版.xls".getBytes("gb2312"),"iso8859-1"));
            //ByteArrayInputStream bais = new ByteArrayInputStream(fb);
            while ((length = f1.read(bytes)) != -1 ) {
                response.getOutputStream().write(bytes,0,length);
                response.flushBuffer();
            }            
            
            f1.close();

%>   
</body>
</html>
