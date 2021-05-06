
package com.example.marvel.data.model

data class Data (

	val offset : String,
	val limit : String,
	val total : String,
	val count : String,
	val results : List<Results>
)