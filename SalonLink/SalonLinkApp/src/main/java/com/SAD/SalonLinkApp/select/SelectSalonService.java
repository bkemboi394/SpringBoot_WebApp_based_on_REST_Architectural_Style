package com.SAD.SalonLinkApp.select;

import com.SAD.SalonLinkApp.UserSession;
import com.SAD.SalonLinkApp.repo.Salon;
import com.SAD.SalonLinkApp.repo.SalonRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SelectSalonService {
    private final SalonRepository salonRepository;



    public List<String> selectSalon(SelectSalonRequest request) {
        String selectedSalonName = request.getSalonName();
        UserSession.setSelectedSalon(selectedSalonName);

        Long loggedInCustomerId = UserSession.getLoggedInCustomerId();
        if (loggedInCustomerId == null) {
            throw new NullPointerException("Please log in first");
        }

        Optional<Salon> selectedSalonOptional = salonRepository.findByName(selectedSalonName);
        if (selectedSalonOptional.isPresent()) {
            Salon selectedSalon = selectedSalonOptional.get();
            String servicesOffered = selectedSalon.getServices();

            // Splits the services offered by commas to get a list of services
            List<String> services = Arrays.asList(servicesOffered.split(",\\s*"));
            return services;
        }

        return Collections.emptyList();
    }

}
