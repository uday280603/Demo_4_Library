package com.ro.Demo_4_Library.Controller;


import com.ro.Demo_4_Library.Dto.BookRequestDto;
import com.ro.Demo_4_Library.Dto.BookResponseDto;
import com.ro.Demo_4_Library.Response.ApiResponse;
import com.ro.Demo_4_Library.Service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;


    @PostMapping("/add")
    public ResponseEntity<ApiResponse<BookResponseDto>> createBook(@Valid @RequestBody BookRequestDto bookRequestDto) {
        BookResponseDto newBook = bookService.createBook(bookRequestDto);
        ApiResponse<BookResponseDto> response = new ApiResponse<>(
                "New book added successfully..!",
                newBook,
                true
        );


        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<BookResponseDto>>> getAllBooks() {
        List<BookResponseDto> getAll = bookService.getAllBooks();
        ApiResponse<List<BookResponseDto>> response = new ApiResponse<>(
                "Fetch all books successfully..!",
                getAll,
                true
        );
        return ResponseEntity.ok(response);
    }


    @GetMapping("/get/{id}")
    public ResponseEntity<ApiResponse<BookResponseDto>> getBookById(@PathVariable Long id) {
        BookResponseDto getBook = bookService.getBookById(id);
        ApiResponse<BookResponseDto> response = new ApiResponse<>(
                "Book Found",
                getBook,
                true
        );
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<BookResponseDto>> updatedBook(@PathVariable Long id , @Valid @RequestBody BookRequestDto bookRequestDto){

        BookResponseDto updatedBook = bookService.updateBookDetails(id, bookRequestDto);
        ApiResponse<BookResponseDto> response = new ApiResponse<>(
                "Book Updated Successfully..",
                updatedBook,
                true
        );
        return  ResponseEntity.ok(response);

    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<String>> deleteBookById(@PathVariable  Long id){

         bookService.deleteBook(id);
         ApiResponse<String> response =  new ApiResponse<>(
                 "Book deleted successfully..!",
                 "Book with id : "+id+" is removes successfully..!",
                 true
         );

         return  ResponseEntity.ok(response);

    }
}
