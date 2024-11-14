package br.com.analuisa936_juliocesar94543.viewModel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import br.com.analuisa936_juliocesar94543.R
import br.com.analuisa936_juliocesar94543.model.DicaModel
import android.widget.Filter
import br.com.analuisa936_juliocesar94543.data.DicaDao

class DicasAdapter(private val onDicaRemoved: (DicaModel) -> Unit) : RecyclerView.Adapter<DicasAdapter.DicaViewHolder>() {

    private var dicas = listOf<DicaModel>()

    inner class DicaViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titulo: TextView = view.findViewById(R.id.titulo)
        val desc: TextView = view.findViewById(R.id.descricao)
        val deleteIcon: ImageView = view.findViewById(R.id.deleteIcon)

        fun bind(dica: DicaModel) {
            titulo.text = dica.titulo
            desc.text = dica.descricao

            deleteIcon.setOnClickListener{
                onDicaRemoved(dica)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DicaViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.dica_layout, parent, false)
        return DicaViewHolder(view)
    }

    override fun getItemCount(): Int = dicas.size

    override fun onBindViewHolder(holder: DicaViewHolder, position: Int) {
        val dica = dicas[position]
        holder.bind(dica)

        holder.itemView.setOnClickListener {
            val detalhes = "Título: ${dica.titulo}\nDescrição: ${dica.descricao}"
            Toast.makeText(holder.itemView.context, detalhes, Toast.LENGTH_LONG).show()
        }
    }

    fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence?): FilterResults {
                val filteredList = if (charSequence.isNullOrEmpty()) {
                    dicas
                } else {
                    val filterPattern = charSequence.toString().lowercase().trim()
                    dicas.filter { it.titulo.lowercase().contains(filterPattern) || it.descricao.lowercase().contains(filterPattern) }
                }
                val results = FilterResults()
                results.values = filteredList
                return results
            }

            override fun publishResults(
                charSequence: CharSequence?,
                filterResults: FilterResults?
            ) {
                dicas = filterResults?.values as List<DicaModel>
                notifyDataSetChanged()
            }
        }
    }


    fun updateDicas(newDicas: List<DicaModel>) {
        dicas = newDicas
        notifyDataSetChanged()
    }
}
