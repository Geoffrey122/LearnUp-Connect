package ph.dlsu.mobdeve.racela.geoffrey.learnupconnect

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ph.dlsu.mobdeve.racela.geoffrey.learnupconnect.databinding.ActivityClassBinding

class ClassActivity : AppCompatActivity() {

    private lateinit var binding: ActivityClassBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityClassBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name = intent.getStringExtra("name")
        val professor = intent.getStringExtra("professor")
        val schedule = intent.getStringExtra("schedule")
        val mode = intent.getStringExtra("mode")

        binding.classnameTest.text = name
        binding.profTest.text = professor
        binding.schedTest.text = schedule
        binding.mode.text = mode
    }
}