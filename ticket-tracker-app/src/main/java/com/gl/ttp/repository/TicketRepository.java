package com.gl.ttp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gl.ttp.model.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long>{

}
