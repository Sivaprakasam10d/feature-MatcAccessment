package com.matc.MATC.AccessMent.Java.MongoModels;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;


@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document("user_mo")
public class UserMo {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long userId;
	@Column
	private String userName;
	@Column
	private String password;
	@Column
	private String role;
	
}
