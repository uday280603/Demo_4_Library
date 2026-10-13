package com.ro.Demo_4_Library.Controller;


import com.ro.Demo_4_Library.Dto.AuthorRequestDto;
import com.ro.Demo_4_Library.Dto.AuthorResponseDto;
import com.ro.Demo_4_Library.Response.ApiResponse;
import com.ro.Demo_4_Library.Service.AuthorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    private AuthorService authorService;


    @PostMapping("/add")
    public ResponseEntity<ApiResponse<AuthorResponseDto>> createAuthor( @Valid @RequestBody AuthorRequestDto authorRequestDto){
        AuthorResponseDto author =  authorService.createAuthor(authorRequestDto);
        ApiResponse<AuthorResponseDto> response =  new ApiResponse<>(
                "New author added successfully...!",
                author,
                true
        );

        return  ResponseEntity.status(HttpStatus.CREATED).body(response);

    }


    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<AuthorResponseDto>>>getAllAuthors(){
        List<AuthorResponseDto> getAll = authorService.getAllAuthors();
        ApiResponse<List<AuthorResponseDto>> response = new ApiResponse<>(
                "All authors fetch successfully..!",
                getAll,
                true

        );
        return ResponseEntity.ok(response);
    }


    @GetMapping("/get/{id}")
    public ResponseEntity<ApiResponse<AuthorResponseDto>> getAuthorById(@PathVariable Long id){
        AuthorResponseDto getAuthor = authorService.getAuthorById(id);
        ApiResponse<AuthorResponseDto> response = new ApiResponse<>(
                "Author fetch successfully..!",
                getAuthor,
                true
        );
        return  ResponseEntity.ok(response);

    }

    @PutMapping("/update/{id}")
    public  ResponseEntity<ApiResponse<AuthorResponseDto>> updateAuthor(@PathVariable Long id , @Valid @RequestBody AuthorRequestDto authorRequestDto){
        AuthorResponseDto updatedAuthor = authorService.updateResponse(id,authorRequestDto);
        ApiResponse<AuthorResponseDto> response = new ApiResponse<>(
                "Author updated successfully..!",
                updatedAuthor,
                true
        );
        return ResponseEntity.ok(response);

    }

    @DeleteMapping("/delete/{id}")
    public  ResponseEntity<ApiResponse<String>> deleteAuthor(@PathVariable Long id){
        authorService.deleteAuthor(id);
        ApiResponse<String> response = new ApiResponse<>(
                "Author deleted successfully..!",
                "Author removed successfully...",true
                );
        return  ResponseEntity.ok(response);

    }


    @GetMapping("/pagination")
    public ResponseEntity<ApiResponse<Page<AuthorResponseDto>>>getAuthorsInChunks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size){
        Page<AuthorResponseDto> getAuthors = authorService.getAuthorsInChunk(page, size);
        ApiResponse<Page<AuthorResponseDto>> response = new ApiResponse<>(
                "Authors fetch successfully..!",
                getAuthors,
                true
        );
       return   ResponseEntity.ok(response);


    }
}
