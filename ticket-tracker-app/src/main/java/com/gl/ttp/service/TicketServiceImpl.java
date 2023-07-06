package com.gl.ttp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.ttp.model.Ticket;
import com.gl.ttp.repository.TicketRepository;

@Service
public class TicketServiceImpl implements TicketService {

	@Autowired
	private final TicketRepository ticketRepository;

	public TicketServiceImpl(TicketRepository ticketRepository) {
		this.ticketRepository = ticketRepository;
	}

	@Override
	public List<Ticket> getAllTickets() {
		return ticketRepository.findAll();
	}

	@Override
	public Optional<Ticket> getTicketById(Long id) {
		return ticketRepository.findById(id);
	}

	@Override
	public Ticket createTicket(Ticket ticket) {
		return ticketRepository.save(ticket);
	}

	@Override
	public Ticket updateTicket(Long id, Ticket ticket) {

		Optional<Ticket> opttkt = ticketRepository.findById(id);
		if (opttkt.isPresent()) {
			Ticket tkt = opttkt.get();
			tkt.setTitle(ticket.getTitle());
			tkt.setDescription(ticket.getDescription());
			tkt.setCreatedOn(ticket.getCreatedOn());
		}
		return ticket;
	}

	@Override
	public void deleteTicket(Long id) {
		ticketRepository.deleteById(id);
	}

	@Override
	public List<Ticket> searchTicket(String keyword) {
		List<Ticket> searchResults = new ArrayList<>();

		// Implement search logic based on the provided keyword
		// For example, search by title, description, or createdOn
		List<Ticket> allTickets = ticketRepository.findAll();
		for (Ticket ticket : allTickets) {
			if (ticket.getTitle().toLowerCase().contains(keyword.toLowerCase())
					|| ticket.getDescription().toLowerCase().contains(keyword.toLowerCase())
					|| ticket.getCreatedOn().toString().contains(keyword)) {
				searchResults.add(ticket);
			}
		}

		return searchResults;

	}

}
