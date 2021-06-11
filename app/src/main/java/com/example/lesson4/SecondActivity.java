package com.example.lesson4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SecondActivity extends AppCompatActivity {

    EditText et_title, et_description;
    Button btn_add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        et_title = findViewById(R.id.et_title);
        et_description = findViewById(R.id.et_description);
        btn_add = findViewById(R.id.btn_add);

        Intent getData = getIntent();

        String str = getIntent().getStringExtra("title");
        String str1 = getIntent().getStringExtra("description");
        et_title.setText(str);
        et_description.setText(str1);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = et_title.getText().toString().trim();
                String description = et_description.getText().toString().trim();
                if (title.isEmpty()){
                    et_title.setError("Input text");
                    return;
                }
                if (description.isEmpty()){
                    et_description.setError("Input text");
                    return;
                }

                Intent intent = new Intent(SecondActivity.this,MainActivity.class);
                intent.putExtra("title",title);
                intent.putExtra("description",description);
                setResult(RESULT_OK,intent);
                finish();
            }
        });

    }
}