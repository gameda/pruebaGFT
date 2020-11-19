package com.dgm.pruebagft.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.dgm.pruebagft.R
import com.dgm.pruebagft.databinding.ActivityLoginBinding
import com.dgm.pruebagft.viewmodel.MainViewModel
import com.dgm.retrofit_corutines_example.utils.Status
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.login.*
import kotlinx.android.synthetic.main.register.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class LoginActivity : AppCompatActivity() {

    private val loginViewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        DataBindingUtil.setContentView<ActivityLoginBinding>(this, R.layout.activity_login)
            .apply {
                this.lifecycleOwner = this@LoginActivity
                this.viewmodel = loginViewModel
            }


        loginActionBttn.setOnClickListener {
            optionView.visibility = GONE
            loginForm.visibility = VISIBLE
        }

        registerActionBttn.setOnClickListener {
            optionView.visibility = GONE
            registerForm.visibility = VISIBLE
        }


        registerBttn.setOnClickListener {
            loginViewModel.onRegisterClick(firstnameET.text.toString(), lastnameET.text.toString(), emailET.text.toString(), passwordET.text.toString()
            ).observe(this, Observer {
                it.let {
                    when (it.status) {
                        Status.SUCCESS -> {
                            Toast.makeText(this, "Usuario creado exitosamente", Toast.LENGTH_LONG).show()
                            registerForm.visibility = GONE
                            loginForm.visibility = VISIBLE
                            progressBar.visibility = GONE

                        }
                        Status.ERROR -> {
                            progressBar.visibility = GONE
                            Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                        }
                        Status.LOADING -> {
                            progressBar.visibility = VISIBLE

                        }
                    }
                }
            })
        }

        loginBttn.setOnClickListener {
            loginViewModel.onLoginClick(login_email.text.toString(), login_password.text.toString())
                .observe(this, Observer {
                    it.let {
                        when (it.status) {
                            Status.SUCCESS -> {
                                progressBar.visibility = GONE
                                val intent = Intent(this, MainActivity::class.java).apply {
                                    putExtra("name", it.data)
                                }
                                startActivity(intent)

                            }
                            Status.ERROR -> {
                                progressBar.visibility = GONE
                                Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                            }
                            Status.LOADING -> {
                                progressBar.visibility = VISIBLE

                            }
                        }
                    }
                })
        }

    }
}