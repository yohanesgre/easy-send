/*
 * Copyright (C) 2018 skydoves
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.easysend.features.komisi.adapter

import android.content.res.Resources
import android.view.View
import com.example.easysend.R
import com.example.easysend.features.komisi.adapter.viewholder.KomisiHeaderViewHolder
import com.example.easysend.features.komisi.adapter.viewholder.KomisiViewHolder
import com.example.easysend.features.komisi.data.model.KomisiItem
import com.skydoves.baserecyclerviewadapter.BaseAdapter
import com.skydoves.baserecyclerviewadapter.BaseViewHolder
import com.skydoves.baserecyclerviewadapter.SectionRow

@Suppress("LiftReturnOrAssignment")
class KomisiAdapter(private val delegate: KomisiViewHolder.Delegate)
  : BaseAdapter() {

  /*init {
    for (i in 0..5) {
      addSection(ArrayList<Any>())
    }
  }*/

  fun addSections(sections: Int){
    for (i in 0..sections) addSection(ArrayList<Any>())
  }

  fun addItems(section: Int, items: List<KomisiItem>) {
    addItemOnSection(section, "Minggu $section")
    addItemListOnSection(section, items)
    notifyDataSetChanged()
  }

  override fun layout(sectionRow: SectionRow): Int {
    when (sectionRow.row) {
      0 -> return R.layout.item_komisi_header
      else -> return R.layout.item_komisi
    }
  }

  override fun viewHolder(layout: Int, view: View): BaseViewHolder {
    when (layout) {
      R.layout.item_komisi_header -> return KomisiHeaderViewHolder(view)
      R.layout.item_komisi -> return KomisiViewHolder(view, delegate)
    }
    throw Resources.NotFoundException("not founded layout")
  }
}
