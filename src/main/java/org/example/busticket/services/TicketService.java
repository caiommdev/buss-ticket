package org.example.busticket.services;

import org.example.busticket.dtos.TicketRequestDTO;
import org.example.busticket.dtos.TicketResponseDTO;
import org.example.busticket.models.Ticket;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class TicketService {

    private List<Ticket> tickets = new ArrayList<>();
    private Long idCounter = 1L;

    public TicketService() {
        tickets.add(new Ticket(idCounter++, "Ana Souza", 10, "Rio de Janeiro", "Sao Paulo", LocalDate.now().plusDays(2), "CONFIRMED"));
        tickets.add(new Ticket(idCounter++, "Bruno Lima", 15, "Belo Horizonte", "Vitoria", LocalDate.now().plusDays(4), "PENDING"));
        tickets.add(new Ticket(idCounter++, "Carla Dias", 20, "Curitiba", "Florianopolis", LocalDate.now().plusDays(6), "CONFIRMED"));
    }

    public List<TicketResponseDTO> findAll() {
        return tickets.stream()
                .map(this::toResponseDto)
                .toList();
    }

    public TicketResponseDTO create(TicketRequestDTO dto) {
        boolean seatAlreadyTaken = tickets.stream()
                .anyMatch(ticket -> ticket.getSeat().equals(dto.getSeat()));

        if (seatAlreadyTaken) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Seat is already reserved");
        }

        Ticket newTicket = toEntity(dto);
        newTicket.setId(idCounter++);
        tickets.add(newTicket);

        return toResponseDto(newTicket);
    }

    public TicketResponseDTO findById(Long id) {
        Ticket ticket = tickets.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ticket not found"));

        return toResponseDto(ticket);
    }

    public TicketResponseDTO update(Long id, TicketRequestDTO dto) {
        Ticket ticket = tickets.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ticket not found"));

        boolean seatAlreadyTakenByAnotherTicket = tickets.stream()
                .anyMatch(t -> !t.getId().equals(id) && t.getSeat().equals(dto.getSeat()));

        if (seatAlreadyTakenByAnotherTicket) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Seat is already reserved");
        }

        ticket.setPassenger(dto.getPassenger());
        ticket.setSeat(dto.getSeat());
        ticket.setOrigin(dto.getOrigin());
        ticket.setDestination(dto.getDestination());
        ticket.setTravelDate(dto.getTravelDate());
        ticket.setStatus(dto.getStatus());

        return toResponseDto(ticket);
    }

    public void delete(Long id) {
        boolean exists = tickets.stream().anyMatch(t -> t.getId().equals(id));
        if (!exists) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ticket not found");
        }

        tickets.removeIf(t -> t.getId().equals(id));
    }

    public List<TicketResponseDTO> findByDestination(String destination) {
        return tickets.stream()
                .filter(ticket -> ticket.getDestination().equalsIgnoreCase(destination))
                .map(this::toResponseDto)
                .toList();
    }

    private Ticket toEntity(TicketRequestDTO dto) {
        return new Ticket(
                null,
                dto.getPassenger(),
                dto.getSeat(),
                dto.getOrigin(),
                dto.getDestination(),
                dto.getTravelDate(),
                dto.getStatus()
        );
    }

    private TicketResponseDTO toResponseDto(Ticket ticket) {
        return new TicketResponseDTO(
                ticket.getId(),
                ticket.getPassenger(),
                ticket.getSeat(),
                ticket.getOrigin(),
                ticket.getDestination(),
                ticket.getTravelDate(),
                ticket.getStatus()
        );
    }
}


