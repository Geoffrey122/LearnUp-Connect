package ph.dlsu.mobdeve.racela.geoffrey.learnupconnect.data

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ph.dlsu.mobdeve.racela.geoffrey.learnupconnect.R

class ClassesAdapter(private val classes: List<String>) :
    RecyclerView.Adapter<ClassesAdapter.ClassesViewHolder>() {

    inner class ClassesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(R.id.class_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClassesViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.class_item, parent, false)
        return ClassesViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ClassesViewHolder, position: Int) {
        val classInfo = classes[position]
        val className = classInfo.split("|")[0]
        holder.textView.text = className
    }

    override fun getItemCount(): Int {
        return classes.size
    }
}
