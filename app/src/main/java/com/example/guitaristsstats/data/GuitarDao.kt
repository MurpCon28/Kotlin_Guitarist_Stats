package com.example.guitaristsstats.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface GuitarDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGuitar(guitar: GuitarEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(guitars: List<GuitarEntity>)

    //uses sql query to find data from the database, gets guitars by name in accending order
    @Query("SELECT * FROM guitarists ORDER BY name ASC")
    fun getAll(): LiveData<List<GuitarEntity>>

    @Query("SELECT * FROM guitarists WHERE id = :id")
    fun getGuitarById(id: Int):GuitarEntity?

    @Query("SELECT COUNT(*) FROM guitarists")
    fun getCount(): Int

    //deletes the selected entiy from the database
    @Delete
    fun deleteGuitars(selectedGuitars: List<GuitarEntity>): Int

    //when this function is called it emptys the database delete everything from it
    @Query("DELETE FROM guitarists")
    fun deleteAll():Int

    //whatever guitarist is passed in delete it from the database
    @Delete
    fun deleteGuitar(guitar: GuitarEntity)
}