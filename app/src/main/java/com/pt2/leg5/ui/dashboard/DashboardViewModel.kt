package com.pt2.leg5.ui.dashboard
import androidx.lifecycle.ViewModel

class DashboardViewModel : ViewModel() {
    var restaurantName: String = ""
    var restaurantAddress: String = ""
    var description: String = ""
    var isClean: Boolean = false
    var isNotClean: Boolean = false

    fun submitComment(name: String, address: String, desc: String, clean: Boolean, notClean: Boolean) {
        // Lakukan sesuatu dengan data komentar/ulasan makanan yang diisi
        // Misalnya, kirim data ke server, simpan di database, dll.

        // Reset nilai variabel setelah submit
        restaurantName = ""
        restaurantAddress = ""
        description = ""
        isClean = false
        isNotClean = false
    }
}
