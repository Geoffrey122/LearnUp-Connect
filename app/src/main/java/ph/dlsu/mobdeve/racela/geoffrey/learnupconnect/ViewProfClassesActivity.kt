package ph.dlsu.mobdeve.racela.geoffrey.learnupconnect

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ph.dlsu.mobdeve.racela.geoffrey.learnupconnect.data.Class
import ph.dlsu.mobdeve.racela.geoffrey.learnupconnect.data.ClassAdapter
import ph.dlsu.mobdeve.racela.geoffrey.learnupconnect.databinding.ActivityViewEnrolledClassesBinding

class ViewProfClassesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityViewEnrolledClassesBinding
    private lateinit var classArrayList: ArrayList<Class>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewEnrolledClassesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = "View Enrolled Classes"

        val name = arrayOf(
            "Physical Education",
            "Integral Calculus",
            "Software Engineering"
        )

        val professor = arrayOf(
            "Mr. Manny Pacquiao",
            "Mr. Isaac Einstein",
            "Dr. Lebron Jordan"
        )

        val schedule = arrayOf(
            "MWF 0800-1100",
            "MWF 1300-1500",
            "TTH 1030-1230"
        )

        val mode = arrayOf(
            "FACE-TO-FACE",
            "HOME TUTORING",
            "ONLINE"
        )

        classArrayList = ArrayList()

        for (i in name.indices) {
            val classvar = Class(name[i], professor[0], schedule[i], mode[i])
            classArrayList.add(classvar)
        }

        binding.listview.isClickable = true
        binding.listview.adapter = ClassAdapter(this, classArrayList)
    }
}