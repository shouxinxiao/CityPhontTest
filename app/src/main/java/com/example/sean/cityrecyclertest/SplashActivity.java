package com.example.sean.cityrecyclertest;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Sean on 2017/5/20.
 */

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDataBase();// 初始化数据库

        startActivity(new Intent(this,MainActivity.class));
    }

    /**
     * 初始化数据库
     */
    private void initDataBase() {
        copyDB("Areainfo.db");
    }
    private void copyDB(String dbName) {
        // 1,将籍贯数据库拷贝到,工程的第三方资产目录
        // 2,将第三方资产文件中的数据库,读取到工程的files文件夹
        // 2.1获取file文件夹路径
        File filesDir = getFilesDir();
        // 2.2创建相应文件夹下文件
        File file = new File(filesDir, dbName);
        if (file.exists()) {
            return;
        }
        InputStream is = null;
        FileOutputStream fos = null;
        // 2.3打开第三方资产目录,并且将数据库通过流的方式读取出来
        try {
            is = getAssets().open(dbName);
            // 2.4写入指定文件
            byte[] buffer = new byte[1024];
            int temp = -1;
            fos = new FileOutputStream(file);
            while ((temp = is.read(buffer)) != -1) {
                fos.write(buffer, 0, temp);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null && fos != null) {
                    is.close();
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
