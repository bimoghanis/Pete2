package com.pt2.leg5.ui.dashboard
import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.pt2.leg5.R
import com.pt2.leg5.ui.dashboard.DashboardViewModel

class DashboardFragment : Fragment() {

    companion object {
        private const val REQUEST_IMAGE_CAPTURE = 1
        private const val REQUEST_IMAGE_GALLERY = 2
    }

    private lateinit var viewModel: DashboardViewModel
    private lateinit var editTextRestaurantName: EditText
    private lateinit var editTextRestaurantAddress: EditText
    private lateinit var buttonAddPhoto: Button
    private lateinit var imageViewSelectedPhoto: ImageView
    private lateinit var editTextDescription: EditText
    private lateinit var checkBoxClean: CheckBox
    private lateinit var checkBoxNotClean: CheckBox
    private lateinit var buttonSubmit: Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_dashboard, container, false)

        viewModel = ViewModelProvider(this).get(DashboardViewModel::class.java)

        editTextRestaurantName = rootView.findViewById(R.id.editTextRestaurantName)
        editTextRestaurantAddress = rootView.findViewById(R.id.editTextRestaurantAddress)
        buttonAddPhoto = rootView.findViewById(R.id.buttonAddPhoto)
        imageViewSelectedPhoto = rootView.findViewById(R.id.imageViewSelectedPhoto)
        editTextDescription = rootView.findViewById(R.id.editTextDescription)
        checkBoxClean = rootView.findViewById(R.id.checkBoxClean)
        checkBoxNotClean = rootView.findViewById(R.id.checkBoxNotClean)
        buttonSubmit = rootView.findViewById(R.id.buttonSubmit)

        buttonAddPhoto.setOnClickListener {
            showImagePickerDialog()
        }

        buttonSubmit.setOnClickListener {
            submitComment()
        }

        return rootView
    }

    private fun showImagePickerDialog() {
        val items = arrayOf<CharSequence>("Camera", "Gallery")
        val dialog = AlertDialog.Builder(requireContext())
        dialog.setTitle("Choose an option")
        dialog.setItems(items) { dialog, which ->
            when (which) {
                0 -> dispatchTakePictureIntent()
                1 -> dispatchGalleryIntent()
            }
        }
        dialog.show()
    }

    private fun dispatchTakePictureIntent() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (takePictureIntent.resolveActivity(requireActivity().packageManager) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
        }
    }

    private fun dispatchGalleryIntent() {
        val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(galleryIntent, REQUEST_IMAGE_GALLERY)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                REQUEST_IMAGE_CAPTURE -> {
                    val imageBitmap = data?.extras?.get("data") as Bitmap
                    imageViewSelectedPhoto.setImageBitmap(imageBitmap)
                }
                REQUEST_IMAGE_GALLERY -> {
                    val selectedImage = data?.data
                    val imageBitmap = MediaStore.Images.Media.getBitmap(requireActivity().contentResolver, selectedImage)
                    imageViewSelectedPhoto.setImageBitmap(imageBitmap)
                }
            }
        }
    }

    private fun submitComment() {
        val restaurantName = editTextRestaurantName.text.toString()
        val restaurantAddress = editTextRestaurantAddress.text.toString()
        val description = editTextDescription.text.toString()

        // Periksa kebersihan yang dipilih
        val isClean = checkBoxClean.isChecked
        val isNotClean = checkBoxNotClean.isChecked

        // Lakukan sesuatu dengan data komentar/ulasan makanan yang diisi
        // Misalnya, kirim data ke server, simpan di database, dll.
        viewModel.submitComment(restaurantName, restaurantAddress, description, isClean, isNotClean)

        // Reset form setelah submit
        editTextRestaurantName.text.clear()
        editTextRestaurantAddress.text.clear()
        imageViewSelectedPhoto.setImageDrawable(null)
        editTextDescription.text.clear()
        checkBoxClean.isChecked = false
        checkBoxNotClean.isChecked = false
    }
}
