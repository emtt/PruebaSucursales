package com.emt_sucursales.brcoredata.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Sucursales {

    @SerializedName("ID")
    @Expose
    private String iD;
    @SerializedName("tipo")
    @Expose
    private String tipo;
    @SerializedName("NOMBRE")
    @Expose
    private String nOMBRE;
    @SerializedName("DOMICILIO")
    @Expose
    private String dOMICILIO;
    @SerializedName("HORARIO")
    @Expose
    private String hORARIO;
    @SerializedName("TELEFONO_PORTAL")
    @Expose
    private String tELEFONOPORTAL;
    @SerializedName("TELEFONO_APP")
    @Expose
    private String tELEFONOAPP;
    @SerializedName("24_HORAS")
    @Expose
    private String _24HORAS;
    @SerializedName("SABADOS")
    @Expose
    private String sABADOS;
    @SerializedName("CIUDAD")
    @Expose
    private String cIUDAD;
    @SerializedName("ESTADO")
    @Expose
    private String eSTADO;
    @SerializedName("Latitud")
    @Expose
    private String latitud;
    @SerializedName("Longitud")
    @Expose
    private String longitud;
    @SerializedName("Correo_Gerente")
    @Expose
    private String correoGerente;
    @SerializedName("URL_FOTO")
    @Expose
    private String uRLFOTO;
    @SerializedName("Suc_Estado_Prioridad")
    @Expose
    private String sucEstadoPrioridad;
    @SerializedName("Suc_Ciudad_Prioridad")
    @Expose
    private String sucCiudadPrioridad;

    public String getID() {
        return iD;
    }

    public void setID(String iD) {
        this.iD = iD;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNOMBRE() {
        return nOMBRE;
    }

    public void setNOMBRE(String nOMBRE) {
        this.nOMBRE = nOMBRE;
    }

    public String getDOMICILIO() {
        return dOMICILIO;
    }

    public void setDOMICILIO(String dOMICILIO) {
        this.dOMICILIO = dOMICILIO;
    }

    public String getHORARIO() {
        return hORARIO;
    }

    public void setHORARIO(String hORARIO) {
        this.hORARIO = hORARIO;
    }

    public String getTELEFONOPORTAL() {
        return tELEFONOPORTAL;
    }

    public void setTELEFONOPORTAL(String tELEFONOPORTAL) {
        this.tELEFONOPORTAL = tELEFONOPORTAL;
    }

    public String getTELEFONOAPP() {
        return tELEFONOAPP;
    }

    public void setTELEFONOAPP(String tELEFONOAPP) {
        this.tELEFONOAPP = tELEFONOAPP;
    }

    public String get24HORAS() {
        return _24HORAS;
    }

    public void set24HORAS(String _24HORAS) {
        this._24HORAS = _24HORAS;
    }

    public String getSABADOS() {
        return sABADOS;
    }

    public void setSABADOS(String sABADOS) {
        this.sABADOS = sABADOS;
    }

    public String getCIUDAD() {
        return cIUDAD;
    }

    public void setCIUDAD(String cIUDAD) {
        this.cIUDAD = cIUDAD;
    }

    public String getESTADO() {
        return eSTADO;
    }

    public void setESTADO(String eSTADO) {
        this.eSTADO = eSTADO;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public String getCorreoGerente() {
        return correoGerente;
    }

    public void setCorreoGerente(String correoGerente) {
        this.correoGerente = correoGerente;
    }

    public String getURLFOTO() {
        return uRLFOTO;
    }

    public void setURLFOTO(String uRLFOTO) {
        this.uRLFOTO = uRLFOTO;
    }

    public String getSucEstadoPrioridad() {
        return sucEstadoPrioridad;
    }

    public void setSucEstadoPrioridad(String sucEstadoPrioridad) {
        this.sucEstadoPrioridad = sucEstadoPrioridad;
    }

    public String getSucCiudadPrioridad() {
        return sucCiudadPrioridad;
    }

    public void setSucCiudadPrioridad(String sucCiudadPrioridad) {
        this.sucCiudadPrioridad = sucCiudadPrioridad;
    }

    @Override
    public String toString() {
        return "Sucursales{" +
                "iD='" + iD + '\'' +
                ", tipo='" + tipo + '\'' +
                ", nOMBRE='" + nOMBRE + '\'' +
                ", dOMICILIO='" + dOMICILIO + '\'' +
                ", hORARIO='" + hORARIO + '\'' +
                ", tELEFONOPORTAL='" + tELEFONOPORTAL + '\'' +
                ", tELEFONOAPP='" + tELEFONOAPP + '\'' +
                ", _24HORAS='" + _24HORAS + '\'' +
                ", sABADOS='" + sABADOS + '\'' +
                ", cIUDAD='" + cIUDAD + '\'' +
                ", eSTADO='" + eSTADO + '\'' +
                ", latitud='" + latitud + '\'' +
                ", longitud='" + longitud + '\'' +
                ", correoGerente='" + correoGerente + '\'' +
                ", uRLFOTO='" + uRLFOTO + '\'' +
                ", sucEstadoPrioridad='" + sucEstadoPrioridad + '\'' +
                ", sucCiudadPrioridad='" + sucCiudadPrioridad + '\'' +
                '}';
    }
}
