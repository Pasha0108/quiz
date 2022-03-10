package space.chistov.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class Level1 extends AppCompatActivity {

    Dialog dialog;
    Dialog dialogEnd;

    public int numLeft;
    public int numRight;
    public int count=0;
    Array array = new Array();
    Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.universal);

        TextView text_levels = findViewById(R.id.text_levels);
        text_levels.setText(R.string.level1);

        final ImageView img_left = (ImageView)findViewById(R.id.img_left);
        img_left.setClipToOutline(true);//скруглаем углы img_left

        final ImageView img_right = (ImageView)findViewById(R.id.img_right);
        img_right.setClipToOutline(true); //скруглаем углы img_right

        final TextView text_left = findViewById(R.id.text_left);//левая TextView
        final TextView text_right = findViewById(R.id.text_right);//правая TextView

        //Развернуть игру на весь экран = начало
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        //Развернуть игру на весь экран - конец
        //вызов диалогового окна в начале игры
        dialog = new Dialog(this);//создаем новое дилоговое окно
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);//скрываем заголовок
        dialog.setContentView(R.layout.previewdialog); //путь к макету дилогового окна
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); //прозрачный фон диологового окна
        dialog.setCancelable(false); //запрещаем закрывать окно кнопокой "назад"

        //кнопка, закрывающая диалоговое окно
        TextView btnclose = (TextView)dialog.findViewById(R.id.btnclose);
        btnclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //обрабатываем нажатие кнопки
                try {
                    Intent intent = new Intent(Level1.this, GameLevels.class);
                    startActivity(intent); finish();
                }catch(Exception e){

                }
                dialog.dismiss();
            }
        });

        //кнопка продолжить
        Button btncontinue = (Button)dialog.findViewById(R.id.btncontinue);
        btncontinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();//показать диплоговое окно

        //_________________________________________
        //вызов диалогового окна в конце игры
        dialogEnd = new Dialog(this);//создаем новое дилоговое окно
        dialogEnd.requestWindowFeature(Window.FEATURE_NO_TITLE);//скрываем заголовок
        dialogEnd.setContentView(R.layout.dialogend); //путь к макету дилогового окна
        dialogEnd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); //прозрачный фон диологового окна
        dialogEnd.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        dialogEnd.setCancelable(false); //запрещаем закрывать окно кнопокой "назад"

        //кнопка, закрывающая диалоговое окно
        TextView btnclose2 = (TextView)dialogEnd.findViewById(R.id.btnclose);
        btnclose2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //обрабатываем нажатие кнопки
                try {
                    Intent intent = new Intent(Level1.this, GameLevels.class);
                    startActivity(intent); finish();
                }catch(Exception e){

                }
                dialogEnd.dismiss();
            }
        });

        //кнопка продолжить
        Button btncontinue2 = (Button)dialogEnd.findViewById(R.id.btncontinue);
        btncontinue2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    Intent intent = new Intent(Level1.this, Level2.class);
                    startActivity(intent); finish();
                }catch(Exception e){

                }

                dialogEnd.dismiss();
            }
        });
        //_________________________________________

        //кнопка назад
        Button btn_back = (Button)findViewById(R.id.button_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent(Level1.this, GameLevels.class);
                    startActivity(intent); finish();
                }catch (Exception e){

                }
            }
        });

        final int[] progress = {
            R.id.point1, R.id.point2, R.id.point3, R.id.point4,
            R.id.point5, R.id.point6, R.id.point7, R.id.point8,
            R.id.point9, R.id.point10, R.id.point11, R.id.point12,
            R.id.point13, R.id.point14, R.id.point15, R.id.point16,
            R.id.point17, R.id.point18, R.id.point19, R.id.point20,
        };

        //подключаем анимацию
        final Animation a = AnimationUtils.loadAnimation(Level1.this, R.anim.alpha);

        numLeft = random.nextInt(10);//random number
        img_left.setImageResource(array.images1[numLeft]);//достаем из массива картинку
        text_left.setText(array.texts1[numLeft]);//достаем из массива текст
        numRight = random.nextInt(10);//генерируем случайное число
        //цикл, проверяющий, чтобы numLeft и numRight не совпадали
        while (numRight == numLeft){
            numRight = random.nextInt(10);
        }

        img_right.setImageResource(array.images1[numRight]);//достаем из массива картинку
        text_right.setText(array.texts1[numRight]);//достаем из массива текст

        //обрабатываем нажатие на левую картинку
        img_left.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //условие касания картинки
                if(event.getAction()==MotionEvent.ACTION_DOWN){
                    //если каснулся экрана пальцем
                    img_right.setEnabled(false);//блокируем правую картинку
                    if(numLeft>numRight){
                        img_left.setImageResource(R.drawable.img_true);
                    }else{
                        img_left.setImageResource(R.drawable.img_false);
                    }
                }else if(event.getAction()==MotionEvent.ACTION_UP) {
                    //когда отпустил палец
                    //если левая картинка больше
                    if (numLeft > numRight) {
                        if (count < 20) {
                            count = count + 1;
                        }
                        //закрашиваем прогресс серым цветом
                        for (int i = 0; i < 20; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }
                        //определяем правильные ответы и закрашиваем зеленым
                        for (int i = 0; i < count; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }
                    } else {
                        if (count > 0) {
                            if (count == 1) {
                                count = 0;
                            } else {
                                count = count - 2;
                            }
                        }
                        //закрашиваем прогресс серым цветом
                        for (int i = 0; i < 19; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }
                        //определяем правильные ответиты и закрашиваем зеленым
                        for (int i = 0; i < count; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }
                    }
                    if(count==20){
                        SharedPreferences save = getSharedPreferences("Save", MODE_PRIVATE);
                        final int level = save.getInt("Level", 1);
                        if (level>1){

                        }else{
                            SharedPreferences.Editor editor = save.edit();
                            editor.putInt("Level",2);
                            editor.commit();
                        }
                        dialogEnd.show();
                    }else{
                        numLeft = random.nextInt(10);//random number
                        img_left.setImageResource(array.images1[numLeft]);//достаем из массива картинку
                        text_left.setText(array.texts1[numLeft]);//достаем из массива текст
                        numRight = random.nextInt(10);//генерируем случайное число
                        //цикл, проверяющий, чтобы numLeft и numRight не совпадали
                        while (numRight == numLeft){
                            numRight = random.nextInt(10);
                        }

                        img_right.setImageResource(array.images1[numRight]);//достаем из массива картинку
                        img_right.startAnimation(a);
                        text_right.setText(array.texts1[numRight]);//достаем из массива текст

                        img_right.setEnabled(true);
                    }
                }
                return true;
            }
        });

        //обрабатываем нажатие на правую картинку
        img_right.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //условие касания картинки
                if(event.getAction()==MotionEvent.ACTION_DOWN){
                    //если каснулся экрана пальцем
                    img_left.setEnabled(false);//блокируем левую картинку
                    if(numLeft<numRight){
                        img_right.setImageResource(R.drawable.img_true);
                    }else{
                        img_right.setImageResource(R.drawable.img_false);
                    }
                }else if(event.getAction()==MotionEvent.ACTION_UP) {
                    //когда отпустил палец
                    //если правая картинка больше
                    if (numLeft < numRight) {
                        if (count < 20) {
                            count = count + 1;
                        }
                        //закрашиваем прогресс серым цветом
                        for (int i = 0; i < 20; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }
                        //определяем правильные ответы и закрашиваем зеленым
                        for (int i = 0; i < count; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }
                    } else {
                        //если правая картинка меньше
                        if (count > 0) {
                            if (count == 1) {
                                count = 0;
                            } else {
                                count = count - 2;
                            }
                        }
                        //закрашиваем прогресс серым цветом
                        for (int i = 0; i < 19; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }
                        //определяем правильные ответиты и закрашиваем зеленым
                        for (int i = 0; i < count; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }
                    }
                    if(count==20){
                        //выход из уровня
                        SharedPreferences save = getSharedPreferences("Save", MODE_PRIVATE);
                        final int level = save.getInt("Level", 1);
                        if (level>1){

                        }else{
                            SharedPreferences.Editor editor = save.edit();
                            editor.putInt("Level",2);
                            editor.commit();
                        }
                        dialogEnd.show();
                    }else{
                        numLeft = random.nextInt(10);//random number
                        img_left.setImageResource(array.images1[numLeft]);//достаем из массива картинку
                        text_left.setText(array.texts1[numLeft]);//достаем из массива текст
                        numRight = random.nextInt(10);//генерируем случайное число
                        //цикл, проверяющий, чтобы numLeft и numRight не совпадали
                        while (numRight == numLeft){
                            numRight = random.nextInt(10);
                        }

                        img_right.setImageResource(array.images1[numRight]);//достаем из массива картинку
                        img_right.startAnimation(a);
                        text_right.setText(array.texts1[numRight]);//достаем из массива текст

                        img_left.setEnabled(true);
                    }
                }
                return true;
            }
        });

    }

    //системная кнопка назад
    @Override
    public void onBackPressed(){
        try {
            Intent intent = new Intent(Level1.this, GameLevels.class);
            startActivity(intent); finish();
        }catch (Exception e){

        }
    }


}