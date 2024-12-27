package com.SAD.SalonLinkApp.login;

import com.SAD.SalonLinkApp.UserSession;
import com.SAD.SalonLinkApp.repo.CustomerAppUser;
import com.SAD.SalonLinkApp.repo.CustomerUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class LoginService {

    private final CustomerUserRepository customerUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    public static Long loggedInCustomerId = null;

    public CustomerAppUser loginCustomer(LoginRequest request) throws IllegalStateException, BadCredentialsException {
        String email = request.getEmail();
        String password = request.getPassword();

        Optional<CustomerAppUser> customerAppUserOptional = customerUserRepository.findByEmail(email);
        if (!customerAppUserOptional.isPresent()) {
            throw new IllegalStateException ("Invalid username or password");
        }

        CustomerAppUser customerAppUser = customerAppUserOptional.get();
        if (!bCryptPasswordEncoder.matches(password, customerAppUser.getPassword())) {
            throw new BadCredentialsException("Invalid username or password");
        }

        loggedInCustomerId = customerAppUser.getId();
        UserSession.setLoggedInCustomerId(loggedInCustomerId);

        return customerAppUser;
    }
}
