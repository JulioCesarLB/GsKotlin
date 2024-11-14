package br.com.analuisa936_juliocesar94543

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class IntegrantesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_integrantes)


        val nome01TextView: TextView = findViewById(R.id.nomeTextView1)
        val rm01TextView: TextView = findViewById(R.id.rmTextView1)

        val nome01 = "Ana Luísa Bernardi Elias"
        val rm01 = "93686"

        val nome02TextView: TextView = findViewById(R.id.nomeTextView2)
        val rm02TextView: TextView = findViewById(R.id.rmTextView2)

        val nome02 = "Julio Cesar Lopes Batista"
        val rm02 = "94543"

        nome01TextView.text = "Nome: $nome01"
        rm01TextView.text = "RM: $rm01"
        nome02TextView.text = "Nome: $nome02"
        rm02TextView.text = "RM: $rm02"
    }
}