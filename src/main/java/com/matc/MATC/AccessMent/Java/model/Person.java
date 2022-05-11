package com.matc.MATC.AccessMent.Java.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.List;


@Data
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name ="person")
public class Person {
	@Id
	private Long personId;

	@Column
	private String name;

	@Column
	private	String email;

	@Column
	private	String mobileNumber;



}
