package com.teamwork.project.db;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "tbl_dicionary_exercise")
public class DictionaryExercise {
	
	
	
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_NAME = "name";
	public static final String COLUMN_DESCRIPTION = "description";
	
	@DatabaseField(generatedId = true, columnName = COLUMN_ID)
	public Integer id;
	
	@DatabaseField(columnName = COLUMN_NAME)
	private String name;
	

	@DatabaseField(columnName = COLUMN_DESCRIPTION)
	private String description;
	
		
	DictionaryExercise() {
		// needed by ormlite
	}
	
	public DictionaryExercise(String nm, String descr) {
		name = nm;
		this.description = descr;
	}
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	
	public String getName() {
		return this.name;
	}

	public void setName(String nm) {
		this.name = nm;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String descr) {
		this.description = descr;
	}
	
	
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("DICT_EX: id=").append(id);
		sb.append(", ").append("name=").append(name);
		sb.append(", ").append("descr=").append(description);
		return sb.toString();
	}
	

}
