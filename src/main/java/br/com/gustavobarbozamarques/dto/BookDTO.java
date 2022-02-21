package br.com.gustavobarbozamarques.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO implements Serializable {
    private Integer id;

    @NotBlank(message = "Title cannot be blank.")
    private String title;

    @NotBlank(message = "Author cannot be blank.")
    private String author;
}
