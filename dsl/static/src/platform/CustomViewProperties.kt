/*
 * Copyright 2015 JetBrains s.r.o.
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

package org.jetbrains.anko

import android.graphics.drawable.Drawable
import android.util.TypedValue
import android.view.View
import android.widget.ImageView
import android.widget.TextView

var View.backgroundDrawable: Drawable
    get() = background
    set(value) = setBackgroundDrawable(value)

var View.leftPadding: Int
    get() = paddingLeft
    set(value) = setPadding(value, paddingTop, paddingRight, paddingBottom)

var View.topPadding: Int
    get() = paddingTop
    set(value) = setPadding(paddingLeft, value, paddingRight, paddingBottom)

var View.rightPadding: Int
    get() = paddingRight
    set(value) = setPadding(paddingLeft, paddingTop, value, paddingBottom)

var View.bottomPadding: Int
    get() = paddingBottom
    set(value) = setPadding(paddingLeft, paddingTop, paddingRight, value)

@Deprecated("Use horizontalPadding instead", ReplaceWith("horizontalPadding"))
var View.paddingHorizontal: Int
    get() = throw PropertyWithoutGetterException("paddingHorizontal")
    set(value) = setPadding(value, paddingTop, value, paddingBottom)

var View.horizontalPadding: Int
    get() = throw PropertyWithoutGetterException("horizontalPadding")
    set(value) = setPadding(value, paddingTop, value, paddingBottom)

@Deprecated("Use verticalPadding instead", ReplaceWith("verticalPadding"))
var View.paddingVertical: Int
    get() = throw PropertyWithoutGetterException("paddingVertical")
    set(value) = setPadding(paddingLeft, value, paddingRight, value)

var View.verticalPadding: Int
    get() = throw PropertyWithoutGetterException("verticalPadding")
    set(value) = setPadding(paddingLeft, value, paddingRight, value)

var View.padding: Int
    get() = throw PropertyWithoutGetterException("padding")
    set(value) = setPadding(value, value, value, value)

var TextView.isSelectable: Boolean
    get() = isTextSelectable
    set(value) = setTextIsSelectable(value)

var TextView.textSizeDimen: Int
    get() = throw PropertyWithoutGetterException("textSizeDimen")
    set(value) = setTextSize(TypedValue.COMPLEX_UNIT_PX, context.resources.getDimension(value))

var ImageView.image: Drawable?
    get() = drawable
    set(value) = setImageDrawable(value)