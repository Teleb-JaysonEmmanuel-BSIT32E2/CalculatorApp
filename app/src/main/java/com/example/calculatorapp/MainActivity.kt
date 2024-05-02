package com.example.calculatorapp


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.example.calculatorapp.databinding.ActivityMainBinding
//import kotlinx.android.synthetic.main.activity_main.* // removed
import org.mariuszgromada.math.mxparser.Expression // It is the source from the internet which automatically compute the formula
import java.lang.Exception
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding // setting up view binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        //setContentView(R.layout.activity_main)
        setContentView(view)

        binding.btnClear.setOnClickListener {
            binding.input.text = ""
            binding.output.text = ""
        }

        // removed
        /* it will clear the display text
        btnClear.setOnClickListener {
            input.text = ""
            output.text = ""
        }*/

        /* when the user press the button, it will perform what value inside addToInputText
           and it will display the value in input field  */

        binding.btnOpenBracket.setOnClickListener {
            binding.input.text = addToInputText("(")
        }
        binding.btnCloseBracket.setOnClickListener {
            binding.input.text = addToInputText(")")
        }
        binding.btn0.setOnClickListener {
            binding.input.text = addToInputText("0")
        }
        binding.btn1.setOnClickListener {
            binding.input.text = addToInputText("1")
        }
        binding.btn2.setOnClickListener {
            binding.input.text = addToInputText("2")
        }
        binding.btn3.setOnClickListener {
            binding.input.text = addToInputText("3")
        }
        binding.btn4.setOnClickListener {
            binding.input.text = addToInputText("4")
        }
        binding.btn5.setOnClickListener {
            binding.input.text = addToInputText("5")
        }
        binding.btn6.setOnClickListener {
            binding.input.text = addToInputText("6")
        }
        binding.btn7.setOnClickListener {
            binding.input.text = addToInputText("7")
        }
        binding.btn8.setOnClickListener {
            binding.input.text = addToInputText("8")
        }
        binding.btn9.setOnClickListener {
            binding.input.text = addToInputText("9")
        }
        binding.btnDecimal.setOnClickListener {
            binding.input.text = addToInputText(".")
        }
        binding.btnDivision.setOnClickListener {
            binding.input.text = addToInputText("÷")
        }
        binding.btnMultiply.setOnClickListener {
            binding.input.text = addToInputText("x")
        }
        binding.btnAddition.setOnClickListener {
            binding.input.text = addToInputText("+")
        }
        binding.btnSubtraction.setOnClickListener {
            binding.input.text = addToInputText("-")
        }

        // it will display the result
        binding.btnEqual.setOnClickListener {
            showResult()
        }

    }

    // I create a method to take shortcut as a button value for input text
    private fun addToInputText(buttonValue: String): String
    {
        return "${binding.input.text}$buttonValue"
    }

    // When the user input "/" or "*", it will reflect as what operators involved in
    private fun getInputExpression(): String
    {
        var expression = binding.input.text.replace(Regex("÷"), "/")
        expression = expression.replace(Regex("×"), "*")
        return expression
    }


    // This function are showing the result
    private fun showResult()
    {
        try
        {
            val expression = getInputExpression()
            val result = Expression(expression).calculate()

            // it shows the error calculation
            if (result.isNaN()){
                // show error message
                binding.output.text = "Error"
                binding.output.setTextColor(ContextCompat.getColor(this,R.color.red))
            }
            else{
                // show result
                binding.output.text = DecimalFormat("0.######").format(result).toString()
                binding.output.setTextColor(ContextCompat.getColor(this,R.color.green))
            }

        }

        // it shows the error message if there is a chances of syntax error in expression
        catch (e: Exception)
        {
            binding.output.text = "Error"
            binding.output.setTextColor(ContextCompat.getColor(this,R.color.red))
        }
    }

}