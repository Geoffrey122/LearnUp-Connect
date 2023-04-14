package ph.dlsu.mobdeve.racela.geoffrey.learnupconnect.data

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import ph.dlsu.mobdeve.racela.geoffrey.learnupconnect.R

class ClassAdapter(private val context: Activity, private val arrayList: ArrayList<Class>):
    ArrayAdapter<Class>(context, R.layout.activity_list_class,arrayList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val inflater: LayoutInflater = LayoutInflater.from(context)
        val view: View = inflater.inflate(R.layout.activity_list_class, null)

        val name: TextView = view.findViewById(R.id.class_name)
        val prof: TextView = view.findViewById(R.id.professor)
        val sched: TextView = view.findViewById(R.id.schedule)
        val mode: TextView = view.findViewById(R.id.mode)

        name.text = arrayList[position].name
        prof.text = arrayList[position].professor
        sched.text = arrayList[position].schedule
        mode.text = arrayList[position].mode

        return view
    }
}