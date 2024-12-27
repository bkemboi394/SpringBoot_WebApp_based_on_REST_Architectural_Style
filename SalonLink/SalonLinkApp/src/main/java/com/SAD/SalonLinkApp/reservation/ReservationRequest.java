package com.SAD.SalonLinkApp.reservation;


import lombok.*;

import java.util.List;

@Getter
@ToString
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReservationRequest {
    private List<String> selectedServices; // For multiple selected services

    // Getters and Setters
    public List<String> getSelectedServices() {
        return selectedServices;
    }

    public void setSelectedServices(List<String> selectedServices) {
        this.selectedServices = selectedServices;
    }
}
