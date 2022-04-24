package com.example.guitaristsstats

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.guitaristsstats.data.AppDatabase
import com.example.guitaristsstats.data.GuitarDao
import com.example.guitaristsstats.data.GuitarEntity
import com.example.guitaristsstats.data.SampleDataProvider
import org.junit.After

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before

@RunWith(AndroidJUnit4::class)
class DatabseTest {

    private lateinit var dao: GuitarDao
    private lateinit var database: AppDatabase

    //before test the database is setup
    @Before
    fun createDb() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        database = Room.inMemoryDatabaseBuilder(appContext, AppDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        dao = database.guitarDao()!!
    }

    @Test
    fun createGuitarist() {
        dao.insertAll(SampleDataProvider.getGuitars())
        val count = dao.getCount()
        assertEquals(count, SampleDataProvider.getGuitars().size)
    }

    @Test
    fun insertGuitar() {
        val guitar = GuitarEntity()
        guitar.name = "Some Guitarist text"
        dao.insertGuitar(guitar)
        val savedGuitar = dao.getGuitarById(1)
        assertEquals(savedGuitar?.id?: 0, 1)
    }

    //after test the databse is closed
    @After
    fun closeDb() {
        database.close()
    }
}