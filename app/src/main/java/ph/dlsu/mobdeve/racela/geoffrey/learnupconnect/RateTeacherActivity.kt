package ph.dlsu.mobdeve.racela.geoffrey.learnupconnect

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import com.google.android.material.snackbar.Snackbar
import ph.dlsu.mobdeve.racela.geoffrey.learnupconnect.databinding.ActivityRateTeacherBinding
import ph.dlsu.mobdeve.racela.geoffrey.learnupconnect.databinding.ActivitySendMessageBinding

class RateTeacherActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRateTeacherBinding

    override fun onResume() {
        super.onResume()
        val teachers = resources.getStringArray(R.array.teachers)
        val arrayAdapter = ArrayAdapter(this, R.layout.teacher_dropdown_item, teachers)
        binding.autoCompleteTV.setAdapter(arrayAdapter)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRateTeacherBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = "Rate Teacher"

        binding.sendButton.setOnClickListener{
            Snackbar.make(binding.root,
                "Posting Feedback...",
                Toast.LENGTH_SHORT).show()
        }
    }

}