package com.eazyrento.agent.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eazyrento.R
import com.eazyrento.agent.model.modelclass.AgentNotesListResModelItem
import kotlinx.android.synthetic.main.agent_note_card_view.view.*

class AgentNotesListAdapter (val items:List<AgentNotesListResModelItem>, val context: Context):
    RecyclerView.Adapter<AgentNotesListAdapter.ViewHolder>() {

    class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        val tvNoteTitle=view.tv_note_title
        val tvNoteDesc=view.tv_note_date

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return AgentNotesListAdapter.ViewHolder(
            LayoutInflater.from(
                context
            ).inflate(R.layout.agent_note_card_view, parent, false)
        )

    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvNoteTitle?.text=items.get(position).header
        holder.tvNoteDesc?.text=items.get(position).description
    }
}