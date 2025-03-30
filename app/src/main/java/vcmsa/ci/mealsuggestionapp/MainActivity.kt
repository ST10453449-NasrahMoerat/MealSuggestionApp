package vcmsa.ci.mealsuggestionapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var timeInput: EditText
    private lateinit var suggestionText: TextView
    private lateinit var resetButton: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Initialize views
        timeInput = findViewById(R.id.timeInput)
        suggestionText = findViewById(R.id.suggestionText)
        resetButton = findViewById(R.id.ResetButton)

        // Set click listener for the suggestion button
        findViewById<Button>(R.id.suggestionButton).setOnClickListener {
            val time = timeInput.text.toString()
            displayMealsuggestion(time)
        }

        // Set click listener for reset button
        resetButton.setOnClickListener {
            resetFields()
        }
    }

    private fun displayMealsuggestion(time: String) {
        val mealSuggestion = getMealSuggestion (time)
        suggestionText.text = mealSuggestion
    }

    private fun getMealSuggestion(time:String) : String {
        val hour = try {
            time.toInt()
        } catch (e: NumberFormatException) {
            return "Invaild time format! Please enter an the time of day."
        }

        return when {
            hour in 8..10-> "Morning: Eggs and macon, Oatmeal with avocado and toast."
            hour in 9..10 -> "Mid Morning snack : Fruit and yogurt."
            hour in 12..16 -> "Lunch: Chicken and mayo snadwich,Vegetarian wraps."
            hour in 14..15 -> "Mid Afternoon Snack: Cake and Biscuits."
            hour in 17..20 -> "Dinner: Pasta, Grilled steak and fries"
            hour > 20 -> "After Dinner Snack: Ice Cream."
            else -> "No meal suggestion available for this time."
        }
    }

    private fun resetFields() {
        timeInput.text.clear()
        suggestionText.text= ""
    }
}
