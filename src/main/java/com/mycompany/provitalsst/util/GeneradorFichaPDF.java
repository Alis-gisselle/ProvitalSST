/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.provitalsst.util;

import com.mycompany.provitalsst.modelo.AntecedenteLaboral;
import com.mycompany.provitalsst.modelo.AntecedentePersonal;
import com.mycompany.provitalsst.modelo.DatosPersonalesFicha;
import com.mycompany.provitalsst.modelo.EPP;
import com.mycompany.provitalsst.modelo.ExamenFisico;
import com.mycompany.provitalsst.modelo.FichaCompleta;
import com.mycompany.provitalsst.modelo.FirmaFicha;
import com.mycompany.provitalsst.modelo.Habito;
import com.mycompany.provitalsst.modelo.PuestoErgonomia;
import com.mycompany.provitalsst.modelo.RiesgoLaboral;
import com.mycompany.provitalsst.modelo.TrastornoMusculo;
import com.mycompany.provitalsst.modelo.ZonaAfectada;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDCheckBox;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;
import org.apache.pdfbox.pdmodel.interactive.form.PDRadioButton;

/**
 *
 * @author alis
 */
public class GeneradorFichaPDF {

    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void generar(String rutaPlantilla, String rutaSalida, FichaCompleta d) throws IOException {
        try (PDDocument documento = Loader.loadPDF(new File(rutaPlantilla))) {
            PDAcroForm form = documento.getDocumentCatalog().getAcroForm();

         
            setTexto(form, "nombre_apellido", d.getPersona().getNombre() + " " + d.getPersona().getApellido());
            setTexto(form, "ci", String.valueOf(d.getPersona().getCi()));
            int edad = Period.between(d.getPersona().getFechaNacimiento(), LocalDate.now()).getYears();
            setTexto(form, "edad", String.valueOf(edad));
            setTexto(form, "fecha", d.getFicha().getFecha().format(FMT));
            setTexto(form, "cargo", d.getCargo() != null ? d.getCargo() : "");
            setRadio(form, "admisional", d.getFicha().getTipoEvaluacion());

           
            DatosPersonalesFicha dp = d.getDatosPersonales();
            if (dp != null) {
                setRadio(form, "M", dp.getSexo());
                if (dp.getFum() != null) setTexto(form, "fum", dp.getFum().format(FMT));
                setTexto(form, "area", dp.getArea());
                setTexto(form, "antiguedad", dp.getAntiguedadCargo());
                setRadio(form, "primaria", dp.getGradoFormacion());
                setTexto(form, "telefono", dp.getTelefono());
                setTexto(form, "domicilio", dp.getDomicilio());
                setTexto(form, "contacto_emergencia",
                    (dp.getContactoEmergencia() != null ? dp.getContactoEmergencia() : "") +
                    (dp.getTelefonoEmergencia() != null ? " - " + dp.getTelefonoEmergencia() : ""));
                if (dp.getNumHijos() != null) setTexto(form, "n_hijos", String.valueOf(dp.getNumHijos()));
                setTexto(form, "hijos_edades", dp.getEdadesHijos());
            }

            
            AntecedentePersonal ap = d.getAntecedentePersonal();
            if (ap != null) {
                setRadio(form, "siEnfermedades", ap.isEnfermedades() ? "siEnfermedades" : "noEnfermedades");
                setRadio(form, "siCirugias", ap.isCirugias() ? "siCirugias" : "noCirugias");
                setRadio(form, "si_medicamento", ap.isMedicamentos() ? "si_medicamento" : "no_medicamento");
                setRadio(form, "si_alergias", ap.isAlergias() ? "si_alergias" : "no_alergias");
                setRadio(form, "si_antecedentes", ap.isOtrosRelevantes() ? "si_antecedentes" : "no_antecedentes");
            }

           
            Habito h = d.getHabito();
            if (h != null) {
                marcarCheckSiNo(form, "si_fuma", "no_fuma", h.isFuma());
                marcarCheckSiNo(form, "si_alcohol", "no_alcohol", h.isConsumeAlcohol());
                marcarCheckSiNo(form, "si_actividad", "no_actividad", h.isActividadFisica());
                marcarCheckSiNo(form, "si_sue#C3#B1oadecuado", "no_sue#C3#B1oadecuado", h.isSuenoAdecuado());
                marcarCheckSiNo(form, "si_habitosrevelantes", "no_habitosrevelantes", h.isOtrosHabitos());
            }

            AntecedenteLaboral al = d.getAntecedenteLaboral();
            if (al != null) {
                setTexto(form, "empresa(antecedentes)", al.getEmpresaAnterior());
                setTexto(form, "puesto(antecedente)", al.getPuestoAnterior());
                if (al.getPeriodoDesde() != null) setTexto(form, "fecha_desde(antecedentes)", al.getPeriodoDesde().format(FMT));
                if (al.getPeriodoHasta() != null) setTexto(form, "fecha_hasta(antecedentes)", al.getPeriodoHasta().format(FMT));
            }

            PuestoErgonomia pe = d.getPuestoErgonomia();
            if (pe != null) {
                setTexto(form, "descripcion_puesto", pe.getDescripcionGeneral());
                setTexto(form, "tareas_principales", pe.getTareasPrincipales());
                setRadio(form, "dinamica_tipoActividad",
                    "dinamica".equals(pe.getTipoActividad()) ? "dinamica_tipoActividad" : "estatica_tipoActividad");
                String esfuerzo = pe.getEsfuerzoFisico();
                setRadio(form, "liviano_esfuerzo",
                    esfuerzo == null ? null : esfuerzo + "_esfuerzo");
                setRadio(form, "no_levantaCarga", pe.isLevantaCargas() ? "si_levantaCarga" : "no_levantaCarga");
                if (pe.getPesoAprox() != null) setTexto(form, "pesoCargas", pe.getPesoAprox().toString());
            }
            
            Map<String, String[]> mapaCamposRiesgo = new HashMap<>();

            mapaCamposRiesgo.put("ruido", new String[]{"si_ruido", "2menor", "si_ruido", "no_ruido"});
            mapaCamposRiesgo.put("vibraciones", new String[]{"si_vibra", "2menor_2", "si_vibra", "no_vibra"});
            mapaCamposRiesgo.put("polvo", new String[]{"si_polvo", "2menor_3", "Yes", "no_polvo"});
            mapaCamposRiesgo.put("estres_termico", new String[]{"si_termico", "2menor_4", "si_termico", "no_termico"});
            mapaCamposRiesgo.put("material_biologico", new String[]{"si_materialb", "2menor_5", "si_materialb", "no_materialb"});
            mapaCamposRiesgo.put("manipulacion_cargas", new String[]{"si_carga", "2menor_6", "si_carga", "no_carga"});
            mapaCamposRiesgo.put("radiacion_ionizante", new String[]{"si_radiacion", "2menor_7", "si_radiacion", "no_radiacion"});
            mapaCamposRiesgo.put("trabajo_alturas", new String[]{"si_trabajoaltura", "2menor_8", "si_trabajoaltura", "no_trabajoaltura"});
            mapaCamposRiesgo.put("espacios_confinados", new String[]{"si_confinado", "2menor_9", "si_confinado", "no_confinado"});
            mapaCamposRiesgo.put("pantallas", new String[]{"si_PVD", "2menor_10", "si_PVD", "no_PVD"});

            if (d.getRiesgos() != null) {
                for (RiesgoLaboral r : d.getRiesgos()) {
                    String[] campos = mapaCamposRiesgo.get(r.getFactor());
                    if (campos == null) continue;
                    String campoSiNo = campos[0];
                    String campoTiempo = campos[1];
                    String valorSi = campos[2];
                    String valorNo = campos[3];

                    setRadio(form, campoSiNo, r.isExpuesto() ? valorSi : valorNo);

                    if (r.isExpuesto() && r.getTiempoExposicion() != null) {
                        String valorTiempo = switch (r.getTiempoExposicion()) {
                        case "menos_2h" -> "2menor";
                        case "2_a_4h" -> "2_4";
                        case "mas_4h" -> "4mayor";
                        default -> null;
                    };
                    setRadio(form, campoTiempo, valorTiempo);
                 }
            }
    }

            EPP epp = d.getEpp();
            if (epp != null) {
                marcarCheck(form, "tapaboca", epp.isTapaboca());
                marcarCheck(form, "auditiva", epp.isProteccionAuditiva());
                marcarCheck(form, "casco", epp.isCasco());
                marcarCheck(form, "gafas", epp.isGafas());
                marcarCheck(form, "botas", epp.isBotas());
                marcarCheck(form, "guantes", epp.isGuantes());
                marcarCheck(form, "delantal", epp.isDelantal());
                if (epp.getOtros() != null && !epp.getOtros().isEmpty()) {
                    marcarCheck(form, "otros_2", true);
                    setTexto(form, "otro_especificar", epp.getOtros());
                }
            }

            TrastornoMusculo tm = d.getTrastornoMusculo();
            if (tm != null) {
                setRadio(form, "si_postura", tm.isPosturasForzadas() ? "si_postura" : "no_postura");
                setRadio(form, "si_movimiento", tm.isMovimientosRepetitivos() ? "si_movimiento" : "no_movimiento");
                setRadio(form, "si_TrabajoElevado", tm.isRitmoElevado() ? "si_TrabajoElevado" : "no_TrabajoElevado");
                setRadio(form, "si_reposo", tm.isReposoInsuficiente() ? "si_reposo" : "no_reposo");
                setRadio(form, "si_tratamientoRecibido", tm.isRecibioTratamiento() ? "si_tratamientoRecibido" : "no_tratamientoRecibido");
                setTexto(form, "observaciones_adicionales_tratamiento", tm.getObservaciones());
                setRadio(form, "sentado", tm.getPosturaPredominante());
                setTexto(form, "tiempo_posturapredominante", tm.getTiempoSintoma());
                setRadio(form, "si_restricciones", tm.isRealizaRestricciones() ? "si_restricciones" : "no_restricciones");
            }

            if (d.getZonas() != null) {
                for (ZonaAfectada z : d.getZonas()) {
                    String campoZona = switch (z.getZona()) {
                        case "muneca_mano" -> "mu#C3#B1eca/mano";
                        default -> z.getZona();
                    };
                    marcarCheck(form, campoZona, true);
                    if (z.getIntensidad() != null) {
                        setRadio(form, "intensidad1", "intensidad" + z.getIntensidad());
                    }
                }
            }

            ExamenFisico ef = d.getExamenFisico();
            if (ef != null) {
                if (ef.getPeso() != null) setTexto(form, "peso_examen", ef.getPeso().toString());
                if (ef.getEstatura() != null) {
                    setTexto(form, "estatura", ef.getEstatura().toString());
                    if (ef.getPeso() != null) {
                        double estaturaM = ef.getEstatura().doubleValue() / 100.0;
                        double imc = ef.getPeso().doubleValue() / (estaturaM * estaturaM);
                        setTexto(form, "IMC", String.format("%.2f", imc));
                    }
                }
                setTexto(form, "presionArterial", ef.getPresionArterial());
                if (ef.getFrecuenciaCardiaca() != null) setTexto(form, "frecuencia", String.valueOf(ef.getFrecuenciaCardiaca()));
                setTexto(form, "ojo_derecho", ef.getAgudezaVisualDerecho());
                setTexto(form, "ojo_izquierdo", ef.getAgudezaVisualIzquierdo());
            }

            FirmaFicha firma = d.getFirma();
            if (firma != null) {
                setTexto(form, "ci_2", firma.getCiTrabajador());
                if (firma.getFechaFirmaTrabajador() != null) setTexto(form, "fecha_declaracion", firma.getFechaFirmaTrabajador().format(FMT));
                setTexto(form, "ci_tecnico", firma.getCiTecnico());
                if (firma.getFechaFirmaTecnico() != null) setTexto(form, "fecha_tecnicofirma", firma.getFechaFirmaTecnico().format(FMT));
            }

            form.flatten();
            documento.save(rutaSalida);
        }
    }

