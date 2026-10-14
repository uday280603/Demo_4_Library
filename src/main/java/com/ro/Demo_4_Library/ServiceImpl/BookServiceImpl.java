package com.ro.Demo_4_Library.ServiceImpl;

import com.ro.Demo_4_Library.Dto.BookRequestDto;
import com.ro.Demo_4_Library.Dto.BookResponseDto;
import com.ro.Demo_4_Library.Entity.Author;
import com.ro.Demo_4_Library.Entity.Book;
import com.ro.Demo_4_Library.Repository.AuthorRepository;
import com.ro.Demo_4_Library.Repository.BookRepository;
import com.ro.Demo_4_Library.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl  implements BookService {


    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public BookResponseDto createBook(BookRequestDto bookRequestDto) {

        Author author = authorRepository.findById(bookRequestDto.getAuthorId()).orElseThrow(()-> new RuntimeException("Author not found..!"));

        Book newBook =  new Book();
        newBook.setBookName(bookRequestDto.getBookName());
        newBook.setBookPrice(bookRequestDto.getBookPrice());
        newBook.setBookPageCount(bookRequestDto.getBookPageCount());
        newBook.setAuthor(author);

        Book savedBook = bookRepository.save(newBook);

        return   new BookResponseDto(
                savedBook.getBookId(),
                savedBook.getBookName(),
                savedBook.getBookPrice(),
                savedBook.getBookPageCount(),
                savedBook.getAuthor().getAuthorId()
        );

    }
    @Override
    public List<BookResponseDto> getAllBooks() {
        List<Book> getAll = bookRepository.findAll();

        return getAll.stream()
                .map(book -> new BookResponseDto(
                        book.getBookId(),
                        book.getBookName(),
                        book.getBookPrice(),
                        book.getBookPageCount(),
                        book.getAuthor() != null
                                ? book.getAuthor().getAuthorId()
                                : null
                ))
                .toList();
    }

    @Override
    public BookResponseDto getBookById(Long bookId) {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Book with id "+bookId+" is not found...!"));
        return  new BookResponseDto(
                book.getBookId(),
                book.getBookName(),
                book.getBookPrice(),
                book.getBookPageCount(),
                book.getAuthor().getAuthorId()
        );
    }

    @Override
    public BookResponseDto updateBookDetails(Long bookId, BookRequestDto bookRequestDto) {
        Book book = bookRepository.findById(bookId).orElseThrow(()-> new RuntimeException("Book not found..!"));

        Author author = authorRepository.findById(bookRequestDto.getAuthorId()).orElseThrow(()-> new RuntimeException("Author not found..!"));


        book.setBookName(bookRequestDto.getBookName());
        book.setBookPrice(bookRequestDto.getBookPrice());
        book.setBookPageCount(bookRequestDto.getBookPageCount());
        book.setAuthor(author);

        Book updateBook = bookRepository.save(book);

        return  new BookResponseDto(
                updateBook.getBookId(),
                updateBook.getBookName(),
                updateBook.getBookPrice(),
                updateBook.getBookPageCount(),
                updateBook.getAuthor().getAuthorId()
        );
    }

    @Override
    public void deleteBook(Long bookId) {
        Book book = bookRepository.findById(bookId).orElseThrow(()-> new RuntimeException("Book not found..!"));
        bookRepository.delete(book);


    }
}
