package ph.dlsu.mobdeve.racela.geoffrey.learnupconnect.data

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ph.dlsu.mobdeve.racela.geoffrey.learnupconnect.R

class SearchClassAdapter(private val classes: ArrayList<Class>): RecyclerView.Adapter<SearchClassAdapter.SearchClassViewHolder>() {

    private lateinit var mListener: onItemClickListener
    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener){
        mListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchClassViewHolder {
        return SearchClassViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_classes, parent, false), mListener)
    }

    override fun onBindViewHolder(holder: SearchClassViewHolder, position: Int) {
        val currentClass = classes[position]

        holder.tv_cname.text = currentClass.name
        holder.tv_csched.text = currentClass.schedule
    }

    override fun getItemCount(): Int {
        return classes.size
    }

    class SearchClassViewHolder(itemView: View, listener: onItemClickListener): RecyclerView.ViewHolder(itemView){

        val tv_cname: TextView = itemView.findViewById(R.id.course_name)
        val tv_csched: TextView = itemView.findViewById(R.id.course_sched)

        init{
            itemView.setOnClickListener{
                listener.onItemClick(adapterPosition)
            }
        }
    }

}