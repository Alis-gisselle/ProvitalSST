/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.provitalsst.util;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;
import java.io.File;
import org.apache.pdfbox.pdmodel.interactive.form.PDCheckBox;
import org.apache.pdfbox.pdmodel.interactive.form.PDRadioButton;

public class InspeccionarPDF {
    public static void main(String[] args) throws Exception {
        try (PDDocument doc = Loader.loadPDF(new File("C:\\Users\\alis\\Documents\\NetBeansProjects\\ProvitalSST\\src\\main\\webapp\\WEB-INF\\plantillas\\ficha.pdf"))) {
            PDAcroForm form = doc.getDocumentCatalog().getAcroForm();
            for (PDField field : form.getFields()) {
                String tipo = field.getClass().getSimpleName();
                String valores = "";
                if (field instanceof PDCheckBox cb) {
                    valores = " -> valores: " + cb.getOnValues();
                } else if (field instanceof PDRadioButton rb) {
                    valores = " -> valores: " + rb.getOnValues();
                }
                System.out.println("Campo: [" + field.getFullyQualifiedName() + "]  Tipo: " + tipo + valores);
            }
        }
    }
}