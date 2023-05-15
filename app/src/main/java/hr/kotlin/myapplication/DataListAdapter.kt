package hr.kotlin.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DataListAdapter(
    private val dataList: List<Person>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<DataListAdapter.DataViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_data, parent, false)
        return DataViewHolder(view)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val person = dataList[position]
        holder.bind(person)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    inner class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        private val tvName: TextView = itemView.findViewById(R.id.tvName)
        private val tvDateOfBirth: TextView = itemView.findViewById(R.id.tvDateOfBirth)

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(person: Person) {
            tvName.text = person.name
            tvDateOfBirth.text = person.dateOfBirth
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
}