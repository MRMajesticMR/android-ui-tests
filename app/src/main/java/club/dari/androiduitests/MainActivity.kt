package club.dari.androiduitests

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private val bButton: Button by lazy { findViewById(R.id.bButton) }
    private val tvText: TextView by lazy { findViewById(R.id.tvText) }
    private val etName: TextView by lazy { findViewById(R.id.etName) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bButton.setOnClickListener {
            if (etName.text.toString().isNotBlank()) {
                tvText.text = etName.text.toString()

            } else {
                tvText.text = "Hello"
            }

            etName.text = ""
        }
    }
}