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
import com.example.easysend.databinding.ItemKomisiHeaderBinding
import com.skydoves.baserecyclerviewadapter.BaseViewHolder

@Suppress("ClassName", "CanBeParameter")
class KomisiHeaderViewHolder(private val view: View)
  : BaseViewHolder(view) {

  private val binding:ItemKomisiHeaderBinding = ItemKomisiHeaderBinding.bind(view)

  override fun bindData(data: Any) {
    if (data is String) binding.sample1Header.text = data
  }

  override fun onClick(v: View?) = Unit

  override fun onLongClick(v: View?) = false
}
