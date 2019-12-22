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

package com.example.easysend.features.komisi.adapter.viewholder

import android.view.View
import com.example.easysend.databinding.ItemKomisiBinding
import com.example.easysend.features.komisi.data.model.KomisiItem
import com.skydoves.baserecyclerviewadapter.BaseViewHolder

@Suppress("CanBeParameter")
class KomisiViewHolder(
  private val view: View,
  private val delegate: Delegate
) : BaseViewHolder(view) {

  private val binding:ItemKomisiBinding = ItemKomisiBinding.bind(view)

  private lateinit var komisiItem: KomisiItem

  interface Delegate {
    fun onItemClick(komisiItem: KomisiItem)
  }

  override fun bindData(data: Any) {
    if (data is KomisiItem) {
      komisiItem = data
      binding.komisi = data
    }
  }

  override fun onClick(v: View?) {
    delegate.onItemClick(this.komisiItem)
  }

  override fun onLongClick(v: View?) = false
}
