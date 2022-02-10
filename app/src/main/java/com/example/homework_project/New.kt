package com.example.homework_project

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class New(val id: Int, val creationTime: String, val title: String, val text: String) : Parcelable {

}