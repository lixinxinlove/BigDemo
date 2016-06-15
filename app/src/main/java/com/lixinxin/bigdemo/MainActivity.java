package com.lixinxin.bigdemo;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.lixinxin.bigdemo.service.BindService;
import com.lixinxin.bigdemo.service.MyService;
import com.lixinxin.bigdemo.ui.DialogActivity;
import com.lixinxin.bigdemo.ui.NormalActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private MyServiceConn conn;
    BindService.ZhouMi zm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Log.e(TAG, "onCreate");

        conn = new MyServiceConn();

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG, "onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e(TAG, "onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy");
    }

    @OnClick({R.id.btn_dialog_activity,
            R.id.btn_dialog,
            R.id.btn_normal_activity,
            R.id.btn_stop_service,
            R.id.btn_start_server,
            R.id.btn_start,
            R.id.btn_bind,
            R.id.btn_stop,
            R.id.btn_unbind,
            R.id.btn_use_service
    })
    public void bigDemo(View v) {
        Intent intent;
        Intent intent2 = new Intent(MainActivity.this, BindService.class);
        switch (v.getId()) {
            case R.id.btn_dialog_activity:
                intent = new Intent(MainActivity.this, DialogActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_dialog:
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("提示").setMessage("lllllllllll").setNegativeButton("取消", null);
                builder.create().show();
                break;
            case R.id.btn_normal_activity:
                intent = new Intent(MainActivity.this, NormalActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_start_server:
                intent = new Intent(MainActivity.this, MyService.class);
                intent.putExtra("name", "lxinxin");
                startService(intent);
                break;
            case R.id.btn_stop_service:
                intent = new Intent(MainActivity.this, MyService.class);
                stopService(intent);
                break;
            case R.id.btn_start:
                startService(intent2);
                break;
            case R.id.btn_stop:
                stopService(intent2);
                break;
            case R.id.btn_bind:
                bindService(intent2, conn, BIND_AUTO_CREATE);
                break;
            case R.id.btn_unbind:
                unbindService(conn);
                break;
            case R.id.btn_use_service:
               zm.aa();
                break;
        }
    }


    class MyServiceConn implements ServiceConnection {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            zm= (BindService.ZhouMi) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.e(TAG,"剪出绑定");
        }
    }

}
