package com.example.theripper.a2048;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by The Ripper on 11/16/2017.
 */

public class Datagame {
  private  static   ArrayList<Integer> arrayList;
   private static int arrInt[][] =new int[4][4];
   private static int[] arraycolor;
    private static boolean[] col =new boolean[4];
   private static int r1=0;
    private static int r2=0;
  private static Random random=new Random();

    public static int[][] getArrInt() {
        return arrInt;
    }
    public ArrayList<Integer> getArrayList() {
        return arrayList;
    }
    private static void setCol(boolean value)
    {
        for(int i=0;i<Data.getRow();i++)
            col[i]=value;
    }

    public void init(Context context){
        Data.Gameover=false;
        Data.Score=0;
        arrayList =new ArrayList<>();

        for (int i=0;i<Data.getRow();i++){

            for(int j=0;j<Data.getCol();j++){
                arrInt[i][j]=0;
            }
        }
        r1=random.nextInt(4);
        r2=random.nextInt(4);
        arrInt[r1][r2]=2;
        do {
            r1=random.nextInt(4);
            r2=random.nextInt(4);

        }while (arrInt[r1][r2]!=0);

        arrInt[r1][r2]=2;


        for(int i=0;i<Data.getRow();i++){
            for (int j=0;j<Data.getCol();j++){
                arrayList.add(arrInt[i][j]);
            }
        }


        TypedArray TA=context.getResources().obtainTypedArray(R.array.colorSquare);

        arraycolor=new int[TA.length()];

        for(int i=0;i<TA.length();i++){
            arraycolor[i]=TA.getColor(i,0);
        }
        TA.recycle();
    }
    public static int colorr(int number){
            if(number==0)
                return Color.rgb(204, 192, 179);
            else return arraycolor[(int) (Math.log(number))];
        }
        private static void Createnumber(int arrInt[][])
        {
            do {
                r1=random.nextInt(Data.getRow());
                r2=random.nextInt(Data.getCol());
            }while (arrInt[r1][r2]!=0);

            arrInt[r1][r2]=2;

        }
        private static void updatearr()
        {
            arrayList.clear();

            for(int i=0;i<Data.getRow();i++){
                for (int j=0;j<Data.getCol();j++){
                    arrayList.add(arrInt[i][j]);
                }
            }
        }

       public static boolean moveleft(int arrInt[][],boolean type)
        {

            boolean left=false;
            for(int i=0;i<Data.getRow();i++)
            {
                setCol(true);
                for (int j=0;j<Data.getCol();j++)
                {
                    if(arrInt[i][j]>0&&j!=0)
                    {
                        int t=j;
                        while (t>0&&arrInt[i][t-1]==0)
                        {
                           int tmp=arrInt[i][t-1];
                           arrInt[i][t-1]=arrInt[i][t];
                           arrInt[i][t]=tmp;
                           left=true;
                            t--;
                        }

                        if(t>0&&arrInt[i][t-1]==arrInt[i][t]&&arrInt[i][t]!=0&&col[t-1])
                        {
                          if(type) Data.Score+=arrInt[i][t-1];
                            arrInt[i][t-1]+=arrInt[i][t];
                            arrInt[i][t]=0;
                            col[t-1]=false;
                            left=true;
                        }
                    }

                }
            }

          if(left)
          {
                  Createnumber(arrInt);
                  updatearr();


          }
          else return false;

            return true;


        }

        public static boolean moveright(int arrInt[][],boolean type)
        {

            boolean right=false;
            for (int i=0;i<Data.getRow();i++)
            {
                setCol(true);
                for (int j=2;j>=0;j--)
                {
                    if(arrInt[i][j]>0&&j!=3)
                    {
                        int t=j;
                        while (t<3&&arrInt[i][t+1]==0)
                        {
                            int tmp=arrInt[i][t+1];
                            arrInt[i][t+1]=arrInt[i][t];
                            arrInt[i][t]=tmp;
                            right=true;
                            t++;
                        }


                        if(t<3&&arrInt[i][t+1]==arrInt[i][t]&&arrInt[i][t]!=0&&col[t+1])
                        {
                           if(type) Data.Score+=arrInt[i][t+1];
                            arrInt[i][t+1]+=arrInt[i][t];
                            arrInt[i][t]=0;
                            col[t+1]=false;
                            right=true;
                        }
                    }
                }
            }
                if(right)
                {


                       Createnumber(arrInt);
                       updatearr();

                }
                else return false;

            return  true;
        }


        public  static boolean moveup(int arrInt[][],boolean type)
        {

            boolean up=false;
            for (int i=0;i<Data.getRow();i++)
            {
                setCol(true);
                for (int j=1;j<Data.getCol();j++)
                {
                    if(arrInt[j][i]>0 &&j!=0)
                    {
                            int t=j;
                            while (t>0&&arrInt[t-1][i]==0)
                            {
                                int tmp=arrInt[t-1][i];
                                arrInt[t-1][i]=arrInt[t][i];
                                arrInt[t][i]=tmp;
                                up=true;
                                t--;
                            }

                            if(t>0&&arrInt[t-1][i]==arrInt[t][i]&&arrInt[t][i]!=0&&col[t-1])
                            {
                               if(type) Data.Score=+arrInt[t-1][i];
                                arrInt[t-1][i]+=arrInt[t][i];
                                arrInt[t][i]=0;
                                col[t-1]=false;
                                up=true;
                            }
                    }
                }
            }
         if(up)
         {


                 Createnumber(arrInt);
                 updatearr();

         }
         else return false;

            return true;
        }

        public static boolean movedown(int arrInt[][],boolean type)
        {

            boolean down=false;
            for (int i=0;i<Data.getRow();i++)
            {
                setCol(true);
                for (int j=2;j>=0;j--)
                {
                    if(arrInt[j][i]>0&&j!=3)
                    {
                        int t=j;
                        while (t<3&&arrInt[t+1][i]==0)
                        {
                            int tmp=arrInt[t+1][i];
                            arrInt[t+1][i]=arrInt[t][i];
                            arrInt[t][i]=tmp;
                            down=true;
                            t++;
                        }


                        if(t<3&&arrInt[t+1][i]==arrInt[t][i]&&arrInt[t][i]!=0&&col[t+1])
                        {
                            if(type) Data.Score+=arrInt[t+1][i];

                            arrInt[t+1][i]+=arrInt[t][i];
                            arrInt[t][i]=0;
                            col[t+1]=false;
                            down=true;
                        }
                    }
                }
            }
           if(down)
           {


                   Createnumber(arrInt);
                   updatearr();

           }
           else return false;

            return true;
        }
        private static int[][] GetArr(int arr[][])
        {
            arr=new int[4][4];
            for(int i=0;i<4;i++)
            {
                for(int j=0;j<4;j++)
                {
                    arr[i][j]=arrInt[i][j];
                }
            }
            return arr;
        }

       public static void Check2()
        {
            int arr[][]=new int[4][4];

            if(!movedown(GetArr(arr),false)&&!moveleft(GetArr(arr),false)&&!moveright(GetArr(arr),false)&&!moveup(GetArr(arr),false)) Data.Gameover=true;

        }









}
