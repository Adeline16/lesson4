package com.example.lesson4;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TaskAdapter taskAdapter;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.rv_task);
        taskAdapter = new TaskAdapter(this,MainActivity.this);
        recyclerView.setAdapter(taskAdapter);
        fab = findViewById(R.id.fabb);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,SecondActivity.class);
                startActivityForResult(intent,100);
            }
        });





        taskAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onClickItem(int position) {
                Intent intent = new Intent(MainActivity.this,SecondActivity.class);
                intent.putExtra("title",taskAdapter.list.get(position).getTitle());
                intent.putExtra("description",taskAdapter.list.get(position).getDescription());
                intent.putExtra("pos",position);
                startActivityForResult(intent,1);
         //       startActivity(intent);


            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == RESULT_OK){
            if (data != null){
                TaskModel taskModel = new TaskModel(data.getStringExtra("title"),data.getStringExtra("description"));
                taskAdapter.addData(taskModel);
            }

        }
        if ( requestCode ==1 && resultCode == RESULT_OK){
                TaskModel model = new TaskModel(data.getStringExtra("title"), data.getStringExtra("description"));
                int pos = data.getIntExtra("pos",0);
                taskAdapter.updateTask(model,pos);
            Log.e("TAG", "getIntent: " + data);

        }
        }

}