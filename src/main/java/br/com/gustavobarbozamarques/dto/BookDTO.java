package br.com.gustavobarbozamarques.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {
    private Integer id;

    @NotBlank(message = "Title cannot be blank.")
    private String title;

    @NotBlank(message = "Author cannot be blank.")
    private String author;
}
