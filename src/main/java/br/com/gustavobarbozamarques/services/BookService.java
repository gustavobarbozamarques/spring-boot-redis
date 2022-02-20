package br.com.gustavobarbozamarques.services;

import br.com.gustavobarbozamarques.dto.BookDTO;
import br.com.gustavobarbozamarques.entity.Book;
import br.com.gustavobarbozamarques.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    public List<BookDTO> all() {
        return bookRepository.findAll()
                .stream()
                .map(book -> BookDTO.builder()
                        .id(book.getId())
                        .author(book.getAuthor())
                        .title(book.getTitle())
                        .build()
                ).collect(Collectors.toList());
    }

    public BookDTO getById(Integer id) {
        var book = bookRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found."));

        return BookDTO.builder()
                .id(book.getId())
                .author(book.getAuthor())
                .title(book.getTitle())
                .build();
    }

    public void save(BookDTO bookDTO) {
        var book = Book.builder()
                .author(bookDTO.getAuthor())
                .title(bookDTO.getTitle())
                .savedAt(new Date())
                .build();
        bookRepository.save(book);
    }

    public void update(BookDTO bookDTO) {
        var book = bookRepository.findById(bookDTO.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found."));
        book.setTitle(bookDTO.getTitle());
        book.setAuthor(bookDTO.getAuthor());
        book.setSavedAt(new Date());
        bookRepository.save(book);
    }

    public void delete(Integer id) {
        bookRepository.deleteById(id);
    }
}
