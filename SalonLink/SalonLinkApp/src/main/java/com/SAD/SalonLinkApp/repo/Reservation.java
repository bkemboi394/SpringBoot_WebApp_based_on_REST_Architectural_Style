package com.SAD.SalonLinkApp.repo;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class Reservation {

    @Id
    private int reservationId;
    private String client;
    private String salonName;

    private String selectedService;

    public Reservation() {
    }

    public Reservation(int reservationId, String client, String salonist, String selectedService) {
        this.reservationId = reservationId;
        this.client = client;
        this.salonName = salonist;
        this.selectedService = selectedService;


    }

    public int getReservationId() {
        return reservationId;
    }


    public String getClient() {
        return client;
    }

    public String getSalonName() {
        return salonName;
    }

    public String getSelectedService() {
        return selectedService;
    }
}
