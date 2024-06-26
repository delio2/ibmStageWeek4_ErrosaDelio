package com.stage.cic;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "MODULE")
public class Module {

	// creo id
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	// creo nome
	@Column(nullable = false, length = 32)
	private String name;

	// creo cognome
	@Column(nullable = false, length = 32)
	private String teacher;

	@ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
}
