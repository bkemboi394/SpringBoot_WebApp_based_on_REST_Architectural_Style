package com.SAD.SalonLinkApp.reservation;

import com.SAD.SalonLinkApp.UserSession;
import com.SAD.SalonLinkApp.repo.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class ReservationService {

    private final SalonRepository salonRepository;
    private final ReservationRepository reservationRepository;
    private final CustomerUserRepository customerUserRepository;


    public int generateUniqueNumber() {
        Random random = new Random();
        return random.nextInt(900000000) + 100000000;
    }

    public String createReservation(ReservationRequest request) {
        List<String> selectedServices = request.getSelectedServices(); // Update to handle a list of services
        Long loggedInCustomerId = UserSession.getLoggedInCustomerId();
        if (loggedInCustomerId == null) {
            throw new NullPointerException("Please log in first");
        }

        String selectedSalonName = UserSession.getSelectedSalon();
        if (selectedSalonName == null) {
            throw new NullPointerException("Please select a salon first");
        }

        Optional<CustomerAppUser> customerAppUserObject = customerUserRepository.findById(loggedInCustomerId);
        CustomerAppUser loggedInCustomer = customerAppUserObject.get();
        String loggedInCustomerEmail = loggedInCustomer.getEmail();

        Optional<Salon> selectedSalonObject = salonRepository.findByName(selectedSalonName);
        if (selectedSalonObject.isEmpty()) {
            return "Selected salon not found.";
        }

        Salon selectedSalon = selectedSalonObject.get();
        String salonServices = selectedSalon.getServices();
        List<String> listOfSalonServices = List.of(salonServices.split(",\\s*"));

        // Check if all selected services are available
        for (String selectedService : selectedServices) {
            if (!listOfSalonServices.contains(selectedService)) {
                return "Service '" + selectedService + "' is not available for this salon.";
            }
        }

        // Create reservation for each service
        StringBuilder reservationSummary = new StringBuilder("You have successfully made a reservation for: ");
        for (String selectedService : selectedServices) {
            int reservationId = generateUniqueNumber(); // Generate a unique order ID
            Reservation reservation = new Reservation(reservationId, loggedInCustomerEmail, selectedSalonName, selectedService);
            reservationRepository.save(reservation);

            reservationSummary.append(selectedService)
                    .append(" at ")
                    .append(selectedSalonName)
                    .append(". Reservation ID: ")
                    .append(reservationId)
                    .append("\n");
        }

        return reservationSummary.toString();
    }










}
