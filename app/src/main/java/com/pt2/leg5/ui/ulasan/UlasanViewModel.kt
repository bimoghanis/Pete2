// UlasanViewModel.kt
package com.pt2.leg5.ui.ulasan

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.pt2.leg5.db.UlasanDao
import com.pt2.leg5.db.UlasanDb
import com.pt2.leg5.db.UlasanEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UlasanViewModel(application: Application) : AndroidViewModel(application) {
    private val ulasanDao: UlasanDao = UlasanDb.getDatabase(application).ulasanDao()

    fun getAllUlasan(): LiveData<List<UlasanEntity>> {
        return ulasanDao.getAllUlasan()
    }

    fun submitComment(
        restaurantName: String,
        restaurantAddress: String,
        description: String,
        isClean: Boolean,
        services: Boolean,
        packaging: Boolean,
        recommend: Boolean

    ) {
        val ulasan = UlasanEntity(
            restaurantName = restaurantName,
            restaurantAddress = restaurantAddress,
            photo = "", // Ubah dengan path foto yang sesuai jika Anda ingin menyimpan path foto ke dalam database
            description = description,
            isClean = isClean,
            packaging = packaging,
            services = services,
            recommend = recommend
        )

        // Menampilkan Konfirmasi berhasil
        viewModelScope.launch(Dispatchers.IO) {
            ulasanDao.insert(ulasan)
            withContext(Dispatchers.Main) {
                Toast.makeText(getApplication(), "Data ulasan berhasil disimpan", Toast.LENGTH_SHORT).show()
            }
        }

    }
}
