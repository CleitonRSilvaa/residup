package br.com.residup.models;


import java.util.Date;

public class Reserva {

    int id_reserva;
    int id_morador;
    int id_area;

    String date_reserva;
    String area;
    String hora_reserva;

    public int getId_reserva() {
        return id_reserva;
    }

    public void setId_reserva(int id_reserva) {
        this.id_reserva = id_reserva;
    }

    public int getId_morador() {
        return id_morador;
    }

    public void setId_morador(int id_morador) {
        this.id_morador = id_morador;
    }

    public int getId_area() {
        return id_area;
    }

    public void setId_area(int id_area) {
        this.id_area = id_area;
    }

    public String getDate_reserva() {
        return date_reserva;
    }

    public void setDate_reserva(String date_reserva) {
        this.date_reserva = date_reserva;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getHora_reserva() {
        return hora_reserva;
    }

    public void setHora_reserva(String hora_reserva) {
        this.hora_reserva = hora_reserva;
    }
}
