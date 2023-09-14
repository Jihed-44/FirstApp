package com.example.firstProject

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.isDigitsOnly

class Main : ComponentActivity() {
	override fun onCreate(savedInstanceState : Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			MaterialTheme {
				MyContacts()
			}
		}
	}
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyContacts() {
	var count by remember {
		mutableStateOf(1)
	}
	var contactName by remember {
		mutableStateOf("")
	}
	var contactNumber by remember {
		mutableStateOf("")
	}
	var allContacts by remember {
		mutableStateOf(listOf<String>())
	}
	val context = LocalContext.current
	Surface(
		modifier = Modifier.fillMaxSize()
	) {
		Card(
			modifier = Modifier
				.padding(4.dp)
				.height(390.dp)
				.width(200.dp),
			shape = RoundedCornerShape(15.dp),
			elevation = CardDefaults.cardElevation(
				7.dp
			)
		) {
			Column(
				modifier = Modifier
					.fillMaxSize()
					.background(color = Color.White),
				verticalArrangement = Arrangement.Top,
				horizontalAlignment = Alignment.End
			) {
				Text(
					text = "Please Enter Your Contact's Info :",
					fontSize = 20.sp,
					color = Color (0xFF7F00FF),
					fontWeight = FontWeight.ExtraBold,
					textDecoration = TextDecoration.Underline,
					modifier = Modifier
						.align(Alignment.CenterHorizontally)
						.padding(16.dp)
				)
				Row(
					modifier = Modifier.offset((-260).dp, 0.dp),
					horizontalArrangement = Arrangement.Start,
					verticalAlignment = Alignment.Top,
				) {
					Text(
						text = "Enter The Name :",
						modifier = Modifier
							.padding(16.dp)
							.offset(x = 30.dp, y = 0.dp),
						fontWeight = FontWeight.Bold,
						textDecoration = TextDecoration.Underline,
					)
				}
				Row(
					modifier = Modifier.offset(x = (-100).dp, y = 0.dp)
				) {
					OutlinedTextField(
						label = { Text("maximum 20 letters") },
						placeholder = { Text("name.. ") },
						value = contactName,
						onValueChange = { text ->
							contactName = text
						},
						leadingIcon = {
							Icon(
								imageVector = Icons.Default.Person, contentDescription = null
							)
						},
					)
				}
				Row(
					modifier = Modifier.offset((-248).dp, 0.dp),
					horizontalArrangement = Arrangement.Start,
					verticalAlignment = Alignment.Top
				) {
					Text(
						text = "Enter The Number :",
						fontWeight = FontWeight.Bold,
						modifier = Modifier
							.padding(16.dp)
							.offset(x = 35.dp, y = 0.dp),
						textDecoration = TextDecoration.Underline,
					)
				}
				Row(
					modifier = Modifier.offset(x = (-100).dp, y = 0.dp)
				) {
					OutlinedTextField(
						label = { Text("8 numbers") },
						placeholder = { Text("number..") },
						value = contactNumber,
						onValueChange = { text ->
							contactNumber = text
						},
						leadingIcon = {
							Icon(
								imageVector = Icons.Default.Phone, contentDescription = null
							)
						},
					)
				}
				Button(modifier = Modifier
					.width(220.dp)
					.align(Alignment.CenterHorizontally)
					.offset(x = 0.dp, y = 10.dp), onClick = {            // if the name is empty
					if (contactName.isBlank()) {
						Toast.makeText(
							context, "Please Enter Your Contact's Name", Toast.LENGTH_LONG
						).show()
					}            // if the name is invalid
					else if (contactNumber.length > 20) {
						Toast.makeText(context, "Please Enter a Shorter Name", Toast.LENGTH_LONG)
							.show()
					}            // if the number is empty
					else if (contactNumber.isBlank()) {
						Toast.makeText(
							context, "Please Enter Your Contact's Number", Toast.LENGTH_LONG
						).show()
					}            // if the number is invalid
					else if (contactNumber.length != 8 || !contactNumber.isDigitsOnly()) {
						Toast.makeText(
							context, "Please Enter a Valid Number ", Toast.LENGTH_LONG
						).show()
					} else {
						count++
						allContacts += "${count - 1} ${contactName}  $contactNumber"
						contactName = ""
						contactNumber = ""
					}
				}) {
					Text(text = "Add a new contact N°: $count")
				}
				Spacer(
					modifier = Modifier
						.fillMaxWidth()
						.height(10.dp)
				)
				// the contact display
				LazyColumn {
					items(allContacts) { currentContact ->
						ContactDisplay(currentContact)
					}
				}
			}
		}
	}
}
@Composable
private fun ContactDisplay(currentContact : String) {
	Box(
		modifier = Modifier
			.padding(7.dp)
			.clip(RoundedCornerShape(12.dp))
			.clickable { }
			.fillMaxWidth()
			.border(
				width = 2.dp, Color (0xFF7F00FF), shape = RoundedCornerShape(12.dp)
			)
			.background(color = Color.LightGray, shape = RoundedCornerShape(12.dp))
	) {
		Text(
			fontSize = 20.sp,
			fontWeight = FontWeight.Bold,
			color = Color.Black,
			text = currentContact,
			modifier = Modifier
				.padding(start = 16.dp, top = 16.dp, end = 250.dp, bottom = 16.dp)
				.fillMaxWidth()
		)
	}
	Spacer(
		modifier = Modifier
			.fillMaxWidth()
			.height(10.dp)
	)
}
@Preview
@Composable
fun MyContactsPreview() {
	MaterialTheme {
		MyContacts()
	}

}