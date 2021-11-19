package com.example.minesweeper;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;

public class gameoverDialog extends Dialog {
    private Context context;
    private CustomDialogClickListener customDialogClickListener;
    private TextView dialogTitle, quitGame, cancelGame;
    public gameoverDialog(@NonNull Context context, CustomDialogClickListener customDialogClickListener){
        super(context);
        this.context = context;
        this.customDialogClickListener= customDialogClickListener;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gameover_dialog);
        dialogTitle = findViewById(R.id.dialog_title);
        quitGame = findViewById(R.id.dialog_button_quit);
        cancelGame = findViewById(R.id.dialog_button_cancel);
        quitGame.setOnClickListener(v -> {
                this.customDialogClickListener.onOverClick();
                dismiss();
                ((MainActivity)context).finish();
        });
        cancelGame.setOnClickListener(v -> {
            this.customDialogClickListener.onCancelClick();
            dismiss();
            ((MainActivity)context).finish();
            ((MainActivity)context).startActivity(new Intent(context,MainActivity.class));
        });

    }


}
