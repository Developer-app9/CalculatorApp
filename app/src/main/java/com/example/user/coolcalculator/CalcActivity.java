package com.example.user.coolcalculator;

import android.app.Activity;
import android.os.Bundle;import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class CalcActivity extends Activity {

    String runningNumber = "";
    String showsteps = "";
    String op="";
    String right="";
    String evaluate="";
    TextView resultView;
    TextView steps;
    double leftvalue=0.0;
    double result=0.0;
    int length=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc);

        Button btn0 = (Button) findViewById(R.id.btn0);
        Button btn1 = (Button) findViewById(R.id.btn1);
        Button btn2 = (Button) findViewById(R.id.btn2);
        Button btn3 = (Button) findViewById(R.id.btn3);
        Button btn4 = (Button) findViewById(R.id.btn4);
        Button btn5 = (Button) findViewById(R.id.btn5);
        Button btn6 = (Button) findViewById(R.id.btn6);
        Button btn7 = (Button) findViewById(R.id.btn7);
        Button btn8 = (Button) findViewById(R.id.btn8);
        Button btn9 = (Button) findViewById(R.id.btn9);

        ImageButton calcBtn = (ImageButton) findViewById(R.id.calcBtn);
        ImageButton divideBtn = (ImageButton) findViewById(R.id.divideBtn);
        ImageButton multiplyBtn = (ImageButton) findViewById(R.id.multiplyBtn);
        ImageButton subtractBtn = (ImageButton) findViewById(R.id.subtractBtn);
        ImageButton addBtn = (ImageButton) findViewById(R.id.addBtn);

        Button clearBtn = (Button) findViewById(R.id.clearBtn);
        resultView = (TextView) findViewById(R.id.resultView);
        steps = (TextView) findViewById(R.id.steps);

        resultView.setText("0");
        steps.setText("");
        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberPressed(0);
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberPressed(1);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberPressed(2);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberPressed(3);
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberPressed(4);
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberPressed(5);
            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberPressed(6);
            }
        });
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberPressed(7);
            }
        });
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberPressed(8);
            }
        });
        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberPressed(9);
            }
        });

        calcBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                steps.setText(showsteps);
                if(evaluate.equals(""))
                {
                    if(right.equals("")) {
                        if(leftvalue!=0)
                        {
                            steps.setText("");
                            resultView.setText(String.valueOf((long)leftvalue));
                            evaluate=String.valueOf(leftvalue);
                        }
                        else
                            if(runningNumber.equals(""))
                                resultView.setText("0");
                            else {
                                resultView.setText(runningNumber);
                                evaluate=runningNumber;
                            }
                    }
                    else {
                        if(op.equals("multiply")) {
                            leftvalue=leftvalue * Double.parseDouble(right);
                            if (leftvalue == (long)leftvalue)
                                resultView.setText(String.valueOf((long)leftvalue));
                            else
                                resultView.setText(String.format("%s", leftvalue));
                            evaluate=String.valueOf(leftvalue);
                        }
                        else if(op.equals("divide")) {
                            leftvalue=leftvalue/Double.parseDouble(right);
                            if (leftvalue == (long)leftvalue)
                                resultView.setText(String.valueOf((long)leftvalue));
                            else
                                resultView.setText(String.format("%s", leftvalue));
                            evaluate=String.valueOf(leftvalue);
                        }
                        op="";
                        right="";
                    }
                }
                else
                {
                    if(!runningNumber.equals("")) {
                        if(!right.equals(""))
                        {
                            if(op.equals("multiply"))
                                leftvalue=leftvalue*Double.parseDouble(right);
                            else if(op.equals("divide"))
                                leftvalue=leftvalue/Double.parseDouble(right);
                            evaluate=evaluate+String.valueOf(leftvalue);
                            right="";
                        }
                        else
                        {
                            if(evaluate.charAt(length) == '+')
                                evaluate+=runningNumber;
                            else if(evaluate.charAt(length) == '-') {
                                evaluate = evaluate.substring(0, evaluate.length() - 1);
                                evaluate=evaluate+"-"+runningNumber;
                            }
                        }
                    }
                    if(evaluate.charAt(evaluate.length()-1) == '+' || evaluate.charAt(evaluate.length()-1) == '-')
                        evaluate=evaluate.substring(0, evaluate.length()-1);
                    evaluate = evaluate.replace("-", "+-");
                    String []byPluses = evaluate.split("\\+");
                    for(String operand : byPluses)
                        result=result+Double.parseDouble(operand);

                    if(result == (long)result)
                        resultView.setText(String.valueOf((long)result));
                    else
                        resultView.setText(String.format("%s",result));
                }
                evaluate=String.valueOf(result);
                showsteps=String.valueOf(result);
            }
        });
        divideBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(resultView.getText().toString().contains("−") || resultView.getText().toString().contains("+"))
                {
                    evaluate=evaluate.substring(0, evaluate.length()-1-length);
                    runningNumber=String.valueOf(leftvalue);
                    showsteps=showsteps.substring(0, showsteps.length()-1);
                }
                else if(resultView.getText().toString().contains("×"))
                {
                    runningNumber=String.valueOf(leftvalue);
                    showsteps=showsteps.substring(0, showsteps.length()-1);
                }
                else if(resultView.getText().toString().contains("÷"))
                    return;

                if(!runningNumber.equals(""))
                {
                    steps.setText(showsteps);
                    if(right.equals(""))
                        leftvalue = Double.parseDouble(runningNumber);
                    else {
                        if(op.equals("multiply"))
                            leftvalue = leftvalue * Double.parseDouble(right);
                        else if(op.equals("divide"))
                            leftvalue = leftvalue/Double.parseDouble(right);
                        right="";
                    }
                    op="divide";
                    length=String.valueOf(leftvalue).length();
                    runningNumber="";
                    resultView.setText("÷");
                    showsteps+="÷";
                }
            }
        });
        multiplyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(resultView.getText().toString().contains("−") || resultView.getText().toString().contains("+"))
                {
                    evaluate=evaluate.substring(0, evaluate.length()-1-length);
                    runningNumber=String.valueOf(leftvalue);
                    showsteps=showsteps.substring(0, showsteps.length()-1);
                    showsteps+="×";
                }
                else if(resultView.getText().toString().contains("÷"))
                {
                    runningNumber=String.valueOf(leftvalue);
                    showsteps=showsteps.substring(0, showsteps.length()-1);
                    showsteps+="×";
                }
                else if(resultView.getText().toString().contains("×"))
                    return;

                if(!runningNumber.equals(""))
                {
                    steps.setText(showsteps);
                    if(right.equals(""))
                        leftvalue = Double.parseDouble(runningNumber);
                    else
                    {
                        if(op.equals("divide"))
                            leftvalue = leftvalue/Double.parseDouble(right);
                        else if(op.equals("multiply"))
                            leftvalue = leftvalue * Double.parseDouble(right);
                        right="";
                    }
                    op="multiply";
                    length=String.valueOf(leftvalue).length();
                    runningNumber="";
                    resultView.setText("×");
                    showsteps+="×";
                }
            }
        });
        subtractBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(resultView.getText().toString().contains("÷") || resultView.getText().toString().contains("×"))
                {
                    showsteps+="-";
                    runningNumber="-";
                }
                else if(resultView.getText().toString().contains("+"))
                {
                    runningNumber=String.valueOf(leftvalue);
                    showsteps=showsteps.substring(0, showsteps.length()-1);
                    showsteps+="-";
                }
                else if(resultView.getText().toString().contains("-"))
                    return;

                if(!runningNumber.equals(""))
                {
                    if(right.equals(""))
                        leftvalue=Double.parseDouble(runningNumber);
                    else{
                        if(op.equals("multiply"))
                            leftvalue = leftvalue * Integer.parseInt(right);
                        else if(op.equals("divide"))
                            leftvalue = leftvalue / Integer.parseInt(right);
                        right="";
                    }
                    op="";
                    runningNumber="";
                    showsteps+="-";
                    length=String.valueOf(leftvalue).length();
                    evaluate=evaluate+String.valueOf(leftvalue)+"-";
                }
                else
                    evaluate=evaluate+"-";
                resultView.setText("-");
                steps.setText(showsteps);
            }
        });
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(resultView.getText().toString().contains("÷") || resultView.getText().toString().contains("×") || resultView.getText().toString().contains("-"))
                {
                    runningNumber=String.valueOf(leftvalue);
                    showsteps=showsteps.substring(0, showsteps.length()-1);
                }
                else if(resultView.getText().toString().contains("+"))
                    return;

                if(!runningNumber.equals(""))
                {
                    if(right.equals(""))
                        leftvalue=Double.parseDouble(runningNumber);
                    else{
                        if(op.equals("multiply"))
                            leftvalue = leftvalue * Integer.parseInt(right);
                        else if(op.equals("divide"))
                            leftvalue = leftvalue / Integer.parseInt(right);
                        right="";
                    }
                    op="";
                    runningNumber="";
                    resultView.setText("+");
                    showsteps+="+";
                    length=String.valueOf(leftvalue).length();
                    evaluate=evaluate+String.valueOf(leftvalue)+"+";
                }
                steps.setText(showsteps);
            }
        });
        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                runningNumber = "";
                showsteps = "";
                op="";
                right="";
                evaluate="";
                resultView.setText("0");
                steps.setText("");
                leftvalue=0.0;
                result=0.0;
                length=0;
            }
        });
    }

    void numberPressed(int number) {
        runningNumber += String.valueOf(number);
        if(op.equals("multiply") || op.equals("divide")) {
            if(resultView.getText().toString().contains("÷") || resultView.getText().toString().contains("×"))
                steps.setText(showsteps);
            right += String.valueOf(number);
        }
        else if(resultView.getText().toString().contains("−") || resultView.getText().toString().contains("+"))
            steps.setText(showsteps);
        resultView.setText(runningNumber);
        showsteps += String.valueOf(number);

    }
}