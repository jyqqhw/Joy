package com.eebbk.joy.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.eebbk.joy.joke.JokeInfo;
import com.eebbk.joy.picture.PictureInfo;
import com.eebbk.joy.robot.RobotInfo;
import com.eebbk.joy.utils.JoyConstant;

public class JokeDao
{

	private DBHelper dbHelper;

	public JokeDao(Context context)
	{
		dbHelper = new DBHelper(context);
	}

	
	
	public void addPicture(PictureInfo info)
	{
		String sql = "insert into db_joke (joke_title,joke_imgLink,joke_date,"
				+ "joke_type) values(?,?,?,?) ;";
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		db.execSQL(sql,new Object[] {info.title, info.imageUrl,info.updateTime,info.type});
		db.close();
	}
	
	public void addJoke(JokeInfo info)
	{
		String sql = "insert into db_joke (joke_title,joke_imgLink,joke_date,joke_type) values(?,?,?,?);";
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		db.execSQL(sql,new Object[] {info.content, null,info.updateTime,0});
		db.close();
	}
	
	public void addAllPic(List<PictureInfo> infos)
	{
		if(null == infos || infos.isEmpty()){
			return;
		}
		for(PictureInfo info:infos){
			addPicture(info);
		}
		
	}
	
	public void addAllJoke(List<JokeInfo> infos)
	{
		if(null == infos || infos.isEmpty()){
			return;
		}
		for(JokeInfo info:infos){
			addJoke(info);
		}
		
	}
	
	

	public void deleteAll(int type)
	{
		String sql = "delete from db_joke where joke_type = ?";
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		db.execSQL(sql, new Object[] { type });
		db.close();
	}

	

	/**
	 * 从数据库中取趣图数据
	 * 
	 */
	public List<PictureInfo> getPicList()
	{
		List<PictureInfo> infos = new ArrayList<PictureInfo>();
		try
		{
			String sql = "select joke_title,joke_imgLink,joke_date,joke_type from db_joke where joke_type = ?";
			SQLiteDatabase db = dbHelper.getReadableDatabase();
			Cursor c = db.rawQuery(sql, new String[] {String.valueOf(JoyConstant.JOKE_TYPE_PICTURE)} );

			PictureInfo info = null;

			while (c.moveToNext())
			{
				info = new PictureInfo();

				info.title = c.getString(0);
				info.imageUrl = c.getString(1);
				info.updateTime = c.getString(2);
				info.type = c.getInt(3);
				infos.add(info);

			}
			c.close();
			db.close();
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return infos;

	}
	
	
	/**
	 * 从数据库中取笑话数据
	 * 
	 */
//	joke_title text , joke_imgLink text , joke_date text ,  joke_type integer
	public List<JokeInfo> getJokeList()
	{
		List<JokeInfo> infos = new ArrayList<JokeInfo>();
		try
		{
			String sql = "select joke_title,joke_imgLink,joke_date,joke_type from db_joke where joke_type = ?";
			SQLiteDatabase db = dbHelper.getReadableDatabase();
			Cursor c = db.rawQuery(sql, new String[] {String.valueOf(JoyConstant.JOKE_TYPE_JOKE)} );

			JokeInfo info = null;

			while (c.moveToNext())
			{
				info = new JokeInfo();

				info.content = c.getString(0);
				info.url = c.getString(1);
				info.updateTime = c.getString(2);
				info.type = c.getInt(3);
				infos.add(info);

			}
			c.close();
			db.close();
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return infos;

	}
	
	

}
