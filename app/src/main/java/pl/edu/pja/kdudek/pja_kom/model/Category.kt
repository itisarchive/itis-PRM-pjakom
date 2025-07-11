package pl.edu.pja.kdudek.pja_kom.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DesktopWindows
import androidx.compose.material.icons.filled.Headphones
import androidx.compose.material.icons.filled.Keyboard
import androidx.compose.material.icons.filled.LaptopChromebook
import androidx.compose.material.icons.filled.Mouse
import androidx.compose.material.icons.filled.PhoneAndroid
import androidx.compose.material.icons.filled.Save
import androidx.compose.ui.graphics.vector.ImageVector
import pl.edu.pja.kdudek.pja_kom.R

enum class Category(val displayName: Int, val icon: ImageVector) {
    LAPTOP(R.string.category_laptop, Icons.Filled.LaptopChromebook),
    SMARTPHONE(R.string.category_smartphone, Icons.Filled.PhoneAndroid),
    HEADPHONE(R.string.category_headphone, Icons.Filled.Headphones),
    DISPLAY(R.string.category_display, Icons.Filled.DesktopWindows),
    DISK(R.string.category_disk, Icons.Filled.Save),
    MOUSE(R.string.category_mouse, Icons.Filled.Mouse),
    KEYBOARD(R.string.category_keyboard, Icons.Filled.Keyboard),
}
