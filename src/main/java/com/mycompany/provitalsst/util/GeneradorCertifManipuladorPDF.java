package com.mycompany.provitalsst.util;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

public class GeneradorCertifManipuladorPDF {

    public static void generar(String rutaJasper, String rutaSalida, int idCertifManipulador, Connection con) throws JRException {
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("idCertifManipulador", idCertifManipulador);

        JasperPrint jasperPrint = JasperFillManager.fillReport(rutaJasper, parametros, con);
        JasperExportManager.exportReportToPdfFile(jasperPrint, rutaSalida);
    }
}