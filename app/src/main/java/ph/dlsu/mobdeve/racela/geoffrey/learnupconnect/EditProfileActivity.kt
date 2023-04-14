package ph.dlsu.mobdeve.racela.geoffrey.learnupconnect

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.google.firebase.database.FirebaseDatabase
import ph.dlsu.mobdeve.racela.geoffrey.learnupconnect.databinding.ActivityEditProfileBinding

class EditProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditProfileBinding
    private var databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://learnup-connect-5daa4-default-rtdb.asia-southeast1.firebasedatabase.app/")
    private val REQUEST_IMAGE_PICK = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = "Edit Profile"
        // Set current user data here
        // For example, get data from database or shared preferences and set them to the EditTexts

        binding.btnChangePhoto.setOnClickListener {
            val pickImageIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(pickImageIntent, REQUEST_IMAGE_PICK)
        }

        binding.btnSaveChanges.setOnClickListener {
            onSaveChangesButtonClick()
        }
    }

    private fun onSaveChangesButtonClick() {
        val newUsername = binding.etUsername.text.toString().trim()
        val newDescription = binding.etEditDescription.text.toString().trim()


        if (newUsername.isBlank()) {
            Toast.makeText(this, "Please enter a username", Toast.LENGTH_SHORT).show()
            return
        }

        // Save updated profile information
        val sharedPreferences = getSharedPreferences("userProfile", Context.MODE_PRIVATE)
        with(sharedPreferences.edit()) {
            putString("username", newUsername)
            putString("description", newDescription)
            apply()

        }



        // Notify the ProfileActivity that the profile has been updated
        LocalBroadcastManager.getInstance(this)
            .sendBroadcast(Intent("PROFILE_UPDATED"))

        // Go back to the ProfileActivity
        onBackPressed()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_PICK && resultCode == Activity.RESULT_OK) {
            val selectedImageUri = data?.data
            binding.ivEditProfilePicture.setImageURI(selectedImageUri)
        }
    }

}
