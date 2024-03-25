package com.stage.cic;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "COURSE")
public class Course {
	// creo id
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	// creo nome
	@Column(nullable = false, length = 32)
	private String name;

	@OneToMany(mappedBy = "course")
	private List<Module> module;

	@OneToMany(mappedBy = "course")
	private List<Registration> registration;
	
}
