package com.example.tarikul.simplecontactlist;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

public class Database {

	public static final String KEY_NAME = "name";
	public static final String KEY_NUMBER = "number";
	public static final String KEY_ROWID = "_id";

	private static final String DATABASE_NAME = "NUPUIT";
	private static final String DATABASE_TABLE = "contactslist";

	private static final int DATABASE_VERSION = 1;

	private DbHelper dbhelper;
	private final Context ourcontext;
	private SQLiteDatabase ourdatabase;

	private static class DbHelper extends SQLiteOpenHelper {

		@SuppressLint("NewApi")
		public DbHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
			// TODO Auto-generated constructor stub

		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub

			db.execSQL("CREATE TABLE " + DATABASE_TABLE + " (" + KEY_ROWID
					+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_NAME
					+ " TEXT NOT NULL, " + KEY_NUMBER + " TEXT NOT NULL );");
		//	db.execSQL(DATABASE_TABLE);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);

			onCreate(db);
		}

	}

	public Database(Context c) {
		ourcontext = c;
	}

	public Database open() {

		dbhelper = new DbHelper(ourcontext);
		ourdatabase = dbhelper.getWritableDatabase();
		return this;
	}

	public void close() {
		dbhelper.close();
	}

	public long  saveContactsToDB(String name,String phonenumber){
		// TODO Auto-generated method stub
		dbhelper = new DbHelper(ourcontext);
		ourdatabase = dbhelper.getWritableDatabase();
		ContentValues cv = new ContentValues();

		cv.put(KEY_NAME, name);
		cv.put(KEY_NUMBER, phonenumber);

		return ourdatabase.insert(DATABASE_TABLE, null, cv);

	}


	
	public List<ContactListModel> getContacts() {
		List<ContactListModel> list = new ArrayList<>();
		String getdata = "Select * from "
				+ DATABASE_TABLE + "limit 10" ;

		Cursor read = ourdatabase.rawQuery(getdata, null);


		int iNumber = read.getColumnIndex(KEY_NUMBER);
		int iName = read.getColumnIndex(KEY_NAME);

		while (read.moveToNext()) {

			String name = read.getString(iName);
			String phoneNumber = read.getString(iNumber);

			ContactListModel contactListModel = new ContactListModel(name,phoneNumber);
			list.add(contactListModel);
		}
		return list;
	}
	public List<ContactListModel> getMoreContacts() {
		List<ContactListModel> list = new ArrayList<>();
		String getdata = "Select * from "
				+ DATABASE_TABLE + "limit 10 OFFSET 10" ;

		Cursor read = ourdatabase.rawQuery(getdata, null);


		int iNumber = read.getColumnIndex(KEY_NUMBER);
		int iName = read.getColumnIndex(KEY_NAME);

		while (read.moveToNext()) {

			String name = read.getString(iName);
			String phoneNumber = read.getString(iNumber);

			ContactListModel contactListModel = new ContactListModel(name,phoneNumber);
			list.add(contactListModel);
		}
		return list;
	}



//	public List<ContactListModel> getAllData() {
//		List<ContactListModel> list = new ArrayList<>();
//
//		while (cursor.moveToNext()) {
//			int index = cursor.getColumnIndex(DataBaseHelper.UID);
//			int index2 = cursor.getColumnIndex(DataBaseHelper.NAME);
//			int index3 = cursor.getColumnIndex(DataBaseHelper.CARD);
//			int index4 = cursor.getColumnIndex(DataBaseHelper.CODE);
//			int cid = cursor.getInt(index);
//			String name = cursor.getString(index2);
//			String card = cursor.getString(index3);
//			String code = cursor.getString(index4);
//			DataBean bean = new DataBean(cid, name, card, code);
//			list.add(bean);
//		}
//		return list;
//	}

}
