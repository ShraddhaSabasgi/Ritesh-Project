package com.example.blogging.payloads_dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {

    private int id;

    @NotEmpty
    @Size(min = 3, message = "Name should be greter than 3 character")
    private String name;

    @Email(message = "Email are not valid please check")
    private String email;

    @NotEmpty
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$",message = "Min 1 uppercase letter.\n" +
            "Min 1 lowercase letter.\n" +
            "Min 1 special character.\n" +
            "Min 1 number.\n" +
            "Min 8 characters.")
    private String password;

    @NotEmpty
    @Size(min = 3,message = "Please enter grater than 3")
    private String about;
}
