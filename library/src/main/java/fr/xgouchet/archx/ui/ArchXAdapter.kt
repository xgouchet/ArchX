package fr.xgouchet.archx.ui

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

abstract class ArchXAdapter<VM, VH, I>(
        protected val extractId: ((VM) -> I)?
) : RecyclerView.Adapter<VH>()
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

    // region ArchXAdapter

    fun getItem(position: Int): VM? {
        return data.getOrNull(position)
    }

    // endregion

    // region Open

    open fun updateData(data: List<VM>) {
        if (extractId == null) {
            this.data = data
            notifyDataSetChanged()
        } else {
            val callback = ArchXDiffUtilCallback(this.data, data, extractId)
            val updates = DiffUtil.calculateDiff(callback)
            updates.dispatchUpdatesTo(this)
            this.data = data
        }
    }

    open fun getSpanSize(position: Int): Int {
        return 1
    }

    open fun getMaxSpanSize(): Int {
        return 1
    }

    // endregion
}
