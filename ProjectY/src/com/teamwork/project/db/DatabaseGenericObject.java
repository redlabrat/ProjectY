package com.teamwork.project.db;

// Объект хранимый в БД.
// Может быть понадобиться переход на универсальный объект
// поэтому в БД все завязано именно на него.

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;



class DatabaseGenericObject implements Parcelable{
	static long counter = 0;

	long id;
	String data1;
	boolean isChecked;
	
	DatabaseGenericObject(){
		counter++;
		id=counter;
		isChecked = false;
		data1 = "Sample " + Long.toString(id);
	}
	
	@Override
	public String toString() {
		return "Obj " + Long.toString(this.id) + this.data1 + "isChk=" + isChecked;
	}

	
	public static DatabaseGenericObject cursorToObject(Cursor cursor){
		DatabaseGenericObject obj = new DatabaseGenericObject();

		obj.id=cursor.getInt(0);
		obj.data1=cursor.getString(1);
//		obj.isChecked=Boolean.parseBoolean(cursor.getString(2));
		
		return obj;
	}
	
	
//	PARCELABLE INTERFACE
	DatabaseGenericObject(Parcel p){
		id = p.readLong();
		data1 = p.readString();
		isChecked = Boolean.parseBoolean(p.readString());
	} 


	public static final Parcelable.Creator<DatabaseGenericObject> CREATOR = new Parcelable.Creator<DatabaseGenericObject>() {
		public DatabaseGenericObject createFromParcel(Parcel parcel) {
			return new DatabaseGenericObject(parcel);
		}

		public DatabaseGenericObject[] newArray(int size) {
			return new DatabaseGenericObject[size];
		}
	};

	
	public int describeContents() {
		return 0;
	}

	
	public void writeToParcel(Parcel p, int flags) {
		p.writeLong(id);
		p.writeString(data1);
		p.writeString(Boolean.toString(isChecked));
	}
	
//	END OF PARCELABLE INTERFACE
	
	
	
	
	
	
}
