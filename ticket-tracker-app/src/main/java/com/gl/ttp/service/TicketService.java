package com.gl.ttp.service;

import java.util.List;
import java.util.Optional;

import com.gl.ttp.model.Ticket;

public interface TicketService {
	
	List<Ticket> getAllTickets();
	
	Optional<Ticket> getTicketById(Long id);
	
	Ticket createTicket(Ticket ticket);
	
	Ticket updateTicket(Long id, Ticket ticket);
	
	void deleteTicket(Long id);
	
	List<Ticket> searchTicket(String keyword);

}

