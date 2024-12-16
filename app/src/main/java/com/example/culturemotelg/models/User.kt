package com.example.culturemotelg.models

data class SignupModel(
    var firstName: String = "",
    var email: String = "",
    var password: String = "",
    var confirmPassword: String = "",
    var userId: String = ""
)