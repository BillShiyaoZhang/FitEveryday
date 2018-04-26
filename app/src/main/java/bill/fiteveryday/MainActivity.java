package bill.fiteveryday;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

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

//        Button button_save = (Button) findViewById(R.id.button_save);
//        button_save.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
//                SharedPreferences.Editor editor = sharedPref.edit();
//                editor.putString("push-ups", ((TextView) findViewById(R.id.text_push_ups)).getText().toString());
//                editor.putString("sit-ups", ((TextView) findViewById(R.id.text_sit_ups)).getText().toString());
//                editor.commit();
//            }
//        });

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
    }

    private void setNumberDialog(final TextView text) {
        AlertDialog.Builder customizeDialog =
                new AlertDialog.Builder(MainActivity.this);
        final View dialogView = LayoutInflater.from(MainActivity.this)
                .inflate(R.layout.set_number, null);
        customizeDialog.setTitle("Input number to be added:");
        customizeDialog.setView(dialogView);
        customizeDialog.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    @SuppressLint("ResourceType")
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 获取EditView中的输入内容
                        EditText edit_text =
                                (EditText) dialogView.findViewById(R.id.edit_text);
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
