package com.teamwork.project.db;

// документация для таблицы в БД
// какие-то штуки должны быть общими и универсальными


import android.database.sqlite.SQLiteDatabase;



public interface DatabaseTable {
	
	// Here we write basic fields which should be defined
	// in class which implements the i-face
	// It is not required, but helps with documenting
	static final String TABLE_NAME = null;
	
	
	// Создаем строку-запрос на создание таблицы по проекции
	public String getSQLCreateString();
	
	// Строка-запрос на удаление таблицы
	public String getSQLDropString();
	
	// Сохранение объекта в таблице
	public long storeObject(DatabaseGenericObject obj);
	
	// Обновление объекта в таблице
	// передаем не айди а сам объект, потому что передаем данные, епты
	public long updateObject(DatabaseGenericObject obj);
	
	// Читаем из БД объект с заданным айди
	public DatabaseGenericObject readObject(long id);
	
	// Удаляем и базы строку с данным айди
	public void deleteObject(long id);
	
	
	// связывает открытую БД из датасорса с каждой таблицой
	// чтобы у каждой была возможность что-то писать
	public void assignDatabase(SQLiteDatabase db);
	
	
		
}
