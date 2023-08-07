package com.example.filesaver;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private static final String file_name="name.txt";
    Button save,load;
    EditText et;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        save=findViewById(R.id.button);
        load=findViewById(R.id.button2);
        et=findViewById(R.id.editText);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readfiletoexternalprivate();
            }
        });
        load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writefiletoexternalprivate();
                et.getText().clear();
            }
        });



    }
    private void readfiletoexternalprivate(){
        File file=new File(getExternalFilesDir(null),file_name);
        String f=loaded(file);
        et.setText(f);
    }
    private void writefiletoexternalprivate(){
        File file=new File(getExternalFilesDir(null),file_name);
        saved(file);
    }
    public void saved(File file){
        FileOutputStream fileOutputStream=null;
        String text=et.getText().toString();
        try {
            fileOutputStream=new FileOutputStream(file);
            fileOutputStream.write(text.getBytes());
            Toast.makeText(MainActivity.this,"Saved file"+file.getName()+"path"+file.getPath(),Toast.LENGTH_LONG).show();
        } catch(FileNotFoundException e){
            e.printStackTrace();
        } catch(IOException e){
            e.printStackTrace();
        } finally {
            if(fileOutputStream!=null){
                try{
                    fileOutputStream.close();
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
        }
    }
    private String loaded(File file) {
        FileInputStream fileInputStream = null;
        StringBuilder stringBuilder = new StringBuilder();
        try {
            fileInputStream = new FileInputStream(file);
            int text;
// while there is a line in our file append it to our string
            while ((text = fileInputStream.read()) != -1) {
                stringBuilder.append((char) text);
            }
// paste our string to Edit text
            et.setText(stringBuilder.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return stringBuilder.toString();
    }
}