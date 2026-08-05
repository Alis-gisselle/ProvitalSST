/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.provitalsst.modelo;

/**
 *
 * @author alis
 */
public class TrastornoMusculo {
    private int idFicha;
    private boolean posturasForzadas;
    private boolean movimientosRepetitivos;
    private boolean ritmoElevado;
    private boolean reposoInsuficiente;
    private String posturaPredominante;
    private String tiempoSintoma;
    private boolean recibioTratamiento;
    private boolean realizaRestricciones;
    private String observaciones;

    public TrastornoMusculo() {
    }

    public int getIdFicha() {
        return idFicha;
    }

    public void setIdFicha(int idFicha) {
        this.idFicha = idFicha;
    }

    public boolean isPosturasForzadas() {
        return posturasForzadas;
    }

    public void setPosturasForzadas(boolean posturasForzadas) {
        this.posturasForzadas = posturasForzadas;
    }

    public boolean isMovimientosRepetitivos() {
        return movimientosRepetitivos;
    }

    public void setMovimientosRepetitivos(boolean movimientosRepetitivos) {
        this.movimientosRepetitivos = movimientosRepetitivos;
    }

    public boolean isRitmoElevado() {
        return ritmoElevado;
    }

    public void setRitmoElevado(boolean ritmoElevado) {
        this.ritmoElevado = ritmoElevado;
    }

    public boolean isReposoInsuficiente() {
        return reposoInsuficiente;
    }

    public void setReposoInsuficiente(boolean reposoInsuficiente) {
        this.reposoInsuficiente = reposoInsuficiente;
    }

    public String getPosturaPredominante() {
        return posturaPredominante;
    }

    public void setPosturaPredominante(String posturaPredominante) {
        this.posturaPredominante = posturaPredominante;
    }

    public String getTiempoSintoma() {
        return tiempoSintoma;
    }

    public void setTiempoSintoma(String tiempoSintoma) {
        this.tiempoSintoma = tiempoSintoma;
    }

    public boolean isRecibioTratamiento() {
        return recibioTratamiento;
    }

    public void setRecibioTratamiento(boolean recibioTratamiento) {
        this.recibioTratamiento = recibioTratamiento;
    }

    public boolean isRealizaRestricciones() {
        return realizaRestricciones;
    }

    public void setRealizaRestricciones(boolean realizaRestricciones) {
        this.realizaRestricciones = realizaRestricciones;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
    
}
