package br.com.gustavobarbozamarques.controllers;

import br.com.gustavobarbozamarques.dto.BookDTO;
import br.com.gustavobarbozamarques.services.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookController.class)
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testSaveShouldReturnSuccess() throws Exception {
        var request = BookDTO.builder()
                .id(1)
                .author("Gustavo Barboza Marques")
                .title("Java Redis")
                .build();

        this.mockMvc
                .perform(
                        post("/books")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(request))
                )
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void testSendEmailShouldReturnBadRequestWhenFieldsMissing() throws Exception {
        var request = BookDTO.builder().build();

        this.mockMvc
                .perform(
                        post("/books")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(request))
                )
                .andDo(print())
                .andExpect(status().isBadRequest());
    }
}
