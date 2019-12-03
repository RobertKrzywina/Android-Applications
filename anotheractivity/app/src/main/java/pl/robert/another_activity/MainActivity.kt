package pl.robert.another_activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class MainActivity : AppCompatActivity() {

    private var num1: EditText? = null
    private var num2: EditText? = null

    private var action: Action? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        num1 = findViewById(R.id.num1)
        num2 = findViewById(R.id.num2)
        val addButton = findViewById<Button>(R.id.addButton)
        val minusButton = findViewById<Button>(R.id.minusButton)
        val multiplyButton = findViewById<Button>(R.id.multiplyButton)
        val divideButton = findViewById<Button>(R.id.divideButton)

        addButton.setOnClickListener {
            action = Action.ADDITION
            initializeIntent()
        }

        minusButton.setOnClickListener {
            action = Action.SUBTRACTION
            initializeIntent()
        }

        multiplyButton.setOnClickListener {
            action = Action.MULTIPLICATION
            initializeIntent()
        }

        divideButton.setOnClickListener {
            action = Action.DIVISION
            initializeIntent()
        }
    }

    private fun initializeIntent() {
        val num1 = num1!!.text.toString()
        val num2 = num2!!.text.toString()
        if (areNumsNotEmpty(num1, num2)) {
            val intent = Intent(this@MainActivity, ResultActivity::class.java)
            intent.putExtra("num1", num1)
            intent.putExtra("num2", num2)
            intent.putExtra("action", action?.name)
            val nums = ArrayList<Int>()
            nums.add(num1.toInt())
            nums.add(num2.toInt())
            intent.putIntegerArrayListExtra("nums", nums)
            startActivity(intent)
        }
    }

    private fun areNumsNotEmpty(num1: String, num2: String): Boolean {
        return num1.isNotEmpty() && num2.isNotEmpty()
    }
}