    private static void setTexto(PDAcroForm form, String nombre, String valor) {
        if (valor == null) return;
        try {
            PDField campo = form.getField(nombre);
            if (campo != null) campo.setValue(valor);
        } catch (Exception ex) {
            System.out.println("No se pudo llenar el campo texto [" + nombre + "]: " + ex.getMessage());
        }
    }

    private static void setRadio(PDAcroForm form, String nombreCampo, String valor) {
        if (valor == null) return;
        try {
            PDField campo = form.getField(nombreCampo);
            if (campo instanceof PDRadioButton rb) {
                rb.setValue(valor);
            }
        } catch (Exception ex) {
            System.out.println("No se pudo marcar el radio [" + nombreCampo + "] con valor [" + valor + "]: " + ex.getMessage());
        }
    }

    private static void marcarCheck(PDAcroForm form, String nombreCampo, boolean marcar) {
        if (!marcar) return;
        try {
            PDField campo = form.getField(nombreCampo);
            if (campo instanceof PDCheckBox cb) {
                cb.check();
            }
        } catch (Exception ex) {
            System.out.println("No se pudo marcar el checkbox [" + nombreCampo + "]: " + ex.getMessage());
        }
    }

    private static void marcarCheckSiNo(PDAcroForm form, String campoSi, String campoNo, boolean valor) {
        marcarCheck(form, valor ? campoSi : campoNo, true);
    }
}