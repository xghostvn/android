package com.example.theripper.a2048;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LayoutAnimationController;
import android.view.animation.TranslateAnimation;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

   private GridView gridView;
   private TextView HS;
   private TextView Score;
   private Datagame datagame=new Datagame();
    private SquareAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        TouchEvent touchEvent=new TouchEvent();

        gridView.setOnTouchListener( touchEvent.Touch(getApplicationContext(),Score,HS,adapter));


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menuitem,menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.restart:
                showDialog();
                break;
        }
        return super.onOptionsItemSelected(item);
    }



    private void showDialog()
    {
        AlertDialog.Builder alBuilder=new AlertDialog.Builder(this);
        alBuilder.setTitle("New Game");
        alBuilder.setMessage("Do you want to start a new game ?");
        alBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                Toast.makeText(getApplicationContext(),"New Game",Toast.LENGTH_LONG).show();
                datagame.init(getApplicationContext());
                adapter.notifyDataSetChanged();
                Score.setText(String.valueOf(Data.getScore()));
                init();

            }
        });


        alBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        alBuilder.show();
    }


    private void Animation(){
        AnimationSet set = new AnimationSet(true);

        Animation animation = new AlphaAnimation(0.0f, 1.0f);
        animation.setDuration(500);
        set.addAnimation(animation);

        animation = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0.0f,Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, -1.0f,Animation.RELATIVE_TO_SELF, 0.0f
        );
        animation.setDuration(500);
        set.addAnimation(animation);

        LayoutAnimationController controller =
                new LayoutAnimationController(set, 0.5f);



        gridView.setLayoutAnimation(controller);
    }


    private void GetView(){
        gridView=findViewById(R.id.GVplay);
        datagame.init(getApplicationContext());
        HS=findViewById(R.id.HS);
        Score=findViewById(R.id.Score);


    }

    private void init(){

        GetView();

        Data.sharedPreferences = getSharedPreferences("High Score" ,MODE_PRIVATE);
        HS.setText(String.valueOf(Data.sharedPreferences.getInt("HighScore",0)));
        Score.setText(String.valueOf(Data.getScore()));
        adapter=new SquareAdapter(getApplicationContext(),R.layout.layout_text,datagame.getArrayList());
        gridView.setAdapter(adapter);

        Animation();


    }

}
