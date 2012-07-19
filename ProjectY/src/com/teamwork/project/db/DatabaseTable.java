package com.teamwork.project.db;

// ������������ ��� ������� � ��
// �����-�� ����� ������ ���� ������ � ��������������


import android.database.sqlite.SQLiteDatabase;



public interface DatabaseTable {
	
	// Here we write basic fields which should be defined
	// in class which implements the i-face
	// It is not required, but helps with documenting
	static final String TABLE_NAME = null;
	
	
	// ������� ������-������ �� �������� ������� �� ��������
	public String getSQLCreateString();
	
	// ������-������ �� �������� �������
	public String getSQLDropString();
	
	// ���������� ������� � �������
	public long storeObject(DatabaseGenericObject obj);
	
	// ���������� ������� � �������
	// �������� �� ���� � ��� ������, ������ ��� �������� ������, ����
	public long updateObject(DatabaseGenericObject obj);
	
	// ������ �� �� ������ � �������� ����
	public DatabaseGenericObject readObject(long id);
	
	// ������� � ���� ������ � ������ ����
	public void deleteObject(long id);
	
	
	// ��������� �������� �� �� ��������� � ������ ��������
	// ����� � ������ ���� ����������� ���-�� ������
	public void assignDatabase(SQLiteDatabase db);
	
	
		
}
