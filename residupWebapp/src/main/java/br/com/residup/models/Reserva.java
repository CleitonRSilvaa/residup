package br.com.residup.models;


public class Reserva {

    private int idReserva;
    private int idMorador;
    private int idArea ;

    private  String dateReserva;
    private String nomeArea;
    private String horaReserva;


    public Reserva(int idReserva, int idMorador, int idArea, String dateReserva, String nomeArea, String horaReserva) {
        this.idReserva = idReserva;
        this.idMorador = idMorador;
        this.idArea = idArea;
        this.dateReserva = dateReserva;
        this.nomeArea = nomeArea;
        this.horaReserva = horaReserva;
    }


    public static ReservaBuilder builder(){
        return new ReservaBuilder();
    }

    public int getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    public int getIdMorador() {
        return idMorador;
    }

    public void setIdMorador(int idMorador) {
        this.idMorador = idMorador;
    }

    public int getIdArea() {
        return idArea;
    }

    public void setIdArea(int idArea) {
        this.idArea = idArea;
    }

    public String getDateReserva() {
        return dateReserva;
    }

    public void setDateReserva(String dateReserva) {
        this.dateReserva = dateReserva;
    }

    public String getNomeArea() {
        return nomeArea;
    }

    public void setNomeArea(String nomeArea) {
        this.nomeArea = nomeArea;
    }

    public String getHoraReserva() {
        return horaReserva;
    }

    public void setHoraReserva(String horaReserva) {
        this.horaReserva = horaReserva;
    }

    @Override
    public String toString() {
        return "Reserva{" +
                "idReserva=" + idReserva +
                ", idMorador=" + idMorador +
                ", idArea=" + idArea +
                ", dateReserva='" + dateReserva + '\'' +
                ", nomeArea='" + nomeArea + '\'' +
                ", horaReserva='" + horaReserva + '\'' +
                '}';
    }
}
