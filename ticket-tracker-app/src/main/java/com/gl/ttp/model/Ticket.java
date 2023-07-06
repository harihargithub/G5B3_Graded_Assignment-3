package com.gl.ttp.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name ="tickettracker")
public class Ticket {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name="title")
    private String title;
	
	@Column(name="description")
    private String description;
	
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	@Column(name="createdon")
    private LocalDate createdOn;


}
