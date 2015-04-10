class __GestureOverlayView_addOnGestureListener_GestureOverlayView_OnGestureListener(val v: android.gesture.GestureOverlayView): ListenerHelper {
  var _onGestureStarted: ((android.gesture.GestureOverlayView?, android.view.MotionEvent?) -> Unit) = { p0, p1 ->  }
  var _onGesture: ((android.gesture.GestureOverlayView?, android.view.MotionEvent?) -> Unit) = { p0, p1 ->  }
  var _onGestureEnded: ((android.gesture.GestureOverlayView?, android.view.MotionEvent?) -> Unit) = { p0, p1 ->  }
  var _onGestureCancelled: ((android.gesture.GestureOverlayView?, android.view.MotionEvent?) -> Unit) = { p0, p1 ->  }

  override fun apply() {
    v.addOnGestureListener(object: android.gesture.GestureOverlayView.OnGestureListener {
      override fun onGestureStarted(p0: android.gesture.GestureOverlayView?, p1: android.view.MotionEvent?) = _onGestureStarted(p0, p1)
      override fun onGesture(p0: android.gesture.GestureOverlayView?, p1: android.view.MotionEvent?) = _onGesture(p0, p1)
      override fun onGestureEnded(p0: android.gesture.GestureOverlayView?, p1: android.view.MotionEvent?) = _onGestureEnded(p0, p1)
      override fun onGestureCancelled(p0: android.gesture.GestureOverlayView?, p1: android.view.MotionEvent?) = _onGestureCancelled(p0, p1)
    })
  }
}

class __GestureOverlayView_addOnGesturingListener_GestureOverlayView_OnGesturingListener(val v: android.gesture.GestureOverlayView): ListenerHelper {
  var _onGesturingStarted: ((android.gesture.GestureOverlayView?) -> Unit) = { p0 ->  }
  var _onGesturingEnded: ((android.gesture.GestureOverlayView?) -> Unit) = { p0 ->  }

  override fun apply() {
    v.addOnGesturingListener(object: android.gesture.GestureOverlayView.OnGesturingListener {
      override fun onGesturingStarted(p0: android.gesture.GestureOverlayView?) = _onGesturingStarted(p0)
      override fun onGesturingEnded(p0: android.gesture.GestureOverlayView?) = _onGesturingEnded(p0)
    })
  }
}

class __KeyboardView_setOnKeyboardActionListener_KeyboardView_OnKeyboardActionListener(val v: android.inputmethodservice.KeyboardView): ListenerHelper {
  var _onPress: ((Int) -> Unit) = { p0 ->  }
  var _onRelease: ((Int) -> Unit) = { p0 ->  }
  var _onKey: ((Int, IntArray?) -> Unit) = { p0, p1 ->  }
  var _onText: ((CharSequence?) -> Unit) = { p0 ->  }
  var _swipeLeft: (() -> Unit) = {}
  var _swipeRight: (() -> Unit) = {}
  var _swipeDown: (() -> Unit) = {}
  var _swipeUp: (() -> Unit) = {}

  override fun apply() {
    v.setOnKeyboardActionListener(object: android.inputmethodservice.KeyboardView.OnKeyboardActionListener {
      override fun onPress(p0: Int) = _onPress(p0)
      override fun onRelease(p0: Int) = _onRelease(p0)
      override fun onKey(p0: Int, p1: IntArray?) = _onKey(p0, p1)
      override fun onText(p0: CharSequence?) = _onText(p0)
      override fun swipeLeft() = _swipeLeft()
      override fun swipeRight() = _swipeRight()
      override fun swipeDown() = _swipeDown()
      override fun swipeUp() = _swipeUp()
    })
  }
}

class __View_addOnAttachStateChangeListener_View_OnAttachStateChangeListener(val v: android.view.View): ListenerHelper {
  var _onViewAttachedToWindow: ((android.view.View?) -> Unit) = { p0 ->  }
  var _onViewDetachedFromWindow: ((android.view.View?) -> Unit) = { p0 ->  }

  override fun apply() {
    v.addOnAttachStateChangeListener(object: android.view.View.OnAttachStateChangeListener {
      override fun onViewAttachedToWindow(p0: android.view.View?) = _onViewAttachedToWindow(p0)
      override fun onViewDetachedFromWindow(p0: android.view.View?) = _onViewDetachedFromWindow(p0)
    })
  }
}

class __ViewGroup_setOnHierarchyChangeListener_ViewGroup_OnHierarchyChangeListener(val v: android.view.ViewGroup): ListenerHelper {
  var _onChildViewAdded: ((android.view.View?, android.view.View?) -> Unit) = { p0, p1 ->  }
  var _onChildViewRemoved: ((android.view.View?, android.view.View?) -> Unit) = { p0, p1 ->  }

