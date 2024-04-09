package com.tienda.clientes;

public class Corportativo extends Cliente implements Descuento {

   private String tipoEmpresa;//electronicos, distribuidor, monotributista,responsable inscripto HACER ENUM
    private String numTel;
    private String mail;
    private String address;


    public Corportativo(String nombre, String dni, String tipoEmpresa, String numTel, String mail, String address) {
        super(nombre, dni);
        this.tipoEmpresa=tipoEmpresa;
        this.numTel=numTel;
        this.mail=mail;
        this.address=address;


    }
    public String getTipoEmpresa() {
        return tipoEmpresa;
    }

    public void setTipoEmpresa(String tipoEmpresa) {
        this.tipoEmpresa = tipoEmpresa;
    }

    public String getNumTel() {
        return numTel;
    }

    public void setNumTel(String numTel) {
        this.numTel = numTel;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString(){return String.format("%s - %s - %s - %s",this.tipoEmpresa,this.address,this.mail,this.numTel);}

    @Override
    public float procentaje() {
        return 0.5f;
    }
}
