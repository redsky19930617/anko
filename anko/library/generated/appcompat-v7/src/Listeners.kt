@file:JvmName("AppcompatV7ListenersKt")
package org.jetbrains.anko.appcompat.v7


inline fun android.support.v7.widget.ActionMenuView.onMenuItemClick(noinline l: (item: android.view.MenuItem?) -> Boolean) {
    setOnMenuItemClickListener(l)
}

inline fun android.support.v7.widget.SearchView.onClose(noinline l: () -> Boolean) {
    setOnCloseListener(l)
}

inline fun android.support.v7.widget.SearchView.onQueryTextFocusChange(noinline l: (v: android.view.View, hasFocus: Boolean) -> Unit) {
    setOnQueryTextFocusChangeListener(l)
}

fun android.support.v7.widget.SearchView.onQueryTextListener(init: __SearchView_OnQueryTextListener.() -> Unit) {
    val listener = __SearchView_OnQueryTextListener()
    listener.init()
    setOnQueryTextListener(listener)
}

class __SearchView_OnQueryTextListener : android.support.v7.widget.SearchView.OnQueryTextListener {
    private var _onQueryTextSubmit: ((String?) -> Boolean)? = null
    private var _onQueryTextChange: ((String?) -> Boolean)? = null

    override fun onQueryTextSubmit(query: String?) = _onQueryTextSubmit?.invoke(query) ?: false

    fun onQueryTextSubmit(listener: (String?) -> Boolean) {
        _onQueryTextSubmit = listener
    }

    override fun onQueryTextChange(newText: String?) = _onQueryTextChange?.invoke(newText) ?: false

    fun onQueryTextChange(listener: (String?) -> Boolean) {
        _onQueryTextChange = listener
    }

}

inline fun android.support.v7.widget.SearchView.onSearchClick(noinline l: (v: android.view.View?) -> Unit) {
    setOnSearchClickListener(l)
}

fun android.support.v7.widget.SearchView.onSuggestionListener(init: __SearchView_OnSuggestionListener.() -> Unit) {
    val listener = __SearchView_OnSuggestionListener()
    listener.init()
    setOnSuggestionListener(listener)
}

class __SearchView_OnSuggestionListener : android.support.v7.widget.SearchView.OnSuggestionListener {
    private var _onSuggestionSelect: ((Int) -> Boolean)? = null
    private var _onSuggestionClick: ((Int) -> Boolean)? = null

    override fun onSuggestionSelect(position: Int) = _onSuggestionSelect?.invoke(position) ?: false

    fun onSuggestionSelect(listener: (Int) -> Boolean) {
        _onSuggestionSelect = listener
    }

    override fun onSuggestionClick(position: Int) = _onSuggestionClick?.invoke(position) ?: false

    fun onSuggestionClick(listener: (Int) -> Boolean) {
        _onSuggestionClick = listener
    }

}

inline fun android.support.v7.widget.Toolbar.onMenuItemClick(noinline l: (item: android.view.MenuItem?) -> Boolean) {
    setOnMenuItemClickListener(l)
}

