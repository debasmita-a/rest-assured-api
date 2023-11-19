package com.pet.api;

import java.util.List;

import javax.swing.text.html.HTML.Tag;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Pet {

	private Integer id;
	private Category category;
	private String name;
	private List<String> photoUrls;
	private List<Tag> tags;
	private String status;
	
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder

	public static class Category{
		
		private Integer id;
		private String name;
	}
	
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	
	public static class Tag{
		private Integer id;
		private String name;
	}
	
}
