package br.com.gustavobarbozamarques.services;

import br.com.gustavobarbozamarques.dto.BookDTO;
import br.com.gustavobarbozamarques.entity.Book;
import br.com.gustavobarbozamarques.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    @Cacheable(value = "books", key = "'all'")
    public List<BookDTO> getAll() {
        log.info("Executing method getAll()");
        return bookRepository.findAll()
                .stream()
                .map(book -> BookDTO.builder()
                        .id(book.getId())
                        .author(book.getAuthor())
                        .title(book.getTitle())
                        .build()
                ).collect(Collectors.toList());
    }

    @Cacheable(value = "books", key = "#id")
    public BookDTO getById(Integer id) {
        log.info("Executing method getById({})", id);
        var book = bookRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found."));

        return BookDTO.builder()
                .id(book.getId())
                .author(book.getAuthor())
                .title(book.getTitle())
                .build();
    }

    @CacheEvict(value = "books", key = "'all'")
    public void save(BookDTO bookDTO) {
        log.info("Executing method save({})", bookDTO);
        var book = Book.builder()
                .author(bookDTO.getAuthor())
                .title(bookDTO.getTitle())
                .savedAt(new Date())
                .build();
        bookRepository.save(book);
    }

    @Caching(evict = {
            @CacheEvict(value = "books", key = "'all'"),
            @CacheEvict(value = "books", key = "#bookDTO.id")})
    public void update(BookDTO bookDTO) {
        log.info("Executing method update({})", bookDTO);
        var book = bookRepository.findById(bookDTO.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found."));
        book.setTitle(bookDTO.getTitle());
        book.setAuthor(bookDTO.getAuthor());
        book.setSavedAt(new Date());
        bookRepository.save(book);
    }

    @Caching(evict = {
            @CacheEvict(value = "books", key = "'all'"),
            @CacheEvict(value = "books", key = "#id")})
    public void delete(Integer id) {
        log.info("Executing method delete({})", id);
        bookRepository.deleteById(id);
    }
}
