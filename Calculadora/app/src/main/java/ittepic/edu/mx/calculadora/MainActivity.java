package ittepic.edu.mx.calculadora;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, neg, diag, por, igual, c, mas, menos;
    //EditText eti;
    TextView eti;
    int ope = 0;
    int aux = 0;
    String a="";
    boolean t = true, suma=false, resta=false, mult=false, div=false,nega=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btn0 = (Button) findViewById(R.id.button14);
        btn1 = (Button) findViewById(R.id.button10);
        btn2 = (Button) findViewById(R.id.button11);
        btn3 = (Button) findViewById(R.id.button12);
        btn4 = (Button) findViewById(R.id.button6);
        btn5 = (Button) findViewById(R.id.button7);
        btn6 = (Button) findViewById(R.id.button8);
        btn7 = (Button) findViewById(R.id.button5);
        btn8 = (Button) findViewById(R.id.button4);
        btn9 = (Button) findViewById(R.id.button3);
        neg = (Button) findViewById(R.id.button);
        diag = (Button) findViewById(R.id.button2);
        por = (Button) findViewById(R.id.button9);
        menos = (Button) findViewById(R.id.button13);
        mas = (Button) findViewById(R.id.button17);
        c = (Button) findViewById(R.id.button18);
        igual = (Button) findViewById(R.id.button16);
        eti = (TextView) findViewById(R.id.textView5);

        btn0.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);
        neg.setOnClickListener(this);
        diag.setOnClickListener(this);
        por.setOnClickListener(this);
        menos.setOnClickListener(this);
        igual.setOnClickListener(this);
        c.setOnClickListener(this);
        mas.setOnClickListener(this);

    }




    @Override
    public void onClick(View view) {
        switch (view.getId()){


            case R.id.button14:

                if (eti.getText().toString().equals("0")||eti.getText().toString().equals("+")||eti.getText().toString().equals("-")||eti.getText().toString().equals("*")||eti.getText().toString().equals("/")){
                    eti.setText("0");
                }else{
                    eti.setText(eti.getText()+"0");
                }

                break;

            case R.id.button10:

                if (eti.getText().toString().equals("0")||eti.getText().toString().equals("+")||eti.getText().toString().equals("-")||eti.getText().toString().equals("*")||eti.getText().toString().equals("/")){
                    eti.setText("1");
                }else{
                    eti.setText(eti.getText()+"1");
                }

                break;


            case R.id.button11:

                if (eti.getText().toString().equals("0")||eti.getText().toString().equals("+")||eti.getText().toString().equals("-")||eti.getText().toString().equals("*")||eti.getText().toString().equals("/")){
                    eti.setText("2");
                }else{
                    eti.setText(eti.getText()+"2");
                }

                break;
            case R.id.button12:

                if (eti.getText().toString().equals("0")||eti.getText().toString().equals("+")||eti.getText().toString().equals("-")||eti.getText().toString().equals("*")||eti.getText().toString().equals("/")){
                    eti.setText("3");
                }else{
                    eti.setText(eti.getText()+"3");
                }

                break;

            case R.id.button6:

                if (eti.getText().toString().equals("0")||eti.getText().toString().equals("+")||eti.getText().toString().equals("-")||eti.getText().toString().equals("*")||eti.getText().toString().equals("/")){
                    eti.setText("4");
                }else{
                    eti.setText(eti.getText()+"4");
                }

                break;
            case R.id.button7:

                if (eti.getText().toString().equals("0")||eti.getText().toString().equals("+")||eti.getText().toString().equals("-")||eti.getText().toString().equals("*")||eti.getText().toString().equals("/")){
                    eti.setText("5");
                }else{
                    eti.setText(eti.getText()+"5");
                }

                break;

            case R.id.button8:

                if (eti.getText().toString().equals("0")||eti.getText().toString().equals("+")||eti.getText().toString().equals("-")||eti.getText().toString().equals("*")||eti.getText().toString().equals("/")){
                    eti.setText("6");
                }else{
                    eti.setText(eti.getText()+"6");
                }

                break;
            case R.id.button5:

                if (eti.getText().toString().equals("0")||eti.getText().toString().equals("+")||eti.getText().toString().equals("-")||eti.getText().toString().equals("*")||eti.getText().toString().equals("/")){
                    eti.setText("7");
                }else{
                    eti.setText(eti.getText()+"7");
                }

                break;
            case R.id.button4:

                if (eti.getText().toString().equals("0")||eti.getText().toString().equals("+")||eti.getText().toString().equals("-")||eti.getText().toString().equals("*")||eti.getText().toString().equals("/")){
                    eti.setText("8");
                }else{
                    eti.setText(eti.getText()+"8");
                }

                break;
            case R.id.button3:

                if (eti.getText().toString().equals("0")||eti.getText().toString().equals("+")||eti.getText().toString().equals("-")||eti.getText().toString().equals("*")||eti.getText().toString().equals("/")){
                    eti.setText("9");
                }else{
                    eti.setText(eti.getText()+"9");
                }

                break;
            case R.id.button18:

                eti.setText("0");
                aux=0;
                ope=0;

                break;
            case R.id.button17:

              aux=aux+Integer.parseInt(eti.getText().toString());
              eti.setText("+");
                suma=true;

                break;
            case R.id.button9:

                aux=Integer.parseInt(eti.getText().toString());
                eti.setText("*");
                mult=true;

                break;
            case R.id.button2:

                aux=Integer.parseInt(eti.getText().toString());
                eti.setText("/");
                div=true;

                break;
            case R.id.button13:

                aux=Integer.parseInt(eti.getText().toString());
                eti.setText("-");
                resta=true;

                break;
            case R.id.button16:
                if(suma){
                    ope=Integer.parseInt(eti.getText().toString());
                    aux=aux+ope;
                    eti.setText(aux+"");
                    aux=0;
                    ope=0;
                    suma=false;
                }
                if(resta){
                    ope=Integer.parseInt(eti.getText().toString());
                    aux=aux-ope;
                    eti.setText(aux+"");
                    aux=0;
                    ope=0;
                    resta=false;
                }
                if(mult){
                    ope=Integer.parseInt(eti.getText().toString());
                    aux=aux*ope;
                    eti.setText(aux+"");
                    mult=false;
                    aux=0;
                    ope=0;
                }
                if(div){
                    ope=Integer.parseInt(eti.getText().toString());
                    aux=aux/ope;
                    eti.setText(aux+"");
                    aux=0;
                    ope=0;
                    div=false;
                }
                break;

            case R.id.button:

                aux=Integer.parseInt(eti.getText().toString());
                if(nega==false){
                    eti.setText("-"+eti.getText().toString());
                    nega=true;
                }else{
                    eti.setText(Math.abs(aux)+"");
                    nega=false;
                }


                break;


        }
    }
}
