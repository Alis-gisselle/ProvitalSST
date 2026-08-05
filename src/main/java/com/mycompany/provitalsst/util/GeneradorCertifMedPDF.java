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
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import org.apache.pdfbox.Loader;

/**
 *
 * @author alis
 */

public class GeneradorCertifMedPDF {

    public static void generar(String rutaPlantilla, String rutaSalida, CertifMed cert, Persona persona, String cargo) throws IOException {
        try (PDDocument documento = Loader.loadPDF(new File(rutaPlantilla))) {
            PDAcroForm form = documento.getDocumentCatalog().getAcroForm();

            int edad = Period.between(persona.getFechaNacimiento(), LocalDate.now()).getYears();
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            form.getField("nombre").setValue(persona.getNombre() + " " + persona.getApellido());
            form.getField("ci").setValue(String.valueOf(persona.getCi()));
            form.getField("edad").setValue(String.valueOf(edad));
            form.getField("cargo").setValue(cargo != null ? cargo : "");
            form.getField("fecha").setValue(cert.getFechaEmision().format(formato));
            form.getField("recomendacion").setValue(cert.getRecomendacion() != null ? cert.getRecomendacion() : "");
            form.getField("observaciones").setValue(cert.getObservaciones() != null ? cert.getObservaciones() : "");

            // Radio group: tipoEvaluacion
            PDRadioButton radioTipo = (PDRadioButton) form.getField("admisional");
            radioTipo.setValue(cert.getTipoEvaluacion());

            // Checkboxes: aptoSi / aptoNo
            PDCheckBox aptoSi = (PDCheckBox) form.getField("aptoSi");
            PDCheckBox aptoNo = (PDCheckBox) form.getField("aptoNo");
            if ("apto".equals(cert.getAptitud())) {
                aptoSi.check();
            } else {
                aptoNo.check();
            }

            form.flatten(); // convierte los campos en texto fijo (no editable) en el PDF final
            documento.save(rutaSalida);
        }
    }
}
