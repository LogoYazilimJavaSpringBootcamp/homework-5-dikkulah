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
@Table(name = "directors")
public class Director extends Person {

}
