package com.example.culturemotelg.network

import android.content.Context
import android.widget.Toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response
import java.net.UnknownHostException

fun makePayment(context: Context, phoneNumber: String, amount: String) {
    val paymentRequest = PaymentRequest(phoneNumber, amount)

    // Launching the payment process inside a coroutine
    CoroutineScope(Dispatchers.Main).launch {
        try {
            // Making the network call on the IO thread
            val response = withContext(Dispatchers.IO) {
                RetrofitClient.apiService.makePayment(paymentRequest)
            }

            // Handle the response based on the success or failure
            if (response.isSuccessful) {
                Toast.makeText(context, response.body()?.message, Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(context, "Payment failed: ${response.message()}", Toast.LENGTH_LONG).show()
            }
        } catch (e: UnknownHostException) {
            // Handle network errors, e.g., when the device can't resolve the host
            Toast.makeText(context, "Network error: Unable to connect to server", Toast.LENGTH_LONG).show()
        } catch (e: Exception) {
            // Catch any other general exceptions
            Toast.makeText(context, "Error: ${e.message}", Toast.LENGTH_LONG).show()
        }
    }
}
