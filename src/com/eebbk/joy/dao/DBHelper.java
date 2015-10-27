package com.eebbk.joy.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper
{
	private static final String DB_NAME = "Big_Lazy_Cat";

	public DBHelper(Context context)
	{
		super(context, DB_NAME, null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db)
	{
		
		//Ц�������ݿ�
//		String jokesql = "create table db_joke( _id integer primary key autoincrement , "
//				+ " joke_title text , joke_imgLink text , joke_date text ,  joke_type integer);";
		
		//�����˵����ݿ�
		String robotsql = "create table db_robot( _id integer primary key autoincrement , "
				+ " robot_text text , robot_type integer);";
		
		//���ŵ����ݿ�
		
		
//		db.execSQL(jokesql);
		db.execSQL(robotsql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
	{
		// TODO Auto-generated method stub

	}

}