  override fun apply() {
    v.setOnHierarchyChangeListener(object: android.view.ViewGroup.OnHierarchyChangeListener {
      override fun onChildViewAdded(p0: android.view.View?, p1: android.view.View?) = _onChildViewAdded(p0, p1)
      override fun onChildViewRemoved(p0: android.view.View?, p1: android.view.View?) = _onChildViewRemoved(p0, p1)
    })
  }
}

class __AbsListView_setOnScrollListener_AbsListView_OnScrollListener(val v: android.widget.AbsListView): ListenerHelper {
  var _onScrollStateChanged: ((android.widget.AbsListView?, Int) -> Unit) = { p0, p1 ->  }
  var _onScroll: ((android.widget.AbsListView?, Int, Int, Int) -> Unit) = { p0, p1, p2, p3 ->  }

  override fun apply() {
    v.setOnScrollListener(object: android.widget.AbsListView.OnScrollListener {
      override fun onScrollStateChanged(p0: android.widget.AbsListView?, p1: Int) = _onScrollStateChanged(p0, p1)
      override fun onScroll(p0: android.widget.AbsListView, p1: Int, p2: Int, p3: Int) = _onScroll(p0, p1, p2, p3)
    })
  }
}

class __AdapterView_setOnItemSelectedListener_AdapterView_OnItemSelectedListener(val v: android.widget.AdapterView<out android.widget.Adapter?>): ListenerHelper {
  var _onItemSelected: ((android.widget.AdapterView<*>?, android.view.View?, Int, Long) -> Unit) = { p0, p1, p2, p3 ->  }
  var _onNothingSelected: ((android.widget.AdapterView<*>?) -> Unit) = { p0 ->  }

  override fun apply() {
    v.setOnItemSelectedListener(object: android.widget.AdapterView.OnItemSelectedListener {
      override fun onItemSelected(p0: android.widget.AdapterView<*>?, p1: android.view.View?, p2: Int, p3: Long) = _onItemSelected(p0, p1, p2, p3)
      override fun onNothingSelected(p0: android.widget.AdapterView<*>?) = _onNothingSelected(p0)
    })
  }
}

class __AutoCompleteTextView_setOnItemSelectedListener_AdapterView_OnItemSelectedListener(val v: android.widget.AutoCompleteTextView): ListenerHelper {
  var _onItemSelected: ((android.widget.AdapterView<*>?, android.view.View?, Int, Long) -> Unit) = { p0, p1, p2, p3 ->  }
  var _onNothingSelected: ((android.widget.AdapterView<*>?) -> Unit) = { p0 ->  }

  override fun apply() {
    v.setOnItemSelectedListener(object: android.widget.AdapterView.OnItemSelectedListener {
      override fun onItemSelected(p0: android.widget.AdapterView<*>?, p1: android.view.View?, p2: Int, p3: Long) = _onItemSelected(p0, p1, p2, p3)
      override fun onNothingSelected(p0: android.widget.AdapterView<*>?) = _onNothingSelected(p0)
    })
  }
}

class __RadioGroup_setOnHierarchyChangeListener_ViewGroup_OnHierarchyChangeListener(val v: android.widget.RadioGroup): ListenerHelper {
  var _onChildViewAdded: ((android.view.View?, android.view.View?) -> Unit) = { p0, p1 ->  }
  var _onChildViewRemoved: ((android.view.View?, android.view.View?) -> Unit) = { p0, p1 ->  }

  override fun apply() {
    v.setOnHierarchyChangeListener(object: android.view.ViewGroup.OnHierarchyChangeListener {
      override fun onChildViewAdded(p0: android.view.View?, p1: android.view.View?) = _onChildViewAdded(p0, p1)
      override fun onChildViewRemoved(p0: android.view.View?, p1: android.view.View?) = _onChildViewRemoved(p0, p1)
    })
  }
}

class __SearchView_setOnQueryTextListener_SearchView_OnQueryTextListener(val v: android.widget.SearchView): ListenerHelper {
  var _onQueryTextSubmit: ((String?) -> Boolean) = { p0 -> false }
  var _onQueryTextChange: ((String?) -> Boolean) = { p0 -> false }

  override fun apply() {
    v.setOnQueryTextListener(object: android.widget.SearchView.OnQueryTextListener {
      override fun onQueryTextSubmit(p0: String?) = _onQueryTextSubmit(p0)
      override fun onQueryTextChange(p0: String?) = _onQueryTextChange(p0)
    })
  }
}

