package ph.dlsu.mobdeve.racela.geoffrey.learnupconnect

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import ph.dlsu.mobdeve.racela.geoffrey.learnupconnect.data.SearchClassActivity
import ph.dlsu.mobdeve.racela.geoffrey.learnupconnect.databinding.ActivityHome2Binding

class HomeActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivityHome2Binding

    lateinit var notificationManager: NotificationManager
    lateinit var notificationChannel: NotificationChannel
    lateinit var  builder: Notification.Builder
    private val channelID = "Mr. Manny Pacquiao"
    private val desc = "Notifications"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHome2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.title = "Home"

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.profile_button -> {
                    Toast.makeText(applicationContext,
                        "Going to Profile..",
                        Toast.LENGTH_SHORT).show()
                    val profileIntent = Intent(this, ProfileActivity2::class.java)
                    startActivity(profileIntent)
                    true
                }

                R.id.search -> {
                    Toast.makeText(applicationContext,
                        "Going to Search Class..",
                        Toast.LENGTH_SHORT).show()
                    val searchClassIntent = Intent(this, SearchClassActivity::class.java)
                    startActivity(searchClassIntent)
                    true
                }

                R.id.notifications -> {
                    Toast.makeText(applicationContext,
                        "Going to Notifications",
                        Toast.LENGTH_SHORT).show()
                    val notifIntent = Intent(this, NotificationActivity::class.java)
                    startActivity(notifIntent)
                    true
                }

                R.id.messages -> {
                    Toast.makeText(applicationContext,
                        "Going to Messages..",
                        Toast.LENGTH_SHORT).show()
                    val messageIntent = Intent(this, SendMessageActivity::class.java)
                    startActivity(messageIntent)
                    true
                }

                else -> false
            }
        }
    }
}