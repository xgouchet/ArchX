package fr.xgouchet.archx.ui

import androidx.recyclerview.widget.DiffUtil

class ArchXDiffUtilCallback<T, I>(
        protected val oldData: List<T>,
        protected val newData: List<T>,
        protected val extractId: (T) -> I
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldData.size

    override fun getNewListSize(): Int = newData.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return extractId(oldData[oldItemPosition]) == extractId(newData[newItemPosition])
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldData[oldItemPosition] == newData[newItemPosition]
    }
}
