package com.example.minesweeper;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;

public class BlockButton extends androidx.appcompat.widget.AppCompatButton {
    static public int flags=10; // 총 깃발개수 == 폭탄개수
    static public int blocks=81; //총 블록개수
    static public int choose=0;
    public int x;
    public int y;
    public boolean mine=false;
    public boolean flag=false;
    public int neighborMines=0;
    Drawable img=getContext().getResources().getDrawable( R.drawable.mine );
    public BlockButton(Context context, int x, int y){
        super(context);
        this.x=x;
        this.y=y;
        mine=false;
        flag=false;
        /*TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(
                TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT,
                1.0f);*/
        /*this.setLayoutParams(layoutParams);*/


    }

    public void toggleFlag(BlockButton[][] buttons, int x, int y){
                if(choose==1) {
                    if (flag) {
                        flag = false;
                        buttons[x][y].setText("");
                        return;
                    } else {
                        flag = true;
                        buttons[x][y].setText("\uD83C\uDFF4");
                        return;
                    }
                }else{
                    return;
                }

    }

    public boolean breakBlock(BlockButton[][] buttons, int x, int y) {


        int cnt; // 주변 지뢰의 개수, cnt가 0이면 그냥 아무 표시없고 0이상일때만 숫자 표시해주면 될듯
        if (!buttons[x][y].isEnabled()) return false;
        if (buttons[x][y].mine) //지뢰일경우
        {
            img.setBounds(0,0,60,60);
            buttons[x][y].setCompoundDrawables(img,null,null,null);
//            buttons[x][y].setBackgroundColor(Color.RED);
            return true;
        }
        cnt = getMineNumber(buttons, x, y);
        buttons[x][y].setEnabled(false); //버튼 비활성화
        buttons[x][y].setText(String.valueOf(cnt)); // 버튼 밑 숫자표기



        if (cnt > 0) //지뢰와의 경계선
        {

            return false;
        }
        if(x<=0 || x>=8 || y<=0 || y>=8) return false;


        if(!buttons[x-1][y+1].mine) breakBlock(buttons, x-1, y+1);
        if(!buttons[x-1][y].mine) breakBlock(buttons, x-1, y);
        if(!buttons[x-1][y-1].mine) breakBlock(buttons, x-1, y-1);

        if(!buttons[x][y+1].mine) breakBlock(buttons, x, y+1);
        if(!buttons[x][y-1].mine) breakBlock(buttons, x, y-1);

        if(!buttons[x+1][y+1].mine) breakBlock(buttons, x+1, y+1);
        if(!buttons[x+1][y].mine) breakBlock(buttons, x+1, y);
        if(!buttons[x+1][y-1].mine) breakBlock(buttons, x+1, y-1);


        return false;
    }



    private int getMineNumber(BlockButton[][] buttons, int row, int col){ // 주변 8칸의 지뢰 개수 반환
        int mineCnt = 0;
        //row,column이 경계값 넘지 않도록 조정
        if(row-1>=0) {
            if(col-1>=0) {
                if (buttons[row - 1][col - 1].mine) mineCnt++;
            }
            if (buttons[row - 1][col].mine) mineCnt++;
            if(col+1<=8) {
                if (buttons[row - 1][col + 1].mine) mineCnt++;
            }
        }

        if(col-1>=0) {
            if (buttons[row][col - 1].mine) mineCnt++;
        }
        if(col+1<=8){
            if(buttons[row][ col+1].mine)mineCnt++;
        }

        if(row+1<=8) {
            if(col-1>=0) {
                if (buttons[row + 1][col - 1].mine) mineCnt++;
            }
            if (buttons[row + 1][col].mine) mineCnt++;
            if(col+1<=8){
                if (buttons[row + 1][col + 1].mine) mineCnt++;
            }
        }

        return mineCnt;
    }

    @Override
    public void setLayoutParams(ViewGroup.LayoutParams params) {
        super.setLayoutParams(params);
    }
}
