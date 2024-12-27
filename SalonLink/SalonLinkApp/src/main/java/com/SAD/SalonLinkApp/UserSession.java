package com.SAD.SalonLinkApp;

// Singleton class to hold loggedInCustomerId
public class UserSession {
    private static Long loggedInCustomerId;
    private static String selectedSalon;

    public static Long getLoggedInCustomerId() {
        return loggedInCustomerId;
    }

    public static void setLoggedInCustomerId(Long id) {
        loggedInCustomerId = id;
    }

    public static String getSelectedSalon() {
        return selectedSalon;
    }

    public static void setSelectedSalon(String selectedSalon) {
        UserSession.selectedSalon = selectedSalon;
    }
}
