package za.ac.iie.imadresubmission

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView

class DetailedViewScreen : AppCompatActivity() {
    private lateinit var daysOfWeek: Array<String>
    private lateinit var dailySpending: DoubleArray
    private lateinit var morningSpending: DoubleArray
    private lateinit var afternoonSpending: DoubleArray
    private lateinit var expenseNotes: Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed_view_screen)

        // Retrieve data from Intent
        daysOfWeek = intent.getStringArrayExtra("daysOfWeek") ?: emptyArray()
        dailySpending = intent.getDoubleArrayExtra("dailySpending") ?: DoubleArray(0)
        morningSpending = intent.getDoubleArrayExtra("morningSpending") ?: DoubleArray(0)
        afternoonSpending = intent.getDoubleArrayExtra("afternoonSpending") ?: DoubleArray(0)
        expenseNotes = intent.getStringArrayExtra("expenseNotes") ?: emptyArray()

        // Initialize views
        val detailedListView: ListView = findViewById(R.id.detailedListView)
        val btnHighestSpending: Button = findViewById(R.id.btnHighestSpending)
        val highestSpendingText: TextView = findViewById(R.id.highestSpendingText)
        val btnBackToMain: Button = findViewById(R.id.btnBackToMain)

        // Populate ListView with detailed information
        val details = daysOfWeek.mapIndexed { index, day ->
            """
                Day: $day
                Morning: $${String.format("%.2f", morningSpending[index])}
                Afternoon: $${String.format("%.2f", afternoonSpending[index])}
                Total: $${String.format("%.2f", dailySpending[index])}
                Notes: ${expenseNotes[index]}
            """.trimIndent()
        }
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, details)
        detailedListView.adapter = adapter

        // Calculate and display the highest spending day
        btnHighestSpending.setOnClickListener {
            val highestIndex = dailySpending.indices.maxByOrNull { dailySpending[it] } ?: -1
            if (highestIndex >= 0) {
                val averageSpending = dailySpending.average()
                highestSpendingText.text = """
                    Highest Spending Day: ${daysOfWeek[highestIndex]} ($${String.format("%.2f", dailySpending[highestIndex])})
                    Average Daily Spending: $${String.format("%.2f", averageSpending)}
                """.trimIndent()
            }
        }

        // Navigate back to Main Screen
        btnBackToMain.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

    }
}