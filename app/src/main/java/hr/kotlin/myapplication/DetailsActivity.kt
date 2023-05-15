package hr.kotlin.myapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailsActivity : AppCompatActivity() {

    private lateinit var tvName: TextView
    private lateinit var tvDateOfBirth: TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        tvName = findViewById(R.id.tvName)
        tvDateOfBirth = findViewById(R.id.tvDateOfBirth)

        val name = intent.getStringExtra("name")
        val dateOfBirth = intent.getStringExtra("dateOfBirth")

        tvName.text = name
        tvDateOfBirth.text = dateOfBirth

        val btnBack: Button = findViewById(R.id.btnBack)
        btnBack.setOnClickListener {
            onBackPressed()
        }
    }
}