package fr.xgouchet.archx.ui

import androidx.recyclerview.widget.RecyclerView

abstract class ArchXAdapter<VM, VH>
    : RecyclerView.Adapter<VH>()
        where VH : ArchXViewHolder<VM> {

    private var data: List<VM> = emptyList()

    // region RecyclerViw.Adapter

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(data[position])
    }

    // endregion

    // region Open

    open fun updateData(data: List<VM>) {
        this.data = data
        notifyDataSetChanged()
        // TODO include diffutils.callback
    }

    open fun getSpanSize(position: Int): Int {
        return 1
    }

    open fun getMaxSpanSize(): Int {
        return 1
    }

    // endregion
}