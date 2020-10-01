/*
 * Copyright (C)  guolin, Glance Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.glance.guolindev.ui.data

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.glance.guolindev.R
import kotlinx.android.synthetic.main.glance_library_data_footer.view.*

/**
 * The footer adapter to show how many records are loaded.
 *
 * @author guolin
 * @since 2020/9/30
 */
class DataFooterAdapter(private val layoutWidth: Int, private val block: () -> Int) : RecyclerView.Adapter<DataFooterAdapter.ViewHolder>() {

    private lateinit var context: Context

    /**
     * We do not display footer at first. Only when main data are loaded, we show the footer.
     */
    private var displayFooter = false

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val msgText = itemView.msgText as TextView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        if (!::context.isInitialized) context = parent.context
        val itemView = LayoutInflater.from(context).inflate(R.layout.glance_library_data_footer, parent, false)
        itemView.layoutParams.width = layoutWidth
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val recordCount = block()
        val text = if (recordCount > 1) {
            context.resources.getString(R.string.glance_library_records_loaded)
        } else {
            context.resources.getString(R.string.glance_library_record_loaded)
        }
        holder.msgText.text = String.format(text, recordCount)
    }

    override fun getItemCount() = if (displayFooter) 1 else 0

    /**
     * Display the footer to show how many records are loaded.
     */
    fun displayFooter() {
        displayFooter = true
        notifyDataSetChanged()
    }

}