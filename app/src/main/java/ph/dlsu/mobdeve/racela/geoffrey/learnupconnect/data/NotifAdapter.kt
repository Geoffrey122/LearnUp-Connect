package ph.dlsu.mobdeve.racela.geoffrey.learnupconnect.data

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.AsyncListUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import ph.dlsu.mobdeve.racela.geoffrey.learnupconnect.R

class NotifAdapter(private val notifList: ArrayList<Notifs>): RecyclerView.Adapter<NotifAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_notifs, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return notifList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val notif = notifList[position]

        holder.pfp_img.setImageResource(notif.pfp_image)
        holder.name.text = notif.name
        holder.message.text = notif.message
        holder.time.text = notif.time
    }

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        val pfp_img: ShapeableImageView = itemView.findViewById(R.id.pfp_notif)
        val name: TextView = itemView.findViewById(R.id.name_notif)
        val message: TextView = itemView.findViewById(R.id.message_notif)
        val time: TextView = itemView.findViewById(R.id.time_notif)
    }

    private val diffCallback = object : DiffUtil.ItemCallback<String>(){
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return true
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, diffCallback)

}