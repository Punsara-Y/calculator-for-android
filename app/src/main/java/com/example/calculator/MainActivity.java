package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView resultTv,solutionTv;
    MaterialButton buttonC,buttonbracketopen,buttonbracketclose;
    MaterialButton buttondivide,buttonmultiply,buttonsum,buttonminus,buttonequals;
    MaterialButton button0,button1,button2,button3,button4,button5,button6,button7,button8,button9;
    MaterialButton buttonAC,buttonDot;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultTv = findViewById(R.id.result_tv);
        solutionTv = findViewById(R.id.solution_tv);

        assignbuttonID(button0,R.id.button_0);
        assignbuttonID(button1,R.id.button_1);
        assignbuttonID(button2,R.id.button_2);
        assignbuttonID(button3,R.id.button_3);
        assignbuttonID(button4,R.id.button_4);
        assignbuttonID(button5,R.id.button_5);
        assignbuttonID(button6,R.id.button_6);
        assignbuttonID(button7,R.id.button_7);
        assignbuttonID(button8,R.id.button_8);
        assignbuttonID(button9,R.id.button_9);
        assignbuttonID(buttonC,R.id.button_c);
        assignbuttonID(buttonbracketopen,R.id.button_open_bracket);
        assignbuttonID(buttonbracketclose,R.id.button_Closed_bracket);
        assignbuttonID(buttondivide,R.id.button_divide);
        assignbuttonID(buttonmultiply,R.id.button_multiply);
        assignbuttonID(buttonsum,R.id.button_sum);
        assignbuttonID(buttonminus,R.id.button_reduce);
        assignbuttonID(buttonequals,R.id.button_equals);
        assignbuttonID(buttonAC,R.id.button_ac);
        assignbuttonID(buttonDot,R.id.button_dot);

    }

    void assignbuttonID(MaterialButton btn,int id)
    {
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        MaterialButton button = (MaterialButton) view;
        String buttontext = button.getText().toString();
        String datatocalculate = solutionTv.getText().toString();

        if (buttontext.equals("AC"))
        {
            solutionTv.setText("");
            resultTv.setText("0");
            return;
        }
        if (buttontext.equals("="))
        {
            solutionTv.setText(resultTv.getText());
            return;
        }
        if (buttontext.equals("DEL"))
        {
            datatocalculate = datatocalculate.substring(0,datatocalculate.length()-1);

        }else
        {
            datatocalculate=datatocalculate+buttontext;
        }

        solutionTv.setText(datatocalculate);

        String finalresult = getresult(datatocalculate);

        if (!finalresult.equals("Error"))
        {
            resultTv.setText(finalresult);
        }

    }

    String getresult(String data)
    {
        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String final_result = context.evaluateString(scriptable,data,"Javascript",1,null).toString();
            if (final_result.endsWith(".0"))
            {
                final_result = final_result.replace(".0","");
            }
            return final_result;
        }catch (Exception e)
        {
            return "Error";
        }
    }
}