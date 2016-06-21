package com.example.user.sharedpaint;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ImageView iv;
    private Bitmap baseBitmap;
    private int startX;
    private int startY;
    public int mColors = 0xff000000;
    private Canvas canvas;
    private Paint paint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv = (ImageView) findViewById(R.id.iv);
        initPaint();

        iv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: // 手指点下的时候
                        System.out.println("手指点下");

                        // 获取落点的开始位置
                        startX = (int) event.getX();
                        startY = (int) event.getY();
                        break;

                    case MotionEvent.ACTION_MOVE: // 手指移动的时候
                        System.out.println("手指移动");
                        int stopX = (int) event.getX();
                        int stopY = (int) event.getY();
                        canvas.drawLine(startX, startY, stopX, stopY, paint);
                        iv.setImageBitmap(baseBitmap);

                        // 重新获取画笔的开始位置
                        startX = (int) event.getX();
                        startY = (int) event.getY();
                        break;

                    case MotionEvent.ACTION_UP: // 手指起来的时候
                        break;
                }
                return true;
            }
        });

        iv.getViewTreeObserver().

                addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        baseBitmap = Bitmap.createBitmap(iv.getWidth(), iv.getHeight(), Bitmap.Config.ARGB_8888); //
                        // 创建一个可以被修改的
                        canvas = new Canvas(baseBitmap); // 创建一个画布
                        canvas.drawColor(Color.WHITE);
                    }
                });

        findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                clearCanvas(v);
            }
        });

        findViewById(R.id.saveButton).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                OnSaveClicked(v);
            }
        });

        findViewById(R.id.newPage).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

        findViewById(R.id.colorPick).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ColorPicker.class);
                startActivityForResult(intent, 2);
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==2 && resultCode == RESULT_OK)
        {
            int colorCode = data.getIntExtra("codeColor",0);
            paint.setColor(colorCode);
            iv.setImageBitmap(baseBitmap);
        }
    }

    private void initPaint() {
        paint = new Paint();
        paint.setStrokeWidth(4f);
        paint.setColor(mColors);
    }

    public void clearCanvas(View v) {
        baseBitmap = null;
        iv.setImageBitmap(baseBitmap);
    }

    public void OnSaveClicked(View view) {
        try {
            File file = new File(Environment.getExternalStorageDirectory(), System.currentTimeMillis() + ".jpg");
            OutputStream stream = new FileOutputStream(file);
            baseBitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
            stream.close();
            Toast.makeText(this, "save success", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, "save failed", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
}
