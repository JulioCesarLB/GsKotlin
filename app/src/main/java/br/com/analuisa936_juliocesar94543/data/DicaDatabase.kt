package br.com.analuisa936_juliocesar94543.data

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.analuisa936_juliocesar94543.model.DicaModel
import br.com.analuisa936_juliocesar94543.data.DicaDao

@Database(entities = [DicaModel::class], version = 1)
abstract class DicaDatabase : RoomDatabase(){

    abstract fun dicaDao(): DicaDao
}
