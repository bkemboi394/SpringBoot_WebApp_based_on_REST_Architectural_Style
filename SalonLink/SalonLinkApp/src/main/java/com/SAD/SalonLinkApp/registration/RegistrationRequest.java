package com.SAD.SalonLinkApp.registration;

import lombok.*;

@Getter
@ToString
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationRequest {
    private String name;
    private String address;
    private String email;
    private String password;


}