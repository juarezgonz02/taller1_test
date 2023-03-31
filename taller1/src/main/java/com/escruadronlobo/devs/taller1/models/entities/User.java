package com.escruadronlobo.devs.taller1.models.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {

	private String code;
	private String Name;
	private String LastName;
	private String hiringDate;
	private Boolean isActive;
	private String rol;
	private String password;
	

}
