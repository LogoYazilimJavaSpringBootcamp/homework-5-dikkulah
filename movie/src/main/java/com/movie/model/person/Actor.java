package com.movie.model.person;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
@Entity
@Getter
@Setter
@AllArgsConstructor
@Table(name = "actors")
public class Actor extends Person{

}
