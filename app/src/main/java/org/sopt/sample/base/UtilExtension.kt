package org.sopt.sample.base

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

/** Fragment 키보드 내리기 확장함수 */
fun Fragment.hideKeyboard() {
    view?.let { activity?.hideKeyboard(it) }
}

/** Activity 키보드 내리기 확장함수 */
fun Activity.hideKeyboard() {
    hideKeyboard(currentFocus ?: View(this))
}

/** Context 키보드 내리기 확장함수 */
fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}

/** Snackbar 확장함수 */
fun Context.showSnackbar(view: View, msg: String) {
    Snackbar.make(view, msg, Toast.LENGTH_SHORT).show()
}

/** Toast 확장함수 */
fun Context.showToast(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}