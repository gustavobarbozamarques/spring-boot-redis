package br.com.gustavobarbozamarques.controllers;

import br.com.gustavobarbozamarques.dto.BookDTO;
import br.com.gustavobarbozamarques.services.BookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "Redis")
@RestController
@RequestMapping(path = "/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    @ApiOperation("All books")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success")
    })
    public List<BookDTO> getAll() {
        return bookService.getAll();
    }

    @GetMapping("/{id}")
    @ApiOperation("Get book by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success")
    })
    public BookDTO getById(@PathVariable("id") Integer id) {
        return bookService.getById(id);
    }

    @PostMapping
    @ApiOperation("Save new book")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success")
    })
    public void save(@RequestBody @Valid BookDTO bookDTO) {
        bookService.save(bookDTO);
    }

    @PutMapping
    @ApiOperation("Update book")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success")
    })
    public void update(@RequestBody @Valid BookDTO bookDTO) {
        bookService.update(bookDTO);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Delete book")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success")
    })
    public void delete(@PathVariable("id") Integer id) {
        bookService.delete(id);
    }
}
