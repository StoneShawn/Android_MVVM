package d.ishigishou.shawn_mvvm.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import d.ishigishou.shawn_mvvm.R
import d.ishigishou.shawn_mvvm.databinding.ItemUserBinding
import d.ishigishou.shawn_mvvm.models.UserListModel

class MainAdapter(private val interaction: Interaction? = null) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val callback = object : DiffUtil.ItemCallback<UserListModel>() {
        override fun areItemsTheSame(oldItem: UserListModel, newItem: UserListModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: UserListModel, newItem: UserListModel): Boolean {
            return oldItem == newItem
        }

    }

    private val differ = AsyncListDiffer(this, callback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false),
            interaction
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ViewHolder -> {
                holder.bind(differ.currentList[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    class ViewHolder constructor(itemView: View, private val interaction: Interaction?) :
        RecyclerView.ViewHolder(itemView) {
        fun bind(item: UserListModel) {
            val binding = ItemUserBinding.bind(itemView)

            itemView.setOnClickListener {
                interaction?.onItemSelected(adapterPosition, item)
            }
            binding.tvName.text = item.login
            Glide.with(binding.root.context)
                .load(item.avatar_url)
                .circleCrop()
                .into(binding.ivAvatar)
        }

    }

    fun submitList(list: ArrayList<UserListModel>) {
        val newList = ArrayList(differ.currentList)
        newList.addAll(list)
        differ.submitList(newList)
    }

    interface Interaction {
        fun onItemSelected(position: Int, item: UserListModel)
    }
}