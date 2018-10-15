package fr.xgouchet.archx.ui

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class ArchXViewHolder<VM>(itemView: View) : RecyclerView.ViewHolder(itemView) {

    abstract fun bind(item: VM)

}