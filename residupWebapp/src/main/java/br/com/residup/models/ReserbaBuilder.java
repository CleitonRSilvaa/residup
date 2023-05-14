package br.com.residup.models;

public class ReserbaBuilder {

    private int idReserva;
    private int idMorador;
    private int idArea;
    private String dateReserva;
    private String nomeArea;
    private String horaReserva;

    public ReserbaBuilder idReserva(int idReserva){
        this.idReserva = idReserva;
        return this;
    }
    public ReserbaBuilder idMorador(int idMorador){
        this.idMorador = idMorador;
        return  this;
    }
    public ReserbaBuilder idArea(int idArea){
        this.idArea = idArea;
        return  this;
    }

    public ReserbaBuilder nomeArea(String nomeArea){
        this.nomeArea = nomeArea;
        return  this;
    }
    public ReserbaBuilder horaReserva(String horaReserva){
        this.horaReserva = horaReserva;
        return  this;
    }
    public ReserbaBuilder dateReserva(String dateReserva){
        this.dateReserva = dateReserva;
        return  this;
    }


    public Reserva build(){
        return new Reserva(idReserva,idMorador,idArea,dateReserva,nomeArea,horaReserva);
    }


}
