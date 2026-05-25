package org.example.busticket.controllers;

import org.example.busticket.dtos.TicketRequestDTO;
import org.example.busticket.dtos.TicketResponseDTO;
import org.example.busticket.services.TicketService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping
    public List<TicketResponseDTO> findAll() {
        return ticketService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TicketResponseDTO create(@RequestBody TicketRequestDTO dto) {
        return ticketService.create(dto);
    }

    @GetMapping("/{id}")
    public TicketResponseDTO findById(@PathVariable Long id) {
        return ticketService.findById(id);
    }

    @PutMapping("/{id}")
    public TicketResponseDTO update(@PathVariable Long id, @RequestBody TicketRequestDTO dto) {
        return ticketService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        ticketService.delete(id);
    }

    @GetMapping("/search")
    public List<TicketResponseDTO> findByDestination(@RequestParam String destination) {
        return ticketService.findByDestination(destination);
    }
}


