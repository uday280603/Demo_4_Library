package com.ro.Demo_4_Library.Dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AuthorRequestDto {


    @NotBlank(message = "Enter Author Name....!")
    @Size(min = 2, max = 20 , message = "Name should be more than 2 and less than 20")
    private String authorName;

    @NotBlank(message = "Enter Author mobile number....!")
    @Size(min = 10, max = 10 , message = "mobile should 10 digit")
    private String mobileNo;

    @NotBlank(message = "Enter Author email!")
    private String authorEmail;

    @NotBlank(message = "Enter Password")
    @Size(min = 8, max = 16 , message = "password should be more than 8 and less than 16")
    private String authorPassword;

    @NotBlank(message = "Enter Author city")
    private String authorCity;


    private int authorAge;
}
