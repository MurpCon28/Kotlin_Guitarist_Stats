package com.example.guitaristsstats.data

import java.util.*

class SampleDataProvider {

    companion object{
        private val sampleText1 = "Jimi Hendrix"
        private val sampleText2 = "The Edge a\nline feed"
        private val sampleText3 = """
        |Tom Morello uses lots of pedal effects and other techniques on his guitar to make unique sounds for songs
        """.trimMargin()

        private fun getDate(diff: Long): Date {
            return Date(Date().time + diff)
        }

        fun getGuitars() = arrayListOf(
//            GuitarEntity(1, getDate(0), sampleText1),
//            GuitarEntity(2, getDate(1), sampleText2),
//            GuitarEntity(3, getDate(2), sampleText3)

            //all sample data for the database
            GuitarEntity("guitarist01","Kurt Cobain", "20-02-1967", "Nirvana", "Punk Rock", "Fender USA Standard Stratocaster Left Handed - Sunburst\nLight Blue Fender Mustang\nFender Road Worn Jaguar Electric Guitar\nMartin D-18E Acoustic-Electric Guitar", "Dead"),
            GuitarEntity("guitarist02","Dave Grohl", "14-01-1969", "Foo Fighters\nNirvana\nQueen Of The Stone Age\nThem Crooked Vultures", "Rock\nHard Rock", "Gibson Trini Lopez Standard Custom Reissue Electric Guitar\nGibson DG-335 Electric Guitar", "Alive"),
            GuitarEntity("guitarist03","Jack White", "09-07-1975", "The White Stripes\nThe Raconteurs", "Rock", "Gretsch Rancher Falcon “Rita”\n1964 Montgomery Ward JB Hutto Airline\nDiPinto Mach 4", "Alive"),
            GuitarEntity("guitarist04","Jimi Hendrix", "27-11-1942", "The Jimi Hendrix Experience", "Rock", "White Fender Stratocaster", "Dead"),
            GuitarEntity("guitarist05","The Edge", "08-08-1961", "U2", "Rock\nAlternative Rock", "Black Fender Stratocaster\nGibson Explorers\nCream & Black Les Paul", "Alive"),
            GuitarEntity("guitarist06","Tom Morello", "30-05-1964", "Rage Against The Machine\nAudioslave\nProphets Of Rage", "Rock\nAlternative Metal", "Fender Telecaster", "Alive"),
            GuitarEntity("guitarist07","Eric Clapton", "30-03-1944", "Cream\nDerek and the Dominos\nSolo", "Rock\nBlues", "1956 Fender Stratocaster “Brownie\nMartin D-45", "Alive"),
            GuitarEntity("guitarist08","Noel Gallagher", "29-05-1967", "Oasis\nSolo", "Rock\nBritpop\nIndie Rock", "Gibson ES-355 Electric Guitar", "Alive"),
            GuitarEntity("guitarist09","Johhny Marr", "31-10-1963", "The Smiths\nModest Mouse\nThe The", "Britpop\nIndie Rock", "Rickenbacker 330 Electric Guitar", "Alive"),
            GuitarEntity("guitarist10","Slash", "23-07-1965", "Guns'n'Roses\nVelvet Revolver", "Rock", "Gibson Slash J-45, November Burst\nGibson Les Paul Slash Standard GT\nGibson Slash Les Paul Standard Limited Edition Vermillion Burst", "Alive"),
            GuitarEntity("guitarist11","Dolores O'Riordan", "06-09-1971", "The Cranberries", "Rock", "Gibson ES-335 Electric Guitar\nGibson SG Standard Electric Guitar\nTaylor GS6e", "Dead"),
            GuitarEntity("guitarist12","Peter Green", "29-10-1946", "Fleetwood Mac", "Rock\nBlues", "Gibson 1959 Les Paul Electric Guitar\nTanglewood Memphis AS-35 Guitar\nIbanez Iceman Electric Guitar", "Dead")
        )
    }
}