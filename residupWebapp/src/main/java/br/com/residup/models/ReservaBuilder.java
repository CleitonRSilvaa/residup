package br.com.residup.models;

public class ReservaBuilder {

    private int idReserva;
    private int idMorador;
    private int idArea;
    private String dateReserva;
    private String nomeArea;
    private String horaReserva;

    public ReservaBuilder idReserva(int idReserva){
        this.idReserva = idReserva;
        return this;
    }
    public ReservaBuilder idMorador(int idMorador){
        this.idMorador = idMorador;
        return  this;
    }
    public ReservaBuilder idArea(int idArea){
        this.idArea = idArea;
        return  this;
    }

    public ReservaBuilder nomeArea(String nomeArea){
        this.nomeArea = nomeArea;
        return  this;
    }
    public ReservaBuilder horaReserva(String horaReserva){
        this.horaReserva = horaReserva;
        return  this;
    }
    public ReservaBuilder dateReserva(String dateReserva){
        this.dateReserva = dateReserva;
        return  this;
    }


    public Reserva build(){
        return new Reserva(idReserva,idMorador,idArea,dateReserva,nomeArea,horaReserva);
    }


}
