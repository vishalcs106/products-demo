package com.demo.productsdemo.base;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Vishal on 13-05-2018.
 */

public class BaseActivity extends AppCompatActivity{
    protected void showErrorDialog(String message){
       AlertDialog.Builder builder = new AlertDialog.Builder(this);
       builder.setTitle("Error").setMessage(message).create().show();
    }
}
