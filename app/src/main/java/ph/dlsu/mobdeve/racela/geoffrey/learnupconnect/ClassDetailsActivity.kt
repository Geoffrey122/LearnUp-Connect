package ph.dlsu.mobdeve.racela.geoffrey.learnupconnect

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ClassDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_class_details)

        val classInfo = intent.getStringExtra("classInfo")
        if (classInfo != null) {
            val classDetails = classInfo.split("|")
            val className = classDetails[0]
            val classDescription = classDetails[1]
            val classMode = classDetails[2]
            val classDate = classDetails[3]
            val classTime = classDetails[4]
            val classPrice = classDetails[5]
            val classMax = classDetails[6]

            val tvClassName = findViewById<TextView>(R.id.tv_class_name)
            tvClassName.text = String.format("Class Name: %s", className)
            val tvClassDescription = findViewById<TextView>(R.id.tv_class_description)
            tvClassDescription.text = String.format("Class Description: %s", classDescription)
            val tvClassMode = findViewById<TextView>(R.id.tv_class_mode)
            tvClassMode.text = String.format("Class Mode: %s", classMode)
            val tvClassDate = findViewById<TextView>(R.id.tv_class_date)
            tvClassDate.text = String.format("Class Date: %s", classDate)
            val tvClassTime = findViewById<TextView>(R.id.tv_class_time)
            tvClassTime.text = String.format("Class Time: %s", classTime)
            val tvClassPrice = findViewById<TextView>(R.id.tv_class_price)
            tvClassPrice.text = String.format("Class Price: %s", classPrice)
            val tvClassMax = findViewById<TextView>(R.id.tv_class_max)
            tvClassMax.text = String.format("Max Students: %s", classMax)




            // Set other TextViews for other class details as needed
        }
    }
}
