package com.dgm.pruebagft.ui

import android.Manifest
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.dgm.pruebagft.R
import com.dgm.pruebagft.repository.remote.model.CatalogResponse
import com.dgm.pruebagft.repository.remote.model.DetailsResponse
import com.dgm.pruebagft.ui.adapter.CardAdapter
import com.dgm.pruebagft.ui.adapter.InfoAdapter
import com.dgm.pruebagft.viewmodel.MainViewModel
import com.dgm.retrofit_corutines_example.utils.Resource
import com.dgm.retrofit_corutines_example.utils.Status
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*


class MainActivity : AppCompatActivity(), CardAdapter.OnItemClickListener {


    private val mainViewModel by viewModel<MainViewModel>()
    private lateinit var cardAdapter : CardAdapter
    private lateinit var cardList : ArrayList<CatalogResponse.CardResponse.CardDetail>

    private lateinit var fusedLocationClient: FusedLocationProviderClient



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)


        val name = intent.getStringExtra("name")
        labelTV?.text = "Bienvenido $name"
        buildRecyclerView()

        consutaBttn.setOnClickListener {
            getCardInfo()
        }

        backBttn.setOnClickListener {
            mainView.visibility = View.VISIBLE
            cardsView.visibility = View.GONE
        }

    }

    private fun buildRecyclerView() {
        mainViewModel.getCatalog().observe(this, Observer {
            it.let {
                when (it.status) {
                    Status.SUCCESS -> {
                        progressBar.visibility = View.GONE
                        recycleViewInit(it)
                    }
                    Status.ERROR -> {
                        progressBar.visibility = View.GONE
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        progressBar.visibility = View.VISIBLE

                    }
                }
            }
        })
    }

    private fun recycleViewInit(resource: Resource<CatalogResponse?>) {

        cardList = resource.data?.response?.type_cards!!
        val linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        myRecycleView.setHasFixedSize(true)
        myRecycleView.layoutManager = linearLayoutManager

        cardAdapter = CardAdapter(cardList, this)
        myRecycleView.adapter = cardAdapter
    }

    private fun addCard(card: CatalogResponse.CardResponse.CardDetail){
        mainViewModel.addCard(card).observe(this, Observer {
            it.let {
                when (it.status) {
                    Status.SUCCESS -> {
                        Toast.makeText(applicationContext, "Tarjeta agregada correctamente", Toast.LENGTH_SHORT).show()
                        progressBar.visibility = View.GONE
                    }
                    Status.ERROR -> {
                        progressBar.visibility = View.GONE
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        progressBar.visibility = View.VISIBLE


                    }
                }
            }
        })

    }

    private fun getCardInfo(){
        mainViewModel.getCardInfo().observe(this, Observer {
            it.let {
                when (it.status) {
                    Status.SUCCESS -> {
                        progressBar.visibility = View.GONE
                        it.data?.cardList?.let { list -> showCards(list) }

                    }
                    Status.ERROR -> {
                        progressBar.visibility = View.GONE
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        progressBar.visibility = View.VISIBLE


                    }
                }
            }
        })

    }

    fun showCards(cardList: ArrayList<DetailsResponse.Card>){
        mainView.visibility = View.GONE
        cardsView.visibility = View.VISIBLE

        val linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        InfoRecycleView.setHasFixedSize(true)
        InfoRecycleView.layoutManager = linearLayoutManager

        val infoAdapter = InfoAdapter(cardList)
        InfoRecycleView.adapter = infoAdapter
    }

    override fun onItemClick(position: Int) {
        addCard(cardList[position])

    }
}