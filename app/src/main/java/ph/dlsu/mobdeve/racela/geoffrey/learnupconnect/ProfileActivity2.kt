package ph.dlsu.mobdeve.racela.geoffrey.learnupconnect

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import ph.dlsu.mobdeve.racela.geoffrey.learnupconnect.databinding.ActivityProfile2Binding
import android.content.SharedPreferences
import android.widget.Toast

class ProfileActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivityProfile2Binding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfile2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.title = "View Profile"
        // Load user data
        loadData()

        // Set up edit profile button
        binding.btnEditProfile.setOnClickListener {
            val editProfileIntent = Intent(this, EditProfileActivity::class.java)
            startActivity(editProfileIntent)
        }
        binding.viewEnrolledclassButton.setOnClickListener{
            Toast.makeText(applicationContext,
                "Going to View enrolled classes..",
                Toast.LENGTH_SHORT).show()

            val goToViewEnrolledClassesActivity = Intent(applicationContext, ViewEnrolledClassesActivity::class.java)
            startActivity(goToViewEnrolledClassesActivity)
        }

        binding.rateButton.setOnClickListener{
            Toast.makeText(applicationContext,
                "Going to Rate teacher..",
                Toast.LENGTH_SHORT).show()

            val goToRateTeacherActivity = Intent(applicationContext, RateTeacherActivity::class.java)
            startActivity(goToRateTeacherActivity)
        }

        binding.btnLogout.setOnClickListener {
            logout()
        }
    }


    private fun logout() {
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }

    private fun loadData() {
        // Load user data from a data source, such as SharedPreferences, a database, or an API.

        // Sample data
        binding.tvUsername.text = "Student Test"
        binding.tvEmail.text = "student@gmail.com"
        binding.tvDescription.text = "Add Description"
    }

    private val profileUpdatedReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            loadProfileData()
        }
    }

    private fun loadProfileData() {
        val sharedPreferences = getSharedPreferences("userProfile", MODE_PRIVATE)
        val username = sharedPreferences.getString("username", "")
        val description = sharedPreferences.getString("description", "")

        binding.tvUsername.text = username
        binding.tvDescription.text = description
    }
    override fun onResume() {
        super.onResume()
        LocalBroadcastManager.getInstance(this)
            .registerReceiver(profileUpdatedReceiver, IntentFilter("PROFILE_UPDATED"))
        loadProfileData()
    }

    override fun onPause() {
        super.onPause()
        LocalBroadcastManager.getInstance(this).unregisterReceiver(profileUpdatedReceiver)
    }

}