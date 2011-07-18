package com.socialsquare.collectionmuseum.datamodel.collections;

import java.util.ArrayList;
import java.util.List;

import com.socialsquare.collectionmuseum.datamodel.operators.Operator;

public class Collection {
	public static final int MAX_SUBTITLE_LENGTH_POTRAIT = 35;
	public static final int MAX_SUBTITLE_LENGTH_LANDSCAPE = 60;

	private Integer maxSubtitleLength = MAX_SUBTITLE_LENGTH_POTRAIT;
	private String name;
	private String longDescription;
	private CollectionCategory category;
	private Operator operator;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSubtitle() {
		if (longDescription == null) {
			return null;
		}
		if (longDescription.length() > maxSubtitleLength) {
			return longDescription.substring(0, maxSubtitleLength) + "...";
		}
		return longDescription;
	}

	public String getLongDescription() {
		return longDescription;
	}

	public void setLongDescription(String longDescription) {
		this.longDescription = longDescription;
	}

	public CollectionCategory getCategory() {
		return category;
	}

	public void setCategory(CollectionCategory category) {
		this.category = category;
	}

	public Operator getOperator() {
		return operator;
	}

	public void setOperator(Operator operator) {
		this.operator = operator;
	}

	public static List<Collection> findAll() {
		ArrayList<Collection> result = new ArrayList<Collection>();

		Collection c = new Collection();
		c.setName("Videojuegos");
		c.setLongDescription("Mi colección de videojuegos");
		result.add(c);
		c = new Collection();
		c.setName("Películas");
		c.setLongDescription("1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890");
		result.add(c);

		return result;
	}

	public static Collection create(String name, String longDescription) {
		Collection c = new Collection();
		c.setName(name);
		c.setLongDescription(longDescription);
		return c;
	}

	public Integer getMaxSubtitleLength() {
		return maxSubtitleLength;
	}

	public void setMaxSubtitleLength(Integer maxSubtitleLength) {
		this.maxSubtitleLength = maxSubtitleLength;
	}
}
