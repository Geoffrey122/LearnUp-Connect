package ph.dlsu.mobdeve.racela.geoffrey.learnupconnect

import android.content.Context
import android.os.Bundle
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.MenuItem
import ph.dlsu.mobdeve.racela.geoffrey.learnupconnect.data.ClassesAdapter

class AllClassesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_classes)


        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        val classes = getClasses()

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = ClassesAdapter(classes)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }



    private fun getClasses(): List<String> {
        val sharedPreferences = getSharedPreferences("classes", MODE_PRIVATE)
        val classes = mutableListOf<String>()

        sharedPreferences.all.forEach { (_, value) ->
            if (value is String) {
                classes.add(value)
            }
        }
        return classes
    }
}