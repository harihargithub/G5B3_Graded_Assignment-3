package com.gl.ttp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.bind.annotation.RestController;

import com.gl.ttp.model.Comment;
import com.gl.ttp.model.Ticket;
import com.gl.ttp.service.TicketService;

@Controller
//@RestController
@RequestMapping("/tickets")
public class TicketController {

	@Autowired
	private TicketService ticketService;

	public TicketController(TicketService ticketService) {
		this.ticketService = ticketService;
	}

	@GetMapping("/list")
	public String getAllTickets(Model model) {
		List<Ticket> tickets = ticketService.getAllTickets();
		model.addAttribute("tickets", tickets);
		return "ticket/list-tickets";
	}

	@GetMapping("/add")
	public String showTicketForm(Model model) {
		model.addAttribute("ticket", new Ticket());
		return "ticket/ticket-form";
	}

	@PostMapping("/save")
	public String createTicket(@ModelAttribute("ticket") Ticket ticket) {
		ticketService.createTicket(ticket);
		return "redirect:/tickets/list";
	}

	@GetMapping("/{id}")
	public String getTicketById(@PathVariable("id") String id, Model model) {
		try {
			Long ticketId = Long.parseLong(id);
			Optional<Ticket> ticket = ticketService.getTicketById(ticketId);
			ticket.ifPresent(value -> model.addAttribute("ticket", value));
		} catch (NumberFormatException e) {
			// Handle the case where the id parameter is not a valid Long value
			// For example, you can display an error message or redirect to an error page
		}
		model.addAttribute("comment", new Comment());
		return "ticket/ticket-form";
	}

	/*
	 * @GetMapping("/{id}") public String getTicketById(@PathVariable Long id, Model
	 * model) { Optional<Ticket> ticket = ticketService.getTicketById(id);
	 * ticket.ifPresent(value -> model.addAttribute("ticket", value)); return
	 * "ticket-details"; }
	 */

	@GetMapping("/{id}/edit")
	public String showEditForm(@PathVariable Long id, Model model) {
		Optional<Ticket> ticket = ticketService.getTicketById(id);
		ticket.ifPresent(value -> model.addAttribute("ticket", value));
		return "ticket-edit-form";
	}

	@PostMapping("/{id}/edit")
	public String updateTicket(@PathVariable Long id, @ModelAttribute("ticket") Ticket ticket) {
		ticketService.updateTicket(id, ticket);
		return "redirect:/tickets/list";
	}

	@GetMapping("/{id}/delete")
	public String deleteTicket(@PathVariable Long id) {
		ticketService.deleteTicket(id);
		return "redirect:/tickets/list";
	}

	@GetMapping("/search")
	public String searchTickets(@RequestParam("keyword") String keyword, Model model) {
		List<Ticket> searchResults = ticketService.searchTicket(keyword);
		model.addAttribute("tickets", searchResults);
		model.addAttribute("keyword", keyword);
		return "ticket/list-tickets";
	}
	
	@PostMapping("/{id}/comments/saveFromForm")
	public String addCommentToTicket(@PathVariable("id") Long ticketId, @ModelAttribute("comment") Comment comment) {
	    ticketService.addCommentToTicket(ticketId, comment);
	    return "redirect:/tickets/" + ticketId;
	}

	@PostMapping("/{id}/comments/save")
	public String addCommentToTicketFromList(@PathVariable("id") Long ticketId, @RequestParam("commentText") String commentText) {
	    Comment comment = new Comment();
	    comment.setText(commentText);
	    ticketService.addCommentToTicket(ticketId, comment);
	    return "redirect:/tickets/list";
	}



}
