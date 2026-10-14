package com.ro.Demo_4_Library.Service;

import com.ro.Demo_4_Library.Dto.BookRequestDto;
import com.ro.Demo_4_Library.Dto.BookResponseDto;

import java.util.List;

public interface BookService {

    BookResponseDto createBook(BookRequestDto bookRequestDto);
    List<BookResponseDto> getAllBooks();
    BookResponseDto getBookById(Long bookId);
    BookResponseDto updateBookDetails(Long bookId, BookRequestDto bookRequestDto);
    void deleteBook(Long bookId);
}
