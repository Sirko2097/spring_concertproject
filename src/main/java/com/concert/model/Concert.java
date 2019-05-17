package com.concert.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Concert {

    private Integer concertId;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate concertDate;

    @NotNull
    private Integer breakId;

    @NotNull
    private String concertName;

    @NotNull
    private Integer announcerId;

    public void setConcertDate(String concertDate) {
        this.concertDate = LocalDate.parse(concertDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}
