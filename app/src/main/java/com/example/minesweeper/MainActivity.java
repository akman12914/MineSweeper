package com.example.minesweeper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
//    public ImageView iv = new ImageView(this);
//    public static Context context_main;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TableLayout tableLayout = (TableLayout) findViewById(R.id.table);
        tableLayout.setShrinkAllColumns(true);
        BlockButton[][] buttons = new BlockButton[9][9];
        TextView test;
        test = (TextView) findViewById(R.id.textView2);
        final int[] minenum = {10};
        ToggleButton toggleButton;
        toggleButton = (ToggleButton) findViewById(R.id.toggleButton);
        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    BlockButton.choose = 1;
                }else{
                    BlockButton.choose = 0;
                }
            }
        });
        for (int i = 0; i < 9; i++) {
            final TableRow tableRow = new TableRow(this);
            for (int j = 0; j < 9; j++) {
                buttons[i][j]= new BlockButton(this,i,j);
                buttons[i][j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        BlockButton b = (BlockButton) v.findViewById(v.getId());
                        ((BlockButton) v).breakBlock(buttons, ((BlockButton) v).x, ((BlockButton) v).y);
                        //((BlockButton) v).toggleFlag(buttons, ((BlockButton) v).x, ((BlockButton) v).y);
                        if(((BlockButton) v).toggleFlag(buttons, ((BlockButton) v).x, ((BlockButton) v).y)){
                            minenum[0]--;
                            test.setText(String.valueOf(minenum[0]));
                            if(minenum[0]==0){
                                minenum[0]++;
                            }
                        }else{
                            minenum[0]++;
                            test.setText(String.valueOf(minenum[0]));
                        }
                    }
                });
                buttons[i][j].setLayoutParams
                        (new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT,1.0f));
                tableRow.addView(buttons[i][j]);

            }
            tableLayout.addView(tableRow);
        }

//        iv.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
//        iv.setImageResource(R.drawable.mine);

        Random random = new Random();
        Integer[] random_x=new Integer[10];
        Integer[] random_y=new Integer[10];

        for (int i = 0; i < 10; i++) {
            random_x[i]=random.nextInt(9);
            random_y[i]=random.nextInt(9);
            for(int j=0; j<i; j++){
                if(random_x[i]==random_x[j] && random_y[i]==random_y[j]){
                    i--;
                    continue;
                }
            }
            buttons[random_x[i]][random_y[i]].mine=true;
        }
    }




}