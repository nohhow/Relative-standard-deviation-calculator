package com.nohhow.pb;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    //입력받은 결과값 3개
    double result1 = 0;
    double result2 = 0;
    double result3 = 0;

    //평균
    double avg = 0;
    int avgInt = 0;
    int r1Int = 0;
    int r2Int = 0;
    int r3Int = 0;
    //상대표준편차
    double rsd_result = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button)findViewById(R.id.main_button);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView avgText = (TextView)findViewById(R.id.avg_text);
                TextView rsdText = (TextView)findViewById(R.id.rsd_text);
                TextView avgSet = (TextView)findViewById(R.id.avg_set);
                TextView topText = (TextView)findViewById(R.id.top);
                TextView passText = (TextView)findViewById(R.id.pass);

                EditText r1 = (EditText)findViewById(R.id.resultOne);
                EditText r2 = (EditText)findViewById(R.id.resultTwo);
                EditText r3 = (EditText)findViewById(R.id.resultThree);

                result1 = Double.parseDouble(r1.getText().toString());
                result2 = Double.parseDouble(r2.getText().toString());
                result3 = Double.parseDouble(r3.getText().toString());

                r1Int = (int) Math.round(result1);
                r2Int = (int) Math.round(result2);
                r3Int = (int) Math.round(result3);

                //평균계산
                avg = Math.round((result1 + result2 + result3)/3);
                avgInt = (int) Math.round(avg);

                rsd_result = (100/avg) * Math.sqrt( (Math.pow((result1 - avg), 2) + Math.pow((result2 - avg), 2) + Math.pow((result3 - avg), 2))/2 );


                if((avg < 1342 || avg > 1484) && rsd_result > 2.0){
                    passText.setTextColor(Color.parseColor("#B9062F"));
                    passText.setText("FAIL-평균값 및 표준편차 범위 초과");
                }else if(rsd_result > 2.0){
                    passText.setTextColor(Color.parseColor("#B9062F"));
                    passText.setText("FAIL-표준편차 범위 초과");
                }else if(avg < 1342 || avg > 1484){
                    passText.setTextColor(Color.parseColor("#B9062F"));
                    passText.setText("FAIL-평균값 초과");
                }else{
                    passText.setTextColor(Color.parseColor("#329632"));
                    passText.setText("PASS");
                }
                avgSet.setText(Integer.toString(avgInt));
                topText.setText(Html.fromHtml("[<b>"+r1Int+"</b> - <b>"+avgInt+"</b>]<sup>2</sup> + [<b>"+r2Int+"</b> - <b>"+avgInt+"</b>]<sup>2</sup> + [<b>"+r3Int+"</b> - <b>"+avgInt+"</b>]<sup>2</sup>"));
                avgText.setText(Integer.toString(avgInt));
                rsdText.setText(Double.toString(rsd_result));
//                expText.setText(Html.fromHtml("<sup>100</sup>/<sub><b>"+avgInt+"</b></sub> ✕ [<b>"+r1Int+"</b> - <b>"+avgInt+"</b>]<sup>2</sup> + <sup>[<b>"+r2Int+"</b> - <b>"+avgInt+"</b>]<sup>2</sup> + [<b>"+r3Int+"</b> - <b>"+avgInt+"</b>]<sup>2</sup></sup>/<sub>2</sub>"));

            }
        });
    }

}