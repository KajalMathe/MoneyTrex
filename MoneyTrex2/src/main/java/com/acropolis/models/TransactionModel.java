package com.acropolis.models;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TransactionModel {

	private String title;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate entrydate;
	private String type;
	private Float amount;
	private String description;
	
}
