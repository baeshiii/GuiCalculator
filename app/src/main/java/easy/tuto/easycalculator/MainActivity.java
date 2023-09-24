package easy.tuto.easycalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView resultTv,solutionTv;
    MaterialButton buttonC,buttonBrackOpen,buttonBrackClose;
    MaterialButton buttonPlus,buttonMultiply,buttonDivide,buttonMinus,buttonEquals;
    MaterialButton button0,button1,button2,button3,button4,button5,button6,button7,button8,button9;
    MaterialButton buttonAC,buttonDot;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       resultTv = findViewById(R.id.result_tv);
       solutionTv = findViewById(R.id.solution_tv);

       assignId(buttonC,R.id.button_ce);
       assignId(buttonBrackOpen,R.id.button_open_bracket);
       assignId(buttonBrackClose,R.id.button_close_bracket);
       assignId(buttonPlus,R.id.button_plus);
       assignId(buttonMultiply,R.id.button_multiply);
       assignId(buttonDivide,R.id.button_divide);
       assignId(buttonMinus,R.id.button_minus);
       assignId(buttonEquals,R.id.button_equals);
       assignId(button0,R.id.button_0);
       assignId(button1,R.id.button_1);
       assignId(button2,R.id.button_2);
       assignId(button3,R.id.button_3);
       assignId(button4,R.id.button_4);
       assignId(button5,R.id.button_5);
       assignId(button6,R.id.button_6);
       assignId(button7,R.id.button_7);
       assignId(button8,R.id.button_8);
       assignId(button9,R.id.button_9);
       assignId(buttonAC,R.id.button_c);
       assignId(buttonDot,R.id.button_dot);





    }

    void assignId(MaterialButton btn,int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        MaterialButton button = (MaterialButton) view;
        String buttonText = button.getText().toString();
        String dataToCalculate = solutionTv.getText().toString();

        if (buttonText.equals("C")) {
            solutionTv.setText("");
            resultTv.setText("0");
        } else if (buttonText.equals("=")) {
            String finalResult = getResult(dataToCalculate);
            if (!finalResult.equals("Error")) {
                solutionTv.setText(finalResult);
                resultTv.setText(finalResult);
            } else {
                // Handle the error condition here
                solutionTv.setText("Error");
                resultTv.setText("Error");
            }
        } else if (buttonText.equals("CE")) {
            if (!dataToCalculate.isEmpty()) {
                dataToCalculate = dataToCalculate.substring(0, dataToCalculate.length() - 1);
                solutionTv.setText(dataToCalculate);
            }
        } else if (buttonText.equals(".")) {
            // Prevent multiple consecutive decimal points and leading decimal points
            if (!dataToCalculate.contains(".") && !dataToCalculate.startsWith(".")) {
                dataToCalculate = dataToCalculate + buttonText;
                solutionTv.setText(dataToCalculate);
            }
        } else {
            dataToCalculate = dataToCalculate + buttonText;
            solutionTv.setText(dataToCalculate);
        }
    }





    String getResult(String data) {
        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            Object result = context.evaluateString(scriptable, data, "Javascript", 1, null);

            if (result == null || result == Context.getUndefinedValue()) {
                return "Error, wag magpakatanga tulad ko";
            }

            String finalResult = result.toString();

            if (finalResult.endsWith(".0")) {
                finalResult = finalResult.replace(".0", "");
            }
            return finalResult;
        } catch (Exception e) {
            return "Error, gusto ko kita 'NIA'";
        }
    }


}