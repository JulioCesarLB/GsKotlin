package br.com.analuisa936_juliocesar94543

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import br.com.analuisa936_juliocesar94543.viewModel.DicasAdapter
import br.com.analuisa936_juliocesar94543.viewModel.DicasViewModel
import br.com.analuisa936_juliocesar94543.viewModel.DicasViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: DicasViewModel
    private lateinit var searchView: SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val botaoIntegrantes: Button = findViewById(R.id.buttonIntegrantes)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Eco Dicas"

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val dicasAdapter = DicasAdapter { dica -> viewModel.removeDica(dica) }
        recyclerView.adapter = dicasAdapter




        searchView = findViewById(R.id.searchView)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                dicasAdapter.getFilter().filter(query)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                dicasAdapter.getFilter().filter(newText)
                return false
            }
        })

        val tituloInput = findViewById<EditText>(R.id.tituloInput)
        val descInput = findViewById<EditText>(R.id.descInput)

        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            val titulo = tituloInput.text.toString()
            val descricao = descInput.text.toString()

            if (titulo.isEmpty()) {
                tituloInput.error = "Preencha o título"
                return@setOnClickListener
            }

            if (descricao.isEmpty()) {
                descInput.error = "Preencha a descrição"
                return@setOnClickListener
            }

            viewModel.addDica(titulo, descricao)

            tituloInput.text.clear()
            descInput.text.clear()
        }

        botaoIntegrantes.setOnClickListener {
            val intent = Intent(this, IntegrantesActivity::class.java)
            startActivity(intent)
        }


        val viewModelFactory = DicasViewModelFactory(application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(DicasViewModel::class.java)

        viewModel.dicasLiveData.observe(this) { dicas ->
            dicasAdapter.updateDicas(dicas)
        }
    }
}
