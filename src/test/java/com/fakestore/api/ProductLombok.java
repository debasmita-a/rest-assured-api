package com.fakestore.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductLombok {

	private int id;
	private String title;
	private double price;
	private String description;
	private String category;
	private String image;
	private Rating rating;

	// Rating class
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	public static class Rating {
		private float rate;
		private int count;
	}
}
