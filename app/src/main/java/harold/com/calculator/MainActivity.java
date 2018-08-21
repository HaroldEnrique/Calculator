package harold.com.calculator;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MainActivity extends Activity {


    private TextView result;

    private String operand;

    private String operator;

    private List<String> numbers;

    private List<String> operators;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result = (TextView) findViewById(R.id.result);
    }

    /**
     * Initialization of set of number values.
     */
    private void initNumbers() {
        numbers = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            numbers.add(Integer.toString(i));
        }
    }

    /**
     * Initialization of set of operators.
     */
    private void initOperators() {
        operators = new ArrayList<>();
        String[] ops = { "+", "-", "*", "/" ,"sqrt"};
        for (String operator : ops) {
            operators.add(operator);
        }
    }

    /**
     * Button click event handler.
     *
     * @param view
     *            clicked button
     * @return void
     */
    public void handleClick(View view) {
        Button clicked = (Button) view;
        String value = clicked.getText().toString();

        if (isNumerical(value)) {
            if (!isDefaultResult(result.getText().toString())) {
                value = result.getText().toString() + value;
            }
        } else if (isOperator(value)) {
            operand = result.getText().toString();
            operator = value;
        } else if (isClear(value)) {
            value = getString(R.string.result_default);
        } else {

            if(operator.equals("sqrt")){

                double a = Double.parseDouble(operand);

                    String indice = result.getText().toString();
                    String[] ar = indice.split("sqrt");


                    Double b = Double.parseDouble(ar[1]);
                    //Double b = Double.parseDouble(ar[3]);
                    value = Double.toString(Math.pow(a,1/b));
                    System.out.println("raiz >> " + a + " --- " + b + "operacion " + value);

            }else{
                double a = Double.parseDouble(operand), b;
                String numberb =  result.getText().toString();
                String[] array =numberb.split("");
                int wide = array.length -1;

                String newvalue="";
                for(int i=2; i<=wide; i++){
                    System.out.println(array[i]);
                    newvalue = newvalue + array[i];
                }
                System.out.println("obteniendo nuevo valor " + newvalue);
                b = Double.parseDouble(newvalue);
                if (operator.equals("+")) {
                    value = Double.toString(a + b);

                    System.out.println("esta sumando" + value);
                }else if(operator.equals("-")){
                    value = Double.toString(a - b);
                    System.out.println("esta restando" + a + " --- " + b + "operacion " + value);
                }else if(operator.equals("*")){
                    value = Double.toString(a * b);
                    System.out.println("esta multiplicando" + a + " --- " + b + "operacion " + value);
                }
            }




            // Reset values.
            operator = null;
            operand = null;
        }

        result.setText(value);
    }

    /**
     * Test if value is the same as clear button's.
     *
     * @param value
     *            button value
     * @return true if button is clear button
     */
    private boolean isClear(String value) {
        return value.equals(getString(R.string.buttonClear));
    }

    /**
     * Test if value is operator.
     *
     * @param value
     *            button value
     * @return true if value is operator
     */
    private boolean isOperator(String value) {
        if (operators == null) {
            initOperators();
        }
        return operators.contains(value);
    }

    /**
     * Test if result was modified.
     *
     * @param value
     *            result value
     * @return true if result is default
     */
    private boolean isDefaultResult(String value) {
        return value.equals(getString(R.string.result_default));
    }

    /**
     * Test if value is numerical.
     *
     * @param value
     *            button's value
     * @return true if value is numerical
     */
    private boolean isNumerical(String value) {
        if (numbers == null) {
            initNumbers();
        }
        return numbers.contains(value);
    }

}
