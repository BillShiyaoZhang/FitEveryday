package bill.fiteveryday;

import android.app.AlertDialog;
import android.content.DialogInterface;
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

        Button button_push_ups = (Button) findViewById(R.id.button_push_ups);
        Button button_sit_ups = (Button) findViewById(R.id.button_sit_ups);
        button_push_ups.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setNumberDialog((TextView) findViewById(R.id.text_push_ups));
            }
        });
        button_sit_ups.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                setNumberDialog((TextView) findViewById(R.id.text_sit_ups));
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
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 获取EditView中的输入内容
                        EditText edit_text =
                                (EditText) dialogView.findViewById(R.id.edit_text);
                        text.setText(edit_text.getText().toString());
                    }
                });
        customizeDialog.show();
    }
}
