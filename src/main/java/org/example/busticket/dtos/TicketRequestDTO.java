package org.example.busticket.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketRequestDTO {
    private String passenger;
    private Integer seat;
    private String origin;
    private String destination;
    private LocalDate travelDate;
    private String status;
}


