<%--<%@ page contentType="text/html; ISO-8859-1" %>--%>
<%@ page contentType="application/pdf; text/html; ISO-8859-1" %>
<%--<%@ page contentType="application/docx" %>--%>
<%@ page import="net.sf.jasperreports.engine.*" %>
<%@ page import="net.sf.jasperreports.engine.data.*" %>
<%@ page import="java.io.*" %>
<%@ page import="java.util.*" %>
<%@ page import="net.sf.jasperreports.engine.export.ooxml.JRDocxExporter" %>
<%

    try{
        List<Map<String,?>> dataSource = (List<Map<String, ?>>) request.getAttribute("lista");
        JRDataSource jrDataSource = new JRBeanCollectionDataSource(dataSource);
        String jrxmlFile = session.getServletContext().getRealPath("/WEB-INF/pages/prueba.jrxml"); ///Ruta al reporte
//        String jrxmlFile = session.getServletContext().getContextPath()+"prueba.jrxml";
        InputStream input = new FileInputStream(new File(jrxmlFile));
        JasperReport jasperReport = JasperCompileManager.compileReport(input);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,null,jrDataSource);
        JasperExportManager.exportReportToPdfStream(jasperPrint,response.getOutputStream());


        //TODO export docx

        /*JRDocxExporter docxExporter = new JRDocxExporter();
        docxExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        docxExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, response.getOutputStream());
        docxExporter.exportReport();*/

//        String htmlFile = session.getServletContext().getRealPath("/WEB-INF/pages/reporte.jsp");
//        JasperExportManager.exportReportToHtmlFile(jasperPrint,htmlFile);

        response.getOutputStream().flush();
        response.getOutputStream().close();
        System.out.print("holaa");
    }   catch (Exception e){
            e.printStackTrace();
    }

%>