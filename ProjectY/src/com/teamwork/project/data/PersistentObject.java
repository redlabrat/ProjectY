package com.teamwork.project.data;

import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.teamwork.project.ProjectYApplication;

public abstract class PersistentObject {

	public abstract Integer getId();
	public abstract String toString();
	
//	public abstract <T extends PersistentObject> RuntimeExceptionDao<T, Integer> getDao();
	@SuppressWarnings("unchecked")
	public <T extends PersistentObject> RuntimeExceptionDao<T,Integer> getDao() {
		return (RuntimeExceptionDao<T, Integer>) ProjectYApplication.getDataSource().dao(getClass());
	}
	
	public void storeToDB() {
		this.getDao().create(this);
	}

	public void updateInDB() {
		if(this.getId()!=null)
			this.getDao().update(this);
	}

	public void deleteFromDB() {
		if(this.getId()!=null)
			this.getDao().deleteById(this.getId());
	}
	
	// Every class contains a STATIC method which creates an object from DB by it's id
	// public static <T extends PersistentObject> T loadFromDB(Integer id);
	
//	public void storeToDB();
//	public void updateToDB();
//	public void deleteFromDB();
	
	
	// YOU SHOULD create methods which:
	// return a parent-object for idObject
	
}
