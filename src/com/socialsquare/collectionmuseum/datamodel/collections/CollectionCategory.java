package com.socialsquare.collectionmuseum.datamodel.collections;

import java.util.ArrayList;
import java.util.List;

public class CollectionCategory {
	private String description;
	private CollectionCategory fatherCategory;

	/**
	 * Default constructor.
	 * 
	 * @param description
	 */
	public CollectionCategory(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public CollectionCategory getFatherCategory() {
		return fatherCategory;
	}

	public void setFatherCategory(CollectionCategory fatherCategory) {
		this.fatherCategory = fatherCategory;
	}

	public static List<CollectionCategory> findAll() {
		ArrayList<CollectionCategory> result = new ArrayList<CollectionCategory>();
		result.add(new CollectionCategory("Videojuegos"));
		result.add(new CollectionCategory("Películas"));
		result.add(new CollectionCategory("Música"));
		return result;
	}
}
