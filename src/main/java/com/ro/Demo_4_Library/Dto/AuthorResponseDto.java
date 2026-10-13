package com.ro.Demo_4_Library.Dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.stream.Stream;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AuthorResponseDto {

    private Long authorId;
    private String authorName;
    private String mobileNo;
    private String authorEmail;
    private String authorCity;
    private int authorAge;


}
