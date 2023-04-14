package ph.dlsu.mobdeve.racela.geoffrey.learnupconnect

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase
import ph.dlsu.mobdeve.racela.geoffrey.learnupconnect.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var auth: FirebaseAuth
    private var databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://learnup-connect-5daa4-default-rtdb.asia-southeast1.firebasedatabase.app/")


    private val launchRegister = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        val data = result.data

        Snackbar.make(binding.root,
            "Registered ${data!!.getStringExtra("email")}",
            Snackbar.LENGTH_LONG).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth

        binding.registerButton.setOnClickListener{
            var goToRegister = Intent(this, RegisterActivity::class.java)
            launchRegister.launch(goToRegister)
        }

        binding.loginButton.setOnClickListener{
            performLogin()
        }
    }

    private fun performLogin() {

        // Getting user input
        val email = findViewById<EditText>(R.id.emailtext)
        val password = findViewById<EditText>(R.id.passwordtext)
        var strEmail1 = email.text.toString() // to preserve the true value of email in string form (test@yahoo.com)
        var strEmail2 = strEmail1.replace(".", " ") // (test@yahoo com) - this serves as the unique item for each user // '.' is not allowed so we replace it

        // Checking for null values
        if(email.text.isEmpty() || password.text.isEmpty()){
            Toast.makeText(this, "Please fill the email or password field!", Toast.LENGTH_SHORT).show()
            return
        }
        else{
            databaseReference.child("users").addListenerForSingleValueEvent(object:
                ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {

                    if(snapshot.hasChild(strEmail2)){

                        val getPassword = snapshot.child(strEmail2).child("password").getValue(String().javaClass)
                        val getUsertype = snapshot.child(strEmail2).child("usertype").getValue(String().javaClass)

                        if(getPassword.equals(password.text.toString())){

                            if(getUsertype.toString().lowercase() == "student"){
                                Toast.makeText(this@MainActivity, "Welcome Student!", Toast.LENGTH_SHORT).show()
                                val intent = Intent(this@MainActivity, HomeActivity2::class.java)
                                startActivity(intent)
                            }
                            else if(getUsertype.toString().lowercase() == "teacher"){
                                Toast.makeText(this@MainActivity, "Welcome Teacher!", Toast.LENGTH_SHORT).show()
                                val intent = Intent(this@MainActivity, HomeActivity1::class.java)
                                startActivity(intent)
                            }

                        } else{

                            Toast.makeText(this@MainActivity, "Wrong Password. Please try again..", Toast.LENGTH_SHORT).show()
                        }
                    } else{

                        Toast.makeText(this@MainActivity, "No email found..", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }
            })
        }
    }
}