package com.example.theripper.a2048;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by The Ripper on 12/19/2017.
 */

public class TouchEvent {


    private  View.OnTouchListener listener;
    private boolean move=false;
    private float X,Y;

    public View.OnTouchListener Touch(final Context context, final TextView Score, final TextView HS, final SquareAdapter adapter){
        listener=new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        X = motionEvent.getX();
                        Y = motionEvent.getY();
                        Log.d("Down", "onTouch: Dow");
                        move=true;
                        break;
                    case MotionEvent.ACTION_MOVE:
                        if(move){
                            if (Math.abs(motionEvent.getX() - X) >0|| Math.abs(motionEvent.getY() - Y)>0){
                                if(Math.abs(motionEvent.getX()-X)>=150)
                                {

                                    try {
                                        Thread.sleep(100);
                                        if (motionEvent.getX()-X>150){
                                            Datagame.moveright(Datagame.getArrInt(),true);
                                        }
                                        else if(X-motionEvent.getX()>=150){
                                            Datagame.moveleft(Datagame.getArrInt(),true);
                                        }
                                        adapter.notifyDataSetChanged();
                                        Score.setText(String.valueOf(Data.getScore()));
                                        Datagame.Check2();
                                        move=false;

                                        if(Data.getScore()> Integer.valueOf(HS.getText().toString()))
                                        {
                                            SharedPreferences.Editor editor=Data.sharedPreferences.edit();
                                            editor.putInt("HighScore",Data.getScore());
                                            editor.commit();
                                            HS.setText(String.valueOf(Data.sharedPreferences.getInt("HighScore",0)));
                                        }

                                        Datagame.Check2();

                                        if(Data.isGameover()) {
                                            Toast.makeText(context,"Game Over",Toast.LENGTH_LONG).show();

                                        }
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                }
                                else
                                {
                                    if(Math.abs(motionEvent.getY()-Y)>=150)
                                    {
                                        try {
                                            Thread.sleep(100);
                                            if(motionEvent.getY()-Y>150)
                                                Datagame.movedown(Datagame.getArrInt(),true);
                                            else if(Y-motionEvent.getY()>=150)
                                                Datagame.moveup(Datagame.getArrInt(),true);
                                            Log.d("Socre", "onTouch:  " + Data.getScore());

                                            adapter.notifyDataSetChanged();
                                            Score.setText(String.valueOf(Data.getScore()));
                                            move=false;
                                            if(Data.getScore()> Integer.valueOf(HS.getText().toString()))
                                            {
                                                SharedPreferences.Editor editor=Data.sharedPreferences.edit();
                                                editor.putInt("HighScore",Data.getScore());
                                                editor.commit();
                                                HS.setText(String.valueOf(Data.sharedPreferences.getInt("HighScore",0)));
                                            }

                                            Datagame.Check2();
                                            if(Data.isGameover()) {
                                                Toast.makeText(context,"Game Over",Toast.LENGTH_LONG).show();

                                            }
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }


                                    }

                                }
                            }
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        move=true;
                        Log.d("Up", "onTouch: Up");
                        break;

                }

                return false;
            }
        };

        return listener;
    }


}