class __SearchView_setOnSuggestionListener_SearchView_OnSuggestionListener(val v: android.widget.SearchView): ListenerHelper {
  var _onSuggestionSelect: ((Int) -> Boolean) = { p0 -> false }
  var _onSuggestionClick: ((Int) -> Boolean) = { p0 -> false }

  override fun apply() {
    v.setOnSuggestionListener(object: android.widget.SearchView.OnSuggestionListener {
      override fun onSuggestionSelect(p0: Int) = _onSuggestionSelect(p0)
      override fun onSuggestionClick(p0: Int) = _onSuggestionClick(p0)
    })
  }
}

class __SeekBar_setOnSeekBarChangeListener_SeekBar_OnSeekBarChangeListener(val v: android.widget.SeekBar): ListenerHelper {
  var _onProgressChanged: ((android.widget.SeekBar?, Int, Boolean) -> Unit) = { p0, p1, p2 ->  }
  var _onStartTrackingTouch: ((android.widget.SeekBar?) -> Unit) = { p0 ->  }
  var _onStopTrackingTouch: ((android.widget.SeekBar?) -> Unit) = { p0 ->  }

  override fun apply() {
    v.setOnSeekBarChangeListener(object: android.widget.SeekBar.OnSeekBarChangeListener {
      override fun onProgressChanged(p0: android.widget.SeekBar, p1: Int, p2: Boolean) = _onProgressChanged(p0, p1, p2)
      override fun onStartTrackingTouch(p0: android.widget.SeekBar?) = _onStartTrackingTouch(p0)
      override fun onStopTrackingTouch(p0: android.widget.SeekBar) = _onStopTrackingTouch(p0)
    })
  }
}

class __SlidingDrawer_setOnDrawerScrollListener_SlidingDrawer_OnDrawerScrollListener(val v: android.widget.SlidingDrawer): ListenerHelper {
  var _onScrollStarted: (() -> Unit) = {}
  var _onScrollEnded: (() -> Unit) = {}

  override fun apply() {
    v.setOnDrawerScrollListener(object: android.widget.SlidingDrawer.OnDrawerScrollListener {
      override fun onScrollStarted() = _onScrollStarted()
      override fun onScrollEnded() = _onScrollEnded()
    })
  }
}

class __TableLayout_setOnHierarchyChangeListener_ViewGroup_OnHierarchyChangeListener(val v: android.widget.TableLayout): ListenerHelper {
  var _onChildViewAdded: ((android.view.View?, android.view.View?) -> Unit) = { p0, p1 ->  }
  var _onChildViewRemoved: ((android.view.View?, android.view.View?) -> Unit) = { p0, p1 ->  }

  override fun apply() {
    v.setOnHierarchyChangeListener(object: android.view.ViewGroup.OnHierarchyChangeListener {
      override fun onChildViewAdded(p0: android.view.View?, p1: android.view.View?) = _onChildViewAdded(p0, p1)
      override fun onChildViewRemoved(p0: android.view.View?, p1: android.view.View?) = _onChildViewRemoved(p0, p1)
    })
  }
}

class __TableRow_setOnHierarchyChangeListener_ViewGroup_OnHierarchyChangeListener(val v: android.widget.TableRow): ListenerHelper {
  var _onChildViewAdded: ((android.view.View?, android.view.View?) -> Unit) = { p0, p1 ->  }
  var _onChildViewRemoved: ((android.view.View?, android.view.View?) -> Unit) = { p0, p1 ->  }

  override fun apply() {
    v.setOnHierarchyChangeListener(object: android.view.ViewGroup.OnHierarchyChangeListener {
      override fun onChildViewAdded(p0: android.view.View?, p1: android.view.View?) = _onChildViewAdded(p0, p1)
      override fun onChildViewRemoved(p0: android.view.View?, p1: android.view.View?) = _onChildViewRemoved(p0, p1)
    })
  }
}

class __TextView_addTextChangedListener_TextWatcher(val v: android.widget.TextView): ListenerHelper {
  var _beforeTextChanged: ((CharSequence?, Int, Int, Int) -> Unit) = { p0, p1, p2, p3 ->  }
  var _onTextChanged: ((CharSequence?, Int, Int, Int) -> Unit) = { p0, p1, p2, p3 ->  }
  var _afterTextChanged: ((android.text.Editable?) -> Unit) = { p0 ->  }

  override fun apply() {
    v.addTextChangedListener(object: android.text.TextWatcher {
      override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) = _beforeTextChanged(p0, p1, p2, p3)
      override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) = _onTextChanged(p0, p1, p2, p3)
      override fun afterTextChanged(p0: android.text.Editable?) = _afterTextChanged(p0)
    })
  }
}

