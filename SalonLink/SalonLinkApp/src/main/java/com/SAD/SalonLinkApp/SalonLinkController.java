package com.SAD.SalonLinkApp;

import com.SAD.SalonLinkApp.repo.CustomerAppUser;
import com.SAD.SalonLinkApp.repo.Salon;
import com.SAD.SalonLinkApp.login.LoginRequest;
import com.SAD.SalonLinkApp.login.LoginService;
import com.SAD.SalonLinkApp.registration.RegistrationRequest;
import com.SAD.SalonLinkApp.registration.SignupService;
import com.SAD.SalonLinkApp.reservation.ReservationRequest;
import com.SAD.SalonLinkApp.reservation.ReservationService;
import com.SAD.SalonLinkApp.search.SearchSalonRequest;
import com.SAD.SalonLinkApp.search.SearchSalonService;
import com.SAD.SalonLinkApp.select.SelectSalonRequest;
import com.SAD.SalonLinkApp.select.SelectSalonService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.io.UnsupportedEncodingException;
import java.util.LinkedHashMap;
import java.util.List;

@Controller
@RequestMapping(path = "api/SalonLink")
@AllArgsConstructor
public class SalonLinkController {

    private final SignupService signupService;
    private final LoginService loginService;
    private final SearchSalonService searchSalonService;
    private final SelectSalonService selectSalonService;
    private final ReservationService reservationService;


    @GetMapping("/registration")
    public String showRegistrationForm(Model model) {
        model.addAttribute("registrationRequest", new RegistrationRequest());
        return "registration";
    }


    @PostMapping("/registration")
    public String register(@ModelAttribute("registrationRequest") RegistrationRequest request, Model model, RedirectAttributes redirectAttributes) {
        //signupService.registerCustomer(request);
        try {
            String reservationMessage = signupService.registerCustomer(request);
            redirectAttributes.addFlashAttribute("message", "You have been successfully registered,welcome to SalonLink! Please login below");
            return "redirect:/api/SalonLink/login"; // Redirect to login page
        } catch (IllegalStateException e) {
            // If registration fails, add error message to model and return to the registration page
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/api/SalonLink/registration"; // Redirect back to registration page with error
        }
    }


    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("loginRequest", new LoginRequest());
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("loginRequest") LoginRequest request, Model model) {
        try {
            CustomerAppUser result = loginService.loginCustomer(request);
            return "redirect:/api/SalonLink/dashboard";
        } catch (IllegalStateException | BadCredentialsException e) {
            // If login fails, add error message to model and return to the login page
            model.addAttribute("error", e.getMessage());
            return "login";
        }
    }


    @GetMapping("/dashboard")
    public String showDashboard(Model model) {
        List<Salon> allSalons = searchSalonService.getAllSalons();
        model.addAttribute("salons", allSalons);
        return "dashboard";
    }

    @GetMapping("/searchforservice")
    public String showSearchForm(Model model) {
        model.addAttribute("searchRequest", new SearchSalonRequest());
        return "searchforservice";
    }

    @PostMapping("/searchforservice")
    public String searchForService(@ModelAttribute("searchRequest") SearchSalonRequest request, Model model) throws UnsupportedEncodingException {
        Object matchingSalons = searchSalonService.searchSalon(request);
        model.addAttribute("sortedSalonDistances", matchingSalons);
        return "searchforservice";
    }

    @GetMapping("/selectsalon")
    public String showSelectSalonForm(@RequestParam("salonName") String salonName, Model model) {
        SelectSalonRequest request = new SelectSalonRequest();
        request.setSalonName(salonName);
        List<String> services = selectSalonService.selectSalon(request);
        model.addAttribute("services", services);
        return "selectsalon";
    }

    @PostMapping("/selectsalon")
    public String selectSalon(@ModelAttribute("selectSalonRequest") SelectSalonRequest request, Model model) {
        List<String> services = selectSalonService.selectSalon(request);
        model.addAttribute("services", services);
        return "selectsalon";
    }

    @GetMapping("/makereservation")
    public String showMakeReservationForm(Model model) {
        model.addAttribute("reservationRequest", new ReservationRequest());
        return "makereservation";
    }

    @PostMapping("/makereservation")
    public String makeReservation(@ModelAttribute("reservationRequest") ReservationRequest request, Model model) {
        String reservationMessage = reservationService.createReservation(request);
        model.addAttribute("selectedservice", reservationMessage);
        return "makereservation";
    }
}


