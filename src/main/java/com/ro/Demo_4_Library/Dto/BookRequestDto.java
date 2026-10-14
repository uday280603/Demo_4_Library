package com.ro.Demo_4_Library.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookRequestDto {

    @NotBlank(message = "Enter book name")
    private String bookName;

    @NotNull(message = "Enter book price")
    @Positive(message = "Book price must be greater than zero")
    private Double bookPrice;

    @Positive(message = "Book page count must be greater than zero")
    private int bookPageCount;

    @NotNull(message = "Enter author ID")
    @Positive(message = "Author ID must be greater than zero")
    private Long authorId;
}