package com.SAD.SalonLinkApp.registration;

import com.SAD.SalonLinkApp.repo.AppUserRole;
import com.SAD.SalonLinkApp.repo.CustomerAppUser;
import com.SAD.SalonLinkApp.repo.CustomerUserRepository;
import com.SAD.SalonLinkApp.repo.CustomerUserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class SignupService {
//    private final CustomerUserRepository customerUserRepository;
//    private final PasswordEncoder passwordEncoder;
    private final CustomerUserService customerUserService;


    public String registerCustomer(RegistrationRequest request){

            CustomerAppUser customerAppUser = new CustomerAppUser(
                request.getName(),
                request.getAddress(),
                request.getEmail(),
                request.getPassword(),
                AppUserRole.USER);

            customerUserService.signUpUser(customerAppUser);


            return "Registered Successfully";

        }



}
