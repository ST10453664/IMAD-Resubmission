package za.ac.iie.imadresubmission


import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    // Parallel arrays for storing daily spending
    private val daysOfWeek = arrayOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")
    private val dailySpending = DoubleArray(7) { 0.0 } // Default all days to $0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize the ListView and Button
        val dailyListView: ListView = findViewById(R.id.dailyListView)
        val btnViewDetails: Button = findViewById(R.id.btnViewDetails)

        // Populate ListView with initial data
        updateListView(dailyListView)

        // Button to navigate to the detailed view
        btnViewDetails.setOnClickListener {
            val intent = Intent(this, DailyDetailsActivity::class.java)
            intent.putExtra("daysOfWeek", daysOfWeek)
            intent.putExtra("dailySpending", dailySpending)
            startActivity(intent)
        }

        // Example: Updating the spending for Wednesday
        dailySpending[2] = 20.5 // Example value
        updateListView(dailyListView)
    }

    // Update the ListView with spending data
    private fun updateListView(listView: ListView) {
        val displayData = daysOfWeek.mapIndexed { index, day ->
            "$day: $${String.format("%.2f", dailySpending[index])}"
        }
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, displayData)
        listView.adapter = adapter
    }
}

