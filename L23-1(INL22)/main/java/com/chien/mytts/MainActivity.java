package com.chien.mytts;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    TextToSpeech tts;
    EditText input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        input = findViewById(R.id.input);
        tts = new TextToSpeech(this,
                new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int status) {
                        if(status == TextToSpeech.SUCCESS){
                            int language = tts.setLanguage(Locale.ENGLISH);
                            if(language == TextToSpeech.LANG_MISSING_DATA
                                    || language == TextToSpeech.LANG_NOT_SUPPORTED){
                                Toast.makeText(MainActivity.this, "不支援", Toast.LENGTH_SHORT).show();
                            }else{
                                tts.setPitch(1);   //語調
                                tts.setSpeechRate(1);  //語速
                            }
                        }
                    }
                });

    }

    public void onClick(View view) {
        String aa = input.getText().toString().trim();
        tts.speak(aa, TextToSpeech.QUEUE_FLUSH, null);  //排程
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        if(tts != null){
            tts.stop(); //停止語音
            tts.shutdown(); //停止目前動作並關閉
        }
    }
}