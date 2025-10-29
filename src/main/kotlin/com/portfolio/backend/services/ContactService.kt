package com.portfolio.backend.services


import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.portfolio.backend.dtos.ContactDto
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.springframework.stereotype.Service
import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException
import java.util.concurrent.TimeUnit

@Service
class ContactService {

    data class WhatsAppMessageRequest(
        val session_code: String,
        val number: String,
        val message: String,
    )

    data class WhatsAppMessageResponse(
        val success: Boolean,
        val message: String,
        val result: Result?
    )

    data class Result(
        val success: Boolean,
        val message_id: String?,
    )

    private val client = OkHttpClient.Builder()
        .connectTimeout(CONNECTION_TIMEOUT.toLong(), TimeUnit.MILLISECONDS)
        .readTimeout(READ_TIMEOUT.toLong(), TimeUnit.MILLISECONDS)
        .build()

    private val mapper = jacksonObjectMapper()

    fun receiveAndSendMessageToWhatsapp(contactDto: ContactDto): String {
        val composed = "New portfolio contact:\n\nName: ${contactDto.name}\n\nEmail: ${contactDto.email}\n\nMessage: ${contactDto.message}"

        val payload = WhatsAppMessageRequest(
            session_code = SESSION_CODE,
            number = PHONE_NUMBER,
            message = composed
        )

        val json = mapper.writeValueAsString(payload)
        val requestBody = json.toRequestBody("application/json".toMediaType())

        val request = Request.Builder()
            .url(BASE_URL)
            .addHeader("Authorization", "Bearer $API_KEY")
            .addHeader("Content-Type", "application/json")
            .post(requestBody)
            .build()

        client.newCall(request).execute().use { response ->
            val responseBodyString = response.body?.string()
            if (!response.isSuccessful) {
                val code = response.code
                val bodyText = responseBodyString ?: "<no body>"
                val status = try { HttpStatus.valueOf(code) } catch (e: IllegalArgumentException) { HttpStatus.INTERNAL_SERVER_ERROR }
                throw ResponseStatusException(status, "Failed to send message to WhatsApp: HTTP $code - $bodyText")
            }

            val responseBody = responseBodyString
                ?: throw Exception("Empty response from WhatsApp API")

            val parsedResponse: WhatsAppMessageResponse = mapper.readValue(responseBody)
            return parsedResponse.message
        }
    }

    companion object {
        // Just a simple api for sending whatsapp message (Not reliable, just for testing)
        const val BASE_URL = "https://wap.moviez.app/send-message"
        const val CONNECTION_TIMEOUT = 30000
        const val READ_TIMEOUT = 30000
        const val SESSION_CODE = "#c716a08342dc"
        const val API_KEY = "wa_VsV4jrbgSDjbFboxsh3oiVBahlk6GtZrH9V8SfKi"
        const val PHONE_NUMBER = "254720243383"
    }
}

