package com.SAD.SalonLinkApp.repo;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerUserService implements UserDetailsService {


    private final CustomerUserRepository customerUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        return customerUserRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                String.format("user with email %s not found", email)));
    }

    public String signUpUser(CustomerAppUser customerAppUser) {
       if(customerUserRepository
                .findByEmail(customerAppUser.getEmail())
                .isPresent())

         {

            throw new IllegalStateException("email already taken");
        }

        String encodedPassword = bCryptPasswordEncoder
                .encode(customerAppUser.getPassword());

        customerAppUser.setPassword(encodedPassword);

        customerUserRepository.save(customerAppUser);

        return "Sign up successful";}





    }
