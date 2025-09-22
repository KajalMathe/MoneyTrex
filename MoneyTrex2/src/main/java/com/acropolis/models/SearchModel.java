package com.acropolis.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SearchModel {

	private Integer month;
	private Integer year;
	private String type;
}
