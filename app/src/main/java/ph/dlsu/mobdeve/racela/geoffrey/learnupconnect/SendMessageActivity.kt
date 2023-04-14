package ph.dlsu.mobdeve.racela.geoffrey.learnupconnect

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar
import ph.dlsu.mobdeve.racela.geoffrey.learnupconnect.data.SearchClassActivity
import ph.dlsu.mobdeve.racela.geoffrey.learnupconnect.databinding.ActivitySendMessageBinding


class SendMessageActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySendMessageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySendMessageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = "Send Message"

        binding.sendButton.setOnClickListener{
            Snackbar.make(binding.root,
                "Sending Message...",
                Toast.LENGTH_SHORT).show()
        }
    }
}