package com.eebbk.joy.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.eebbk.joy.robot.RobotInfo;

public class RobotDao
{

	private DBHelper dbHelper;

	public RobotDao(Context context)
	{
		dbHelper = new DBHelper(context);
	}

	public void add(RobotInfo info)
	{
		String sql = "insert into db_robot (robot_text,robot_type) values(?,?) ;";
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		db.execSQL(sql,new Object[] {info.text, info.isMine?1:0});
		db.close();
	}

	private void deleteAll(int newsType)
	{
		String sql = "delete from tb_newsItem where newstype = ?";
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		db.execSQL(sql, new Object[] { newsType });
		db.close();
	}

	

	/**
	 * 根据newsType和currentPage从数据库中取数据
	 * 
	 * @param newsType
	 * @param currentPage
	 * @return
	 */
	public List<RobotInfo> getList()
	{

		// 0 -9 , 10 - 19 ,
		List<RobotInfo> infos = new ArrayList<RobotInfo>();
		try
		{
			String sql = "select robot_text,robot_type from db_robot";
			SQLiteDatabase db = dbHelper.getReadableDatabase();
			Cursor c = db.rawQuery(sql, null);

			RobotInfo info = null;

			while (c.moveToNext())
			{
				info = new RobotInfo();

				info.text = c.getString(0);
				info.isMine = 1 == c.getInt(1);

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
