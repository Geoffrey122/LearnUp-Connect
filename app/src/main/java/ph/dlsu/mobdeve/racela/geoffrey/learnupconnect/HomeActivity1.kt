package ph.dlsu.mobdeve.racela.geoffrey.learnupconnect

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import ph.dlsu.mobdeve.racela.geoffrey.learnupconnect.data.SearchClassActivity

class HomeActivity1 : AppCompatActivity() {

    lateinit var notificationManager: NotificationManager
    lateinit var notificationChannel: NotificationChannel
    lateinit var  builder: Notification.Builder
    private val channelID = "Mr. Manny Pacquiao"
    private val desc = "Notifications"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home1)

        supportActionBar!!.title = "LearnUp Connect - Teacher"

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.profile_button -> {
                    Toast.makeText(
                        applicationContext,
                        "Going to Profile..",
                        Toast.LENGTH_SHORT
                    ).show()
                    val profileIntent = Intent(this, ProfileActivity::class.java)
                    startActivity(profileIntent)
                    true
                }

                R.id.search -> {
                    Toast.makeText(
                        applicationContext,
                        "Going to Search Class..",
                        Toast.LENGTH_SHORT
                    ).show()
                    val searchClassIntent = Intent(this, SearchClassActivity::class.java)
                    startActivity(searchClassIntent)
                    true
                }

                R.id.notifications -> {
                    Toast.makeText(
                        applicationContext,
                        "Going to Notifications",
                        Toast.LENGTH_SHORT
                    ).show()
                    val notifIntent = Intent(this, NotificationActivity2::class.java)
                    startActivity(notifIntent)
                    true
                }

                R.id.messages -> {
                    Toast.makeText(
                        applicationContext,
                        "Going to Messages..",
                        Toast.LENGTH_SHORT
                    ).show()
                    val messageIntent = Intent(this, SendMessageActivity::class.java)
                    startActivity(messageIntent)
                    true
                }

                else -> false
            }
        }

        val viewAllClasses: TextView = findViewById(R.id.tv_view_all_classes)
        viewAllClasses.setOnClickListener {
            val intent = Intent(this, AllClassesActivity::class.java)
            startActivity(intent)
        }

        val btnCreateClass = findViewById<Button>(R.id.btn_create_class)
        btnCreateClass.setOnClickListener {
            val intent = Intent(this, CreateClassActivity::class.java)
            startActivity(intent)
        }

        val btnCreateMessageActivity = findViewById<Button>(R.id.btn_send_message)
        btnCreateMessageActivity.setOnClickListener {
            val intent = Intent(this, SendMessageActivity::class.java)
            startActivity(intent)
        }

        // Get classes from SharedPreferences
        val classes = getClasses()
        displayClasses(classes)


    }


    private fun getClasses(): List<String> {
        val sharedPreferences = getSharedPreferences("classes", Context.MODE_PRIVATE)
        val classes = mutableListOf<String>()

        sharedPreferences.all.forEach { (key, value) ->
            if (value is String) {
                classes.add(value)
            }
        }

        return classes
    }

    private fun displayClasses(classes: List<String>) {
        val linearLayout = findViewById<LinearLayout>(R.id.layout_classes)
        linearLayout.removeAllViews()
        classes.forEach { classInfo ->
            val classDetails = classInfo.split("|")
            val className = classDetails[0]

            val textView = TextView(this)
            textView.text = className
            textView.textSize = 16f
            textView.layoutParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            ).apply { setMargins(0, 8, 0, 8) }
            textView.background = ContextCompat.getDrawable(this, R.drawable.class_item_background)
            textView.setTextColor(ContextCompat.getColor(this, android.R.color.black))
            textView.tag = classInfo // Set the tag with classInfo

            textView.setOnClickListener {
                val clickedClassInfo = it.tag as String // Get the classInfo from the tag
                val intent = Intent(this, ClassDetailsActivity::class.java)
                intent.putExtra("classInfo", clickedClassInfo)
                startActivity(intent)
            }

            linearLayout.addView(textView)
        }
    }

    private val newClassReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            val classes = getClasses()
            displayClasses(classes)
        }
    }

    override fun onResume() {
        super.onResume()
        LocalBroadcastManager.getInstance(this)
            .registerReceiver(newClassReceiver, IntentFilter("NEW_CLASS_CREATED"))
    }

    override fun onPause() {
        super.onPause()
        LocalBroadcastManager.getInstance(this).unregisterReceiver(newClassReceiver)
    }
}