package com.itproger.ninthprak;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextInputEditText fileName = findViewById(R.id.fileName);
        TextInputEditText fileContent = findViewById(R.id.fileContent);

        Button saveButton = findViewById(R.id.saveButton);
        Button readButton = findViewById(R.id.readButton);
        Button deleteButton = findViewById(R.id.deleteButton);
        Button putButton = findViewById(R.id.addButton);

        TextView fileContentField = findViewById(R.id.fileContentField);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fileName.getText().toString().matches("")) {
                    Toast.makeText(getApplicationContext(), "Введите название файла", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (fileContent.getText().toString().matches("")) {
                    Toast.makeText(getApplicationContext(), "Введите содержимое", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (fileName.getText().toString().matches("") && fileContent.getText().toString().matches("")) {
                    Toast.makeText(getApplicationContext(), "Введите информацию", Toast.LENGTH_SHORT).show();
                    return;
                }

                File storageDir =
                        Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);

                if (!storageDir.exists()) {
                    if (!storageDir.mkdirs()) {
                        Toast.makeText(getApplicationContext(), "Не удалось создать директорию", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }

                File file = new File(storageDir, fileName.getText().toString());
                try {
                    if (!file.exists()) {
                        boolean created = file.createNewFile();
                        if (created) {
                            FileWriter writer = new FileWriter(file);
                            writer.append(fileContent.getText().toString());
                            writer.flush();
                            writer.close();
                        }
                    } else {
                        FileWriter writer = new FileWriter(file);
                        writer.append(fileContent.getText().toString());
                        writer.flush();
                        writer.close();
                    }

                    Toast.makeText(getApplicationContext(), "Сохранено", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}