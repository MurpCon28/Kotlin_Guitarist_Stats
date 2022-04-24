package com.example.guitaristsstats.data

import android.media.Image
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.guitaristsstats.NEW_GUITAR_ID
import kotlinx.android.parcel.Parcelize
import kotlinx.android.synthetic.main.editor_fragment.*
import java.util.*

@Parcelize
//names the table in the datbase
@Entity(tableName = "guitarists")
data class GuitarEntity (
    //makes auto ide for database object
    @PrimaryKey(autoGenerate = true)
    var id:Int,
    var guitaristImage: String,
    var name: String,
    var dob: String,
    var band: String,
    var genre: String,
    var guitarType: String,
//    var guitarPhoto: Image,
//    var dead: Boolean,
    var status: String


) : Parcelable {
    //constructor(): this(NEW_GUITAR_ID, "", "", "", "", "", "", "")
    //constructor(guitaristPhoto:Bitmap, name: String, dob: String, band: String, genre: String, guitarType: String, status: String): this(NEW_GUITAR_ID, guitaristPhoto, name, dob, band, genre, guitarType, status)
    constructor(): this(NEW_GUITAR_ID, "", "", "", "", "", "", "")
    constructor(guitaristImage: String, name: String, dob: String, band: String, genre: String, guitarType: String, status: String): this(NEW_GUITAR_ID, guitaristImage, name, dob, band, genre, guitarType, status)
}
