package com.ro.Demo_4_Library.Service;

import com.ro.Demo_4_Library.Dto.AuthorRequestDto;
import com.ro.Demo_4_Library.Dto.AuthorResponseDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface AuthorService {

    AuthorResponseDto createAuthor(AuthorRequestDto authorRequestDto);
    List<AuthorResponseDto> getAllAuthors();
    AuthorResponseDto getAuthorById(Long authorId);
    AuthorResponseDto updateResponse(Long authorId, AuthorRequestDto authorRequestDto);
    void deleteAuthor(Long authorId);
    Page<AuthorResponseDto> getAuthorsInChunk(int page, int size);

}
