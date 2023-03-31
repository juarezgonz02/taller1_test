package com.escruadronlobo.devs.taller1.models.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorsDto {

	private List<String> messages;

	public ErrorsDto() {
		super();
		messages = new ArrayList<>();

	}

	
	

}
