package com.example.user.sharedpaint;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class ColorPicker extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_picker);

        findViewById(R.id.red).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onBackPressed(0xffff0000);
            }
        });

        findViewById(R.id.yellow).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onBackPressed(0xffffff00);
            }
        });

        findViewById(R.id.black).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onBackPressed(0xff000000);
            }
        });

        findViewById(R.id.blue).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onBackPressed(0xff0000ff);
            }
        });

        findViewById(R.id.gray).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onBackPressed(0xff888888);
            }
        });

        findViewById(R.id.green).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onBackPressed(0xff00ff00);
            }
        });

        findViewById(R.id.orange).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onBackPressed(0xffe3622f);
            }
        });

        findViewById(R.id.brown).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onBackPressed(0xff6f4e37);
            }
        });

        findViewById(R.id.Indigo).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onBackPressed(0xff006dc0);
            }
        });

        findViewById(R.id.pink).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onBackPressed(0xffeea5a7);
            }
        });

        findViewById(R.id.white).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onBackPressed(0xffffffff);
            }
        });

        findViewById(R.id.purple).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onBackPressed(0xff562f7e);
            }
        });
    }

    public void onBackPressed(int num) {
        Intent i = new Intent(ColorPicker.this, MainActivity.class);

        i.putExtra("codeColor", num);
        setResult(RESULT_OK, i);
        finish();
    }

}
