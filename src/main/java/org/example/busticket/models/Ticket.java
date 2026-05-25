package org.example.busticket.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {
    private Long id;
    private String passenger;
    private Integer seat;
    private String origin;
    private String destination;
    private LocalDate travelDate;
    private String status;
}


