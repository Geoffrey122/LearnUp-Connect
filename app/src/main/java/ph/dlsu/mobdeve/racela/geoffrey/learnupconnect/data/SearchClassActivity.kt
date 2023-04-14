package ph.dlsu.mobdeve.racela.geoffrey.learnupconnect.data

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.SearchView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ph.dlsu.mobdeve.racela.geoffrey.learnupconnect.R
import ph.dlsu.mobdeve.racela.geoffrey.learnupconnect.SearchClassDetailsActivity
import java.util.*
import kotlin.collections.ArrayList

class SearchClassActivity : AppCompatActivity() {

    private lateinit var newRecyclerView: RecyclerView
    private lateinit var newCourseArray: ArrayList<Class>
    private lateinit var tempArrayList: ArrayList<Class>
    lateinit var cname: Array<String>
    lateinit var cprof: Array<String>
    lateinit var csched: Array<String>
    lateinit var cmode: Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_class)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = "Search Class"

        cname = arrayOf(
            "Mobile Development",
            "Ethics 2",
            "Software Engineering",
            "Physical Education",
            "Integral Calculus",
            "Art Appreciation",
            "Oral Communication",
            "Practical Research",
            "Earth Science",
            "Science and Technology"
        )
        cprof = arrayOf(
            "Dr. Giannis Embiid",
            "Atty. Kawhi Westbrook",
            "Dr. Lebron Jordan",
            "Mr. Manny Pacquiao",
            "Mr. Isaac Einstein",
            "Dr. Tacko Fall",
            "Mr. Damian Irving",
            "Dra. Isabella James",
            "Mr. Luka Magic",
            "Mr. Elon Bezos"
        )
        csched = arrayOf(
            "TTH 1600–1800",
            "MWF 0900–1200",
            "TTH 1030-1230",
            "MWF 0800-1100",
            "MWF 1300-1500",
            "TTH 1430–1630",
            "SAT 1400–1600",
            "SAT 0900–1100",
            "MWF 1800–2000",
            "TTH 0730–0930"
        )
        cmode = arrayOf(
            "F-2-F",
            "ONL",
            "F-2-F",
            "HOME TUTOR",
            "ONL",
            "HOME TUTOR",
            "ONL",
            "F-2-F",
            "HYBRID",
            "ONL"
        )
        newRecyclerView = findViewById(R.id.recycler_view)
        newRecyclerView.layoutManager = LinearLayoutManager(this)
        newRecyclerView.setHasFixedSize(true)
        newCourseArray = arrayListOf<Class>()
        tempArrayList = arrayListOf<Class>()
        getData()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.nav_menu, menu)
        val item = menu?.findItem(R.id.nav_search)
        val searchView = item?.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                TODO("Not yet implemented")
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                tempArrayList.clear()
                val searchText = newText!!.lowercase(Locale.getDefault())
                if(searchText.isNotEmpty()){

                    newCourseArray.forEach {
                        if(it.name.lowercase(Locale.getDefault()).contains(searchText)){

                            tempArrayList.add(it)
                        }
                    }
                    newRecyclerView.adapter!!.notifyDataSetChanged()
                }
                else{
                    tempArrayList.clear()
                    tempArrayList.addAll(newCourseArray)
                    newRecyclerView.adapter!!.notifyDataSetChanged()
                }
                return false
            }
        })

        return super.onCreateOptionsMenu(menu)
    }

    private fun getData() {
        for(i in cprof.indices){
            val cls = Class(cname[i], cprof[i], csched[i], cmode[i])
            newCourseArray.add(cls)
        }

        tempArrayList.addAll(newCourseArray)

        var adapter = SearchClassAdapter(tempArrayList)
        newRecyclerView.adapter = adapter
        adapter.setOnItemClickListener(object : SearchClassAdapter.onItemClickListener {
            override fun onItemClick(position: Int) {
                val intent = Intent(this@SearchClassActivity, SearchClassDetailsActivity::class.java)
                intent.putExtra("name", newCourseArray[position].name)
                intent.putExtra("prof", newCourseArray[position].professor)
                intent.putExtra("sched", newCourseArray[position].schedule)
                intent.putExtra("mode", newCourseArray[position].mode)
                startActivity(intent)
            }
        })
    }
}