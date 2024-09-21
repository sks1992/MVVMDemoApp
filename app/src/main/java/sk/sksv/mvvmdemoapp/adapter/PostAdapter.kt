package sk.sksv.mvvmdemoapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import sk.sksv.mvvmdemoapp.databinding.EachRowBinding
import sk.sksv.mvvmdemoapp.model.Post

class PostAdapter(private var post: List<Post>) : RecyclerView.Adapter<PostAdapter.MyViewHolder>() {
    private lateinit var binding: EachRowBinding

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        binding = EachRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding.root)
    }

    override fun getItemCount(): Int = post.size


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        binding.tasks.text = post[position].body
    }

    fun setData(postList: List<Post>){
        this.post =postList
        notifyDataSetChanged()
    }
}