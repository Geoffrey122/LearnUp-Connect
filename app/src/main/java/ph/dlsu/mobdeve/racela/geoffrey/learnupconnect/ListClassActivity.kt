package ph.dlsu.mobdeve.racela.geoffrey.learnupconnect

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ph.dlsu.mobdeve.racela.geoffrey.learnupconnect.databinding.ActivityListClassBinding

class ListClassActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListClassBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListClassBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.className.setText(intent.getStringExtra("name"));
        binding.professor.setText(intent.getStringExtra("professor"));
        binding.schedule.setText(intent.getStringExtra("schedule"));
        binding.mode.setText(intent.getStringExtra("mode"));

    }
}