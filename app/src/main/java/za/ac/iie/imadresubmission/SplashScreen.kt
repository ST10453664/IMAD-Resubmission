package za.ac.iie.imadresubmission

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.Button

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val btnStart: Button = findViewById(R.id.btnStart)
        val btnExit: Button = findViewById(R.id.btnExit)

        btnStart.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        btnExit.setOnClickListener {
            finishAffinity()
        }



    }
}