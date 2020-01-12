package pl.robert.another_activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

@SuppressLint("Registered")
class ResultActivity : AppCompatActivity() {

    private var resultText: TextView? = null
    private var message: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.result_activity)

        resultText = findViewById(R.id.result)
        message = findViewById(R.id.message)
        val backButton = findViewById<Button>(R.id.backButton)

        val action = intent.getStringExtra("action")
        val nums: List<Int> = intent.getIntegerArrayListExtra("nums")

        calculate(action, nums)

        backButton.setOnClickListener { startActivity(Intent(this@ResultActivity, MainActivity::class.java)) }
    }

    @SuppressLint("SetTextI18n")
    private fun calculate(action: String, nums: List<Int>?) {

        var action = action
        if (nums != null && nums.isNotEmpty()) {
            val num1 = nums[0]
            val num2 = nums[1]
            var result = 0

            when {
                Action.ADDITION.name == action -> {
                    result = num1 + num2
                }
                Action.SUBTRACTION.name == action -> {
                    result = num1 - num2
                }
                Action.MULTIPLICATION.name == action -> {
                    result = num1 * num2
                }
                Action.DIVISION.name == action -> {
                    try {
                        result = num1 / num2
                    } catch (e: ArithmeticException) {
                        action += ", but please ... Do not divide by 0!"
                    }
                }
            }
            resultText!!.text = result.toString()
            message!!.text = "Action: $action"
        }
    }
}