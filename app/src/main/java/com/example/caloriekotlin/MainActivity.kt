package com.example.caloriekotlin

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val userLogin: EditText = findViewById(R.id.user_login)
        val userEmail: EditText = findViewById(R.id.user_email)
        val userPass: EditText = findViewById(R.id.user_pass)
        val userHeight: EditText = findViewById(R.id.user_height)
        val userWeight: EditText = findViewById(R.id.user_weight)
        val switchGen: Switch = findViewById(R.id.switch_gen)
        val switchAct: Switch = findViewById(R.id.switch_act)
        val button: Button = findViewById(R.id.button_reg)
        val linkToAuth: TextView = findViewById(R.id.link_to_auth)

        linkToAuth.setOnClickListener {
            val intent = Intent(this, AuthActivity::class.java)
            startActivity(intent)
        }

        button.setOnClickListener {
            val login = userLogin.text.toString().trim()
            val email = userEmail.text.toString().trim()
            val pass = userPass.text.toString().trim()
            val height = userHeight.text.toString().trim()
            val weight = userWeight.text.toString().trim()
            val gender = if (switchGen.isChecked) "M" else "F"
            val activityLevel = if (switchAct.isChecked) "High" else "Low"

            if (login.isEmpty() || email.isEmpty() || pass.isEmpty() || height.isEmpty() || weight.isEmpty()) {
                Toast.makeText(this, "Не все поля заполнены", Toast.LENGTH_LONG).show()
            } else {
                val user = User(login, email, pass, height.toInt(), weight.toInt(), gender, activityLevel)

                val db = DbHelper(this, null)
                db.addUser(user)
                Toast.makeText(this, "Пользователь $login добавлен", Toast.LENGTH_LONG).show()

                userLogin.text.clear()
                userEmail.text.clear()
                userPass.text.clear()
                userHeight.text.clear()
                userWeight.text.clear()
                switchGen.isChecked = false
                switchAct.isChecked = false
            }
        }
    }
}