package dev.pedroabreu.restapi.run;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;

public record Run(
        Integer id,
        // Anotação que valida se a string não é nula e não está vazia
        @NotEmpty
        String title,
        LocalDateTime start,
        LocalDateTime end,
        @Positive
        Integer kilometers,
        Location location
) {

    public Run {
        if(!end.isAfter(start)) {
            throw new IllegalArgumentException("A data de término deve ser após a data de início");
        }
    }
}
