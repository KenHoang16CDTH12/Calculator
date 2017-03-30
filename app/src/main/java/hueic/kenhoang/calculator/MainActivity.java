package hueic.kenhoang.calculator;

import android.content.DialogInterface;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;

public class MainActivity extends AppCompatActivity {
    //Declare control
    private TextView tvSyntax;
    private TextView tvResult;
    private Button btnDelete;
    private Button btn0;
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;
    private Button btn5;
    private Button btn6;
    private Button btn7;
    private Button btn8;
    private Button btn9;
    private Button btnCham;
    private Button btnSum;
    private Button btnSub;
    private Button btnMul;
    private Button btnDevide;
    private Button btnEqual;

    private StringBuffer str_show = new StringBuffer("");
    private BigDecimal num1, num2;
    private boolean flag_dot = true;
    private boolean flag_num1 = false;
    private String str_oper = null;
    private String str_result = null;
    private int scale = 2;
    private boolean isScaleChanged = false;
    private boolean flag_minus = false;

    public void setScale (int scale) {
        this.scale = scale;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //remove title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        //hide action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_main);
        //findviewById
        addControls();
        //handle events
        addEvents();
    }

    private void addEvents() {
        //handle btnEquals
        btnSum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //handleEquals(1);
                setNum1(btnSum.getText().toString());
                tvResult.setText(btnSum.getText());
            }
        });
        btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!flag_minus) {
                    if (str_show.toString().equals("")) {
                        str_show.append("-");
                        showInTextView(str_show.toString());
                        flag_minus = true;
                    }
                }
                setNum1(btnSub.getText().toString());
                tvResult.setText(btnSub.getText());
            }
        });
        btnMul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setNum1(btnMul.getText().toString());
                tvResult.setText(btnMul.getText());
            }
        });
        btnDevide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setNum1(btnDevide.getText().toString());
                tvResult.setText(btnDevide.getText());
            }
        });
        btnEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (str_oper == null || str_show.toString().equals("")
                        || !flag_num1) {

                }
                calculate();
            }
        });
        //handle btnNumber
        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleNumber(0);
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleNumber(1);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleNumber(2);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleNumber(3);
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleNumber(4);
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleNumber(5);
            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleNumber(6);
            }
        });
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleNumber(7);
            }
        });
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleNumber(8);
            }
        });
        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleNumber(9);
            }
        });

        btnCham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (str_show.toString() == "") {
                } else if (flag_dot) {
                    str_show.append(".");
                    showInTextView(str_show.toString());
                    flag_dot = false;
                }
            }
        });
        //handle Btn Delete
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleReset();
            }
        });
        btnDelete.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                tvResult.setText("Operator");
                tvSyntax.setText("");
                str_show = new StringBuffer("");
                flag_dot = true;
                flag_num1 = false;
                flag_minus = false;
                str_oper = null;
                return true;
            }
        });

    }

    private void setNum1(String oper) {
        if (str_oper != null && !str_show.toString().equals("") && flag_num1) {
            calculate();
        }
        str_oper = oper;
        if (!(str_show.toString() == "") && !str_show.toString().equals("-")) {
            num1 = new BigDecimal(str_show.toString());
            showInTextView(str_show.toString());
            str_show = new StringBuffer("");
            str_result = null;
            flag_num1 = true;
            flag_minus = false;
        } else if (str_result != null) {
            num1 = new BigDecimal(str_result);
            showInTextView(str_result);
            str_result = null;
            flag_num1 = true;
            flag_minus = false;
        }
        flag_dot = true;
    }

    private void calculate() {
        if(str_show.toString().equals("-")) return;
        double result = 0;
        num2 = new BigDecimal(str_show.toString());
        if (str_oper.equals("+")) {
            result = Calculate.add(num1, num2);
        }
        if (str_oper.equals("-")) {
            result = Calculate.sub(num1, num2);
        }
        if (str_oper.equals("*")) {
            result = Calculate.mul(num1, num2);
        }
        if (str_oper.equals("/")) {
            if (!num2.equals(BigDecimal.ZERO)) {
                result = Calculate.div(num1, num2, scale);
            } else {
                Toast.makeText(MainActivity.this, ":)))！", Toast.LENGTH_LONG)
                        .show();
                showInTextView("");
                str_show = new StringBuffer("");
                str_oper = null;
                flag_num1 = false;
                flag_dot = true;
                return;
            }
        }
        str_result = String.valueOf(Calculate.round(result, scale));
        String[] resultStrings = str_result.split("\\.");
        if (resultStrings[1].equals("0")) {
            str_result = resultStrings[0];
        }
        showInTextView(str_result);
        str_show = new StringBuffer("");
        flag_dot = true;
        flag_num1 = false;
        str_oper = null;
        flag_minus = true;
    }

    private void showInTextView(String s) {
            tvSyntax.setText(s);
    }

    private void handleReset() {
        tvResult.setText("Operator");
        if (!(str_show.toString() == "")) {
            if (!flag_dot) {
                String lastStr = String.valueOf(str_show.charAt(str_show
                        .length() - 1));
                if (lastStr.equals(".")) {
                    flag_dot = true;
                }
            }
            str_show.deleteCharAt(str_show.length() - 1);
            if(str_show.toString().equals("")){
                flag_minus = false;
            }
            showInTextView(str_show.toString());
        } else {
            showInTextView("");
            str_result = null;
            str_show = new StringBuffer("");
            flag_dot = true;
            flag_minus = false;
        }
        flag_num1 = false;
    }


    private void handleNumber(int i) {

        str_show.append(Integer.toString(i));

        tvSyntax.setText(str_show);
    }

    private void addControls() {
        //textview
        tvSyntax = (TextView) findViewById(R.id.tvSyntax);
        tvResult = (TextView) findViewById(R.id.tvResult);
        //button events
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnSum = (Button) findViewById(R.id.btnSum);
        btnSub = (Button) findViewById(R.id.btnSub);
        btnMul = (Button) findViewById(R.id.btnMul);
        btnDevide = (Button) findViewById(R.id.btnDevide);
        btnEqual = (Button) findViewById(R.id.btnEqual);
        //button numbers
        btnCham = (Button) findViewById(R.id.btnCham);
        btn0 = (Button) findViewById(R.id.btn0);
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn4 = (Button) findViewById(R.id.btn4);
        btn5 = (Button) findViewById(R.id.btn5);
        btn6 = (Button) findViewById(R.id.btn6);
        btn7 = (Button) findViewById(R.id.btn7);
        btn8 = (Button) findViewById(R.id.btn8);
        btn9 = (Button) findViewById(R.id.btn9);

    }
}

