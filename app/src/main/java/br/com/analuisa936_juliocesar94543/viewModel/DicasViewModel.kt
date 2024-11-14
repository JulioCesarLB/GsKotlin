package br.com.analuisa936_juliocesar94543.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import br.com.analuisa936_juliocesar94543.data.DicaDatabase

import br.com.analuisa936_juliocesar94543.data.DicaDao
import br.com.analuisa936_juliocesar94543.model.DicaModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DicasViewModel (application: Application) : AndroidViewModel (application) {

    private val dicaDao: DicaDao
    val dicasLiveData: LiveData<List<DicaModel>>

    init {
        val database = Room.databaseBuilder(
            getApplication(),
            DicaDatabase::class.java,
            "dicas_database"
        ).build()

        dicaDao = database.dicaDao()
        dicasLiveData = dicaDao.getAll()
    }

    fun addDica(titulo: String, descricao: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val newDica = DicaModel(titulo = titulo, descricao = descricao)
            dicaDao.insert(newDica)
        }
    }

    fun removeDica(dica: DicaModel){
        viewModelScope.launch(Dispatchers.IO){
            dicaDao.delete(dica)
        }
    }



}