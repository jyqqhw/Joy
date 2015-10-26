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
		/**
		 *
		 */
//		String sql = "create table tb_newsItem if not exists( _id integer primary key autoincrement , "
//				+ " title text , link text , date text , imgLink text , content text , newstype integer  );";
		
		//笑话的数据库
		String sql = "create table tb_newsItem if not exists( _id integer primary key autoincrement , "
				+ " joke_title text , joke_imgLink text , joke_date text ,  joke_type integer  );";
		
		//机器人的数据库
		
		//新闻的数据库
		
		
		db.execSQL(sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
	{
		// TODO Auto-generated method stub

	}

}
