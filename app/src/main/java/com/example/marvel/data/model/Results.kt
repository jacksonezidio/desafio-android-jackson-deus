
package com.example.marvel.data.model

data class Results (

	val id : String,
	val name : String,
	val description : String,
	val modified : String,
	val resourceURI : String,
	val urls : List<Urls>,
	val thumbnail : Thumbnail,
	val comics : Comics,
	val stories : Stories,
	val events : Events,
	val series : Series
)