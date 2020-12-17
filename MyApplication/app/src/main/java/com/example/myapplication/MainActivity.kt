package btu.edu.tasiko

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private lateinit var emailInput: EditText
    private lateinit var passwordInput: EditText
    private lateinit var submitButton: Button
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        emailInput = findViewById(R.id.emailInput)
        passwordInput = findViewById(R.id.passwordInput)
        submitButton = findViewById(R.id.submitButton)
        auth = Firebase.auth
    }

    fun createUser(view: View){
        var email = emailInput.text.toString()
        var password = passwordInput.text.toString()
        if(email.isEmpty() || password.isEmpty()){
            return Toast.makeText(baseContext, "გთხოვთ შეავსეთ ყველა ველი",
                    Toast.LENGTH_LONG).show()
        }
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        val user = auth.currentUser
                        Toast.makeText(baseContext, "მომხმარებელი შეიქმნა", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(baseContext, "მომხარებელი ვერ შეიქმნა \n" + (task.exception?.localizedMessage
                                ?: ""),
                                Toast.LENGTH_LONG).show()
                    }

                }
    }
}