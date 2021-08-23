package com.ram.kotlin.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.infyapps.kotlin.model.InputValidations
import com.ram.kotlin.R
import com.ram.kotlin.db.Users
import com.ram.kotlin.viewmodel.UsersViewModel
import kotlinx.android.synthetic.main.activity_add_user.*


class AddUserActivity : AppCompatActivity() {
    private val activity = this@AddUserActivity
    lateinit var viewModel: UsersViewModel
    private lateinit var inputValidations: InputValidations

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_user)

        val actionBar = supportActionBar
        actionBar!!.title = "Add User"

        // initializing the objects
        initObjects()


        btnSave.setOnClickListener {
            saveUser()
        }


    }

    private fun initObjects() {
        viewModel = ViewModelProviders.of(this).get(UsersViewModel::class.java)
        inputValidations = InputValidations(activity)
    }

    //Insert the data
    private fun saveUser() {

        val name = editTextName.text.toString().trim()
        val email = editTextEmail.text.toString().trim()
        val number = editTextPhone.text.toString().trim()

        //To check the name is empty or not
        if (!inputValidations!!.isInputEditTextFilled(
                editTextName!!,
                textInputUserName!!,
                getString(R.string.name_error)
            )
        ) {
            return
        }

        //To check the number is empty or not
        if (!inputValidations!!.isInputEditTextFilled(
                editTextPhone!!,
                textInputUserMobile!!,
                getString(R.string.number_error)
            )
        ) {
            return
        }

        //To check the number is valid or not
        if (!inputValidations!!.isInputEditTextNumber(
                editTextPhone!!,
                textInputUserMobile!!,
                getString(R.string.valid_number_error)
            )
        ) {
            return
        }

        //To check the email is empty or not
        if (!inputValidations!!.isInputEditTextFilled(
                editTextEmail!!,
                textInputUserEmail!!,
                getString(R.string.email_error)
            )
        ) {
            return
        }

        //To check the email is valid or not
        if (!inputValidations!!.isInputEditTextEmail(
                editTextEmail!!,
                textInputUserEmail!!,
                getString(R.string.valid_email_error)
            )
        ) {
            return
        }

        if (!name.isEmpty() && !email.isEmpty() && !number.isEmpty()) {
            val user = Users(0, name, email, number)
            viewModel.insertUser(user)

        }


        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent)

        emptyInputEditText()

    }


    // This method is to empty all input edit text
    private fun emptyInputEditText() {
        editTextName.setText("")
        editTextEmail.setText("")
        editTextPhone.setText("")
    }

}