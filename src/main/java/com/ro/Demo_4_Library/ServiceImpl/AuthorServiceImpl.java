package com.ro.Demo_4_Library.ServiceImpl;

import com.ro.Demo_4_Library.Dto.AuthorRequestDto;
import com.ro.Demo_4_Library.Dto.AuthorResponseDto;
import com.ro.Demo_4_Library.Entity.Author;
import com.ro.Demo_4_Library.Repository.AuthorRepository;
import com.ro.Demo_4_Library.Service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {


    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public AuthorResponseDto createAuthor(AuthorRequestDto authorRequestDto) {
        Author newAuthor =  new Author();
        newAuthor.setAuthorName(authorRequestDto.getAuthorName());
        newAuthor.setAuthorAge(authorRequestDto.getAuthorAge());
        newAuthor.setAuthorEmail(authorRequestDto.getAuthorEmail());
        newAuthor.setAuthorPassword(authorRequestDto.getAuthorPassword());
        newAuthor.setMobileNo(authorRequestDto.getMobileNo());
        newAuthor.setAuthorCity(authorRequestDto.getAuthorCity());

        Author savedAuthor = authorRepository.save(newAuthor);

        return  new AuthorResponseDto(
                savedAuthor.getAuthorId(),
                savedAuthor.getAuthorName(),
                savedAuthor.getMobileNo(),
                savedAuthor.getAuthorEmail(),
                savedAuthor.getAuthorCity(),
                savedAuthor.getAuthorAge()




        );
    }

    @Override
    public List<AuthorResponseDto> getAllAuthors() {

        List<Author> getAll = authorRepository.findAll();

        return getAll.stream()
                .map(author -> new AuthorResponseDto(
                        author.getAuthorId(),
                        author.getAuthorName(),
                        author.getMobileNo(),
                        author.getAuthorEmail(),
                        author.getAuthorCity(),
                        author.getAuthorAge()
                ))
                .toList();
    }

    @Override
    public AuthorResponseDto getAuthorById(Long authorId) {

        Author getAuthor = authorRepository.findById(authorId).orElseThrow(()-> new RuntimeException("No author found with id :" + authorId));
        return new AuthorResponseDto(
                getAuthor.getAuthorId(),
                getAuthor.getAuthorName(),
                getAuthor.getAuthorCity(),
                getAuthor.getAuthorEmail(),
                getAuthor.getMobileNo(),
                getAuthor.getAuthorAge()
        );
    }

    @Override
    public AuthorResponseDto updateResponse(Long authorId, AuthorRequestDto authorRequestDto) {
        Author getAuthor = authorRepository.findById(authorId).orElseThrow(()-> new RuntimeException("Author not found..!"));

        getAuthor.setAuthorName(authorRequestDto.getAuthorName());
        getAuthor.setAuthorEmail(authorRequestDto.getAuthorEmail());
        getAuthor.setMobileNo(authorRequestDto.getMobileNo());
        getAuthor.setAuthorPassword(authorRequestDto.getAuthorPassword());
        getAuthor.setAuthorAge(authorRequestDto.getAuthorAge());
        getAuthor.setAuthorCity(authorRequestDto.getAuthorCity());

        Author updatedAuthor = authorRepository.save(getAuthor);

        return new AuthorResponseDto(
                updatedAuthor.getAuthorId(),
                updatedAuthor.getAuthorName(),
                updatedAuthor.getMobileNo(),
                updatedAuthor.getAuthorEmail(),
                updatedAuthor.getAuthorCity(),
                updatedAuthor.getAuthorAge()
        );
    }

    @Override
    public void deleteAuthor(Long authorId) {
        authorRepository.deleteById(authorId);

    }

    @Override
    public Page<AuthorResponseDto> getAuthorsInChunk(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Author> authors = authorRepository.findAll(pageable);

        return authors.map(author -> new AuthorResponseDto(
                author.getAuthorId(),
                author.getAuthorName(),
                author.getMobileNo(),
                author.getAuthorEmail(),
                author.getAuthorCity(),
                author.getAuthorAge()
        ));
    }
}
