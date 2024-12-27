package com.SAD.SalonLinkApp.search;

import com.SAD.SalonLinkApp.UserSession;
import com.SAD.SalonLinkApp.repo.CustomerAppUser;
import com.SAD.SalonLinkApp.repo.CustomerUserRepository;
import com.SAD.SalonLinkApp.repo.Salon;
import com.SAD.SalonLinkApp.repo.SalonRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.*;

@AllArgsConstructor
@Service
public class SearchSalonService {
    private final CustomerUserRepository customerUserRepository;
    private final SalonRepository salonRepository;
    private final GoogleMapsApiService googleMapsApiService;





    public List<Salon> getAllSalons() {
        return salonRepository.findAll();
    }

    public Object searchSalon(SearchSalonRequest request) throws UnsupportedEncodingException {
        String serviceSelection = request.getService().toLowerCase();
        List<Salon> allSalons = salonRepository.findAll();
        List<Salon> salonMatches = new ArrayList<>();
        for (Salon salon : allSalons) {
            //
            if (salon.getServices().contains(serviceSelection)) {
                salonMatches.add(salon);

            }

        }
        Long loggedInCustomerId = UserSession.getLoggedInCustomerId();
        if (loggedInCustomerId == null) {
            throw new NullPointerException("Please log in first");
        }

        Optional<CustomerAppUser> loggedInCustomerOptional = customerUserRepository.findById(loggedInCustomerId);
        CustomerAppUser loggedInCustomer = loggedInCustomerOptional.get();
        String loggedInCustomerAddress = loggedInCustomer.getAddress();

        String apiKey = "AIzaSyBh0IBov_m62GNc7QJYkvZZU-LtfGaGR0M";

        HashMap<String, Double> salonDistances = new HashMap<>();
        for (Salon salon : salonMatches) {
            String salonAddress = salon.getAddress();
            String stringDistance = googleMapsApiService.calculateDistanceBetweenTwoAddresses(apiKey, loggedInCustomerAddress, salonAddress);
            String numericDistance = stringDistance.replaceAll("[^\\d.]", "");
            double distance = Double.parseDouble(numericDistance);
            String salonName = salon.getName();
            salonDistances.put(salonName, distance);
        }

        // Sorting the HashMap by distance
        List<Map.Entry<String, Double>> sortedEntries = new ArrayList<>(salonDistances.entrySet());
        sortedEntries.sort(Map.Entry.comparingByValue());

        // Using Linked HashMap to preserve the order of sorted entries
        LinkedHashMap<String, String> sortedSalonDistances = new LinkedHashMap<>();
        for (Map.Entry<String, Double> entry : sortedEntries) {
            String distanceInMiles = String.valueOf(entry.getValue()) + " miles";
            sortedSalonDistances.put(entry.getKey(), distanceInMiles);
        }

        return sortedSalonDistances;


    }

}







