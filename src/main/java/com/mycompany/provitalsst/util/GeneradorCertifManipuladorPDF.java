package com.mycompany.provitalsst.util;

import com.mycompany.provitalsst.modelo.CertifManipulador;
import com.mycompany.provitalsst.modelo.Persona;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDRadioButton;
import java.io.File;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

public class GeneradorCertifManipuladorPDF {

    public static void generar(String rutaPlantilla, String rutaSalida, CertifManipulador cert,
                                Persona persona, String nombreEmpresa) throws IOException {
        try (PDDocument documento = Loader.loadPDF(new File(rutaPlantilla))) {
            PDAcroForm form = documento.getDocumentCatalog().getAcroForm();

            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            form.getField("empresa").setValue(nombreEmpresa != null ? nombreEmpresa : "Independiente");
            form.getField("nombre_apellido").setValue(persona.getNombre() + " " + persona.getApellido());
            form.getField("ci").setValue(String.valueOf(persona.getCi()));
            form.getField("fecha").setValue(cert.getFechaEmision().format(formato));
            form.getField("recomendaciones").setValue(cert.getRecomendaciones() != null ? cert.getRecomendaciones() : "");

            PDRadioButton radioAptitud = (PDRadioButton) form.getField("apto");
            radioAptitud.setValue(cert.getAptitud());

            form.flatten();
            documento.save(rutaSalida);
        }
    }
}