/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.provitalsst.util;

import com.mycompany.provitalsst.modelo.CertifMed;
import com.mycompany.provitalsst.modelo.Persona;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDCheckBox;
import org.apache.pdfbox.pdmodel.interactive.form.PDRadioButton;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.apache.pdfbox.Loader;

/**
 *
 * @author alis
 */

public class GeneradorCertifMedPDF {

    public static void generar(String rutaJasper, String rutaSalida, int idCertifMed, Connection con) throws JRException {
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("idCertifMed", idCertifMed);

        JasperPrint jasperPrint = JasperFillManager.fillReport(rutaJasper, parametros, con);
        JasperExportManager.exportReportToPdfFile(jasperPrint, rutaSalida);
    }
}