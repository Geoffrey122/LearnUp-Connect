package ph.dlsu.mobdeve.racela.geoffrey.learnupconnect

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.localbroadcastmanager.content.LocalBroadcastManager

class CreateClassActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_class)

        val editTextClassName: EditText = findViewById(R.id.et_class_name)
        val editTextClassSubject: EditText = findViewById(R.id.et_subject)
        val radioButtonOnline: RadioButton = findViewById(R.id.rb_online)
        val editTextClassDate: EditText = findViewById(R.id.et_date)
        val editTextClassTime: EditText = findViewById(R.id.et_time)
        val editTextClassPrice: EditText = findViewById(R.id.et_price)
        val editTextMaxStudents: EditText = findViewById(R.id.et_max_students)

        val submitButton: Button = findViewById(R.id.btn_submit)
        submitButton.setOnClickListener {
            val className = editTextClassName.text.toString()
            val classSubject = editTextClassSubject.text.toString()
            val classType = if (radioButtonOnline.isChecked) "Online" else "Face to Face"
            val classDate = editTextClassDate.text.toString()
            val classTime = editTextClassTime.text.toString()
            val classPrice = editTextClassPrice.text.toString()
            val classMaxStudents = editTextMaxStudents.text.toString()

            val sharedPreferences = getSharedPreferences("classes", Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            val classInfo = "$className|$classSubject|$classType|$classDate|$classTime|$classPrice|$classMaxStudents"
            editor.putString(className, classInfo)
            editor.apply()

            Toast.makeText(this, "Class created successfully", Toast.LENGTH_SHORT).show()
            finish()

            LocalBroadcastManager.getInstance(this).sendBroadcast(Intent("NEW_CLASS_CREATED"))
        }
    }
}