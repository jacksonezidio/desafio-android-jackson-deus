package com.example.marvel.data.model

data class Comics (

	val available : String,
	val returned : String,
	val collectionURI : String,
	val items : List<Items>
)