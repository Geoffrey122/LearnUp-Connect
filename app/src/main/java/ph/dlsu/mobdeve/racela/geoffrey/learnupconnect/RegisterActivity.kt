package ph.dlsu.mobdeve.racela.geoffrey.learnupconnect

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import ph.dlsu.mobdeve.racela.geoffrey.learnupconnect.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.title = "Register"

        auth = Firebase.auth
        databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://learnup-connect-5daa4-default-rtdb.asia-southeast1.firebasedatabase.app/")

        binding.registerSubmit.setOnClickListener{
            performSignUp()
        }
    }

    private fun performSignUp() {
        val email = findViewById<EditText>(R.id.register_email)
        val username = findViewById<EditText>(R.id.register_username)
        val password = findViewById<EditText>(R.id.register_password)
        val usertype = findViewById<EditText>(R.id.register_usertype)
        var strEmail1 = email.text.toString() // to preserve the true value of email in string form (test@yahoo.com)
        var strEmail2 = strEmail1.replace(".", " ") // (test@yahoo com) - this serves as the unique item for each user // '.' is not allowed so we replace it

        if(email.text.isEmpty() || username.text.isEmpty() || password.text.isEmpty() || usertype.text.isEmpty()){
            Toast.makeText(this, "Please fill all fields up.", Toast.LENGTH_SHORT).show()
            return
        } else{

            databaseReference.child("users").addListenerForSingleValueEvent(object: ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    if(snapshot.hasChild(strEmail2)){
                        Toast.makeText(this@RegisterActivity, "This username is already taken!", Toast.LENGTH_SHORT).show()
                    }
                    else{
                        databaseReference.child("users").child(strEmail2).child("username").setValue(username.text.toString())
                        databaseReference.child("users").child(strEmail2).child("password").setValue(password.text.toString())
                        databaseReference.child("users").child(strEmail2).child("usertype").setValue(usertype.text.toString())

                        Toast.makeText(this@RegisterActivity, "$username registered successfully!", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this@RegisterActivity, MainActivity::class.java)
                        startActivity(intent)
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            })
        }
    }
}