package com.SAD.SalonLinkApp.repo;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

@Service
public class SalonService {

    private final SalonRepository salonRepository;

    public SalonService(SalonRepository salonRepository) {
        this.salonRepository = salonRepository;
    }

    @PostConstruct
    public void init() {
        // Hardcoding salon instances to the salon repository
        salonRepository.save(new Salon("Hair salon-1", "9730 Abrams Rd, Dallas, TX 75243", "braiding,blowdry, styling,haircut"));
        salonRepository.save(new Salon("Beauty salon-1", "9220 Skillman St, Dallas, TX 75243", "manicure, pedicure,waxing,makeup"));
        salonRepository.save(new Salon("Hair salon-2", "6465 E Mockingbird Ln, Dallas, TX 75214", "braiding,blowdry, styling,haircut"));
        salonRepository.save(new Salon("Hair salon-3", "1100 Business Pkwy, Richardson, TX 75081", "braiding,blowdry, styling,haircut"));
        salonRepository.save(new Salon("Beauty salon-2", "8308 Preston Center Plaza, Dallas, TX 75225", "manicure, pedicure,waxing,makeup"));
        salonRepository.save(new Salon("Beauty salon-3", "9100 N Central Expy, Richardson, TX 75231", "manicure, pedicure,waxing,makeup"));
    }
}
