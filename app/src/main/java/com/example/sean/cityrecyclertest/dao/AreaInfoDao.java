package com.example.sean.cityrecyclertest.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.sean.cityrecyclertest.bean.Areainfo;

import java.util.ArrayList;
import java.util.List;


/**
 * 查询籍贯数据库,获取地区信息
 *
 * @author Jet
 * @data 2016-12-8
 */
public class AreaInfoDao {
    public static String path = "data/data/com.example.sean.cityrecyclertest/files/Areainfo.db";

    // 获取Cjwd_areainfo表中组数据方法
    public static List<Areainfo> getAreaInfo(int id) {
        List<Areainfo> areainfoList = new ArrayList<Areainfo>();
        SQLiteDatabase db = SQLiteDatabase.openDatabase(path, null,
                SQLiteDatabase.OPEN_READONLY);

        Cursor cursor = db.query("areainfo", new String[]{"AreaId",
                        "AreaName", "ParentId"}, "ParentId=" + id, null, null, null,
                null);
        while (cursor.moveToNext()) {
            Areainfo areainfo = new Areainfo();
            areainfo.setAreaId(Integer.parseInt(cursor.getString(0)));
            areainfo.setAreaName(cursor.getString(1));
            areainfo.setParentId(Integer.parseInt(cursor.getString(2)));
            areainfoList.add(areainfo);
        }
        cursor.close();
        db.close();
        return areainfoList;
    }
}
