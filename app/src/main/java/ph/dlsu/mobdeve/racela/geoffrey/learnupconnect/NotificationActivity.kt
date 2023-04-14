package ph.dlsu.mobdeve.racela.geoffrey.learnupconnect

import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import ph.dlsu.mobdeve.racela.geoffrey.learnupconnect.data.NotifAdapter
import ph.dlsu.mobdeve.racela.geoffrey.learnupconnect.data.Notifs
import ph.dlsu.mobdeve.racela.geoffrey.learnupconnect.databinding.ActivityNotificationBinding

class NotificationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNotificationBinding
    private lateinit var newRecyclerView: RecyclerView
    private lateinit var newNotifs: ArrayList<Notifs>
    private lateinit var notifAdapter: NotifAdapter
    lateinit var pfpnotif: IntArray
    lateinit var names: Array<String>
    lateinit var messages: Array<String>
    lateinit var times: Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotificationBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_notification)
        supportActionBar!!.title = "Notifications"

        pfpnotif = intArrayOf(
            R.drawable.docprof,
            R.drawable.sample_profile_picture
        )

         names = arrayOf(
            "Dr. Lebron Jordan",
            "Mr. Manny Pacquiao"
        )

        messages = arrayOf(
            "Why are you late??",
            "[ANNOUNCEMENT]: Those who are late..."
        )

        times = arrayOf(
            "10:31",
            "11:00"
        )

        newNotifs = ArrayList()
        getData()
        swipeToDelete()
        newRecyclerView = findViewById(R.id.recycler_view)
        newRecyclerView.layoutManager = LinearLayoutManager(this)
        newRecyclerView.setHasFixedSize(true)
        newRecyclerView.adapter = NotifAdapter(newNotifs)
    }

    private fun getData(){
        for(i in pfpnotif.indices){
            val notif = Notifs(pfpnotif[i], names[i], messages[i], times[i])
            newNotifs.add(notif)
        }
    }

    private fun swipeToDelete(){
        ItemTouchHelper(object: ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val notif = notifAdapter.differ.currentList[position]

                newNotifs.removeAt(position)
                notifAdapter.notifyItemRemoved(position)
            }

        }).attachToRecyclerView(binding.recyclerView)
    }
}