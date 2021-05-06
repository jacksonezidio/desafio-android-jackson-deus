
package com.example.marvel.data.model


data class MarvelResponse (

	val code : String,
	val status : String,
	val copyright : String,
	val attributionText : String,
	val attributionHTML : String,
	val data : Data,
	val etag : String
)