package com.googl.xcdq.mytest;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.TimeUtils;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    private EditText mEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEditText = (EditText) findViewById(R.id.editText);
        String inputText = load();
        //TextUtils.isEmpty()可以一次性判断两种非空判断 传入null或者空, 都返回true
        if (!(TextUtils.isEmpty(inputText))) {
            mEditText.setText(inputText);
            //setSelection()表示将光标移动在文本框的末尾位置, 以便继续输入
            mEditText.setSelection(inputText.length());
            //弹出Toast, 给出一个提示, 表示读取数据成功
            Toast.makeText(this, "读取数据成功!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //得到mEditText上的文本
        String inputText = mEditText.getText().toString();
        save(inputText);
    }

    private void save(String inputText) {
        FileOutputStream out = null;
        BufferedWriter writer = null;
        try {
            //使用openFileOutput创建一个文件来存数据
            out = openFileOutput("data", Context.MODE_APPEND);
            //创建一个BufferedWriter对象来写数据到创建文件夹(包装一下OutputStreamWriter对象)
            writer = new BufferedWriter(new OutputStreamWriter(out));
            //调用writer方法将用户传过来的数据存到文件中去
            writer.write(inputText);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String load() {
        FileInputStream in = null;
        BufferedReader br = null;
        StringBuffer stringBuffer = new StringBuffer();
        try {
            //调用openFileInput打开存数据的文件夹
            in = openFileInput("data");
            //包装一下
            br = new BufferedReader(new InputStreamReader(in));
            String line = "";
            //一行行读数据
            while ((line = br.readLine()) != null) {
                //调用StringBuffer的append的方法一行行已添加读到的数据
                stringBuffer.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //stringBuffer是一个字符串流
        return stringBuffer.toString();
    }
}
