package bill.fiteveryday;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        String push_ups = sharedPref.getString("push-ups", "0");
        String sit_ups = sharedPref.getString("sit-ups", "0");

        ((TextView) findViewById(R.id.text_push_ups)).setText(push_ups);
        ((TextView) findViewById(R.id.text_sit_ups)).setText(sit_ups);

        Button button_push_ups = (Button) findViewById(R.id.button_push_ups);
        button_push_ups.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setNumberDialog((TextView) findViewById(R.id.text_push_ups));
            }
        });

        Button button_sit_ups = (Button) findViewById(R.id.button_sit_ups);
        button_sit_ups.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setNumberDialog((TextView) findViewById(R.id.text_sit_ups));
            }
        });

        Button button_clear = (Button) findViewById(R.id.button_clear);
        button_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("push-ups", "0");
                editor.putString("sit-ups", "0");
                editor.commit();
                ((TextView) findViewById(R.id.text_push_ups)).setText("0");
                ((TextView) findViewById(R.id.text_sit_ups)).setText("0");
            }
        });

        Button button_history = (Button) findViewById(R.id.button_history);
        button_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, HistoryActivity.class);
                startActivity(intent);
            }
        });

//        FloatingActionButton button_add = (FloatingActionButton) findViewById(R.id.button_add);
////        button_add.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View view) {
////                AlertDialog.Builder customizeDialog =
////                        new AlertDialog.Builder(MainActivity.this);
////                final View dialogView = LayoutInflater.from(MainActivity.this)
////                        .inflate(R.layout.pop_up_edit_text, null);
////                customizeDialog.setTitle("Name of the button:");
////                customizeDialog.setView(dialogView);
////                customizeDialog.setPositiveButton("OK",
////                        new DialogInterface.OnClickListener() {
////                            @Override
////                            public void onClick(DialogInterface dialog, int which) {
////                                String name = ((EditText) dialogView.findViewById(R.id.edit_text)).getText().toString();
////                                Button newButton = new Button(MainActivity.this);
////                                newButton.setId(R.id.newButton);
////                                float density = MainActivity.this.getResources().getDisplayMetrics().density;
////                                newButton.setText(name);
////                                newButton.setTextSize(8 * density);
////                                newButton.setMinWidth(0);
////                                newButton.setMinimumWidth(0);
////                                newButton.setWidth((int) (174 * density));
////                                newButton.setMinHeight(0);
////                                newButton.setMinimumHeight(0);
////                                newButton.setHeight((int) (133 * density));
//////                                ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) newButton.getLayoutParams();
//////                                params.topToBottom = R.id.button_sit_ups;
////                                ConstraintSet constraintSet = new ConstraintSet();
//////                                constraintSet.clone(MainActivity.this,R.layout.activity_main);
////                                constraintSet.connect(newButton.getId(), ConstraintSet.TOP, R.id.button_sit_ups, ConstraintSet.BOTTOM, (int) (8 * density));
////                                constraintSet.connect(newButton.getId(), ConstraintSet.LEFT, R.id.activity_main, ConstraintSet.LEFT, (int) (8 * density));
////                                ConstraintLayout cl = (ConstraintLayout) findViewById(R.id.activity_main);
////                                cl.addView(newButton);
////                                constraintSet.applyTo(cl);
////                            }
////                        });
////                customizeDialog.show();
////            }
////        });
    }

    private void setNumberDialog(final TextView text) {
        AlertDialog.Builder customizeDialog =
                new AlertDialog.Builder(MainActivity.this);
        final View dialogView = LayoutInflater.from(MainActivity.this)
                .inflate(R.layout.pop_up_edit_text, null);
        customizeDialog.setTitle("Number to be added:");
        customizeDialog.setView(dialogView);
        customizeDialog.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    @SuppressLint("ResourceType")
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 获取EditView中的输入内容
                        EditText edit_text = (EditText) dialogView.findViewById(R.id.edit_text);
                        edit_text.setInputType(EditorInfo.TYPE_CLASS_PHONE);
                        int i = -1;
                        try {
                            i = Integer.valueOf(text.getText().toString());
                        } catch (NumberFormatException e) {
                            Toast.makeText(MainActivity.this,
                                    e.getMessage(),
                                    Toast.LENGTH_SHORT).show();
                        } catch (Exception e1) {
                            Toast.makeText(MainActivity.this,
                                    "Exception text",
                                    Toast.LENGTH_SHORT).show();
                        }
                        try {
                            i = i + Integer.valueOf(edit_text.getText().toString());
                        } catch (NumberFormatException e) {
                            Toast.makeText(MainActivity.this,
                                    "You could only type numbers!",
                                    Toast.LENGTH_SHORT).show();
                        } catch (Exception e1) {
                            Toast.makeText(MainActivity.this,
                                    "Exception edit_text",
                                    Toast.LENGTH_SHORT).show();
                        }
                        text.setText(String.valueOf(i));
                        SharedPreferences.Editor editor = getPreferences(Context.MODE_PRIVATE).edit();
                        String target = "";
                        switch (text.getId()) {
                            case R.id.text_push_ups:
                                target = "push-ups";
                                break;
                            case R.id.text_sit_ups:
                                target = "sit-ups";
                                break;
                        }
                        editor.putString(target, String.valueOf(i));
                        editor.commit();
                    }
                });
        customizeDialog.show();
    }

}
