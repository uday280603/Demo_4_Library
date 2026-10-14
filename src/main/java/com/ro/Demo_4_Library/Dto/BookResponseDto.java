package com.ro.Demo_4_Library.Dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class BookResponseDto {

    private  Long bookId;
    private String bookName;
    private Double bookPrice;
    private int bookPageCount;
    private Long authorId;

}