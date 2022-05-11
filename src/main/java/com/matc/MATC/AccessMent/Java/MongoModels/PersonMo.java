package com.matc.MATC.AccessMent.Java.MongoModels;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;


@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document("person")
public class PersonMo {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long personId;

	@Column
	private String name;

	@Column
	private	String email;

	@Column
	private	String mobileNumber;

}
