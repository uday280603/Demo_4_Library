package com.ro.Demo_4_Library.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;

    @NotBlank(message = "Enter book name")
    private String bookName;

    private Double bookPrice;

    private int bookPageCount;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;
}