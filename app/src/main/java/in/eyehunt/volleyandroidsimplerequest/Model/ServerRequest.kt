package `in`.eyehunt.volleyandroidsimplerequest.Model

import android.util.Log
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONObject
import java.security.KeyManagementException
import java.security.NoSuchAlgorithmException
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import javax.net.ssl.HttpsURLConnection
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

class ServerRequest {
    lateinit var responseJson: JSONObject

    fun disableSSLCertificateChecking() {
        val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
            override fun getAcceptedIssuers(): Array<X509Certificate>? {
                return null
            }

            @Throws(CertificateException::class)
            override fun checkClientTrusted(arg0: Array<X509Certificate>, arg1: String) {
                // Not implemented
            }

            @Throws(CertificateException::class)
            override fun checkServerTrusted(arg0: Array<X509Certificate>, arg1: String) {
                // Not implemented
            }
        })

        try {
            val sc = SSLContext.getInstance("TLS")

            sc.init(null, trustAllCerts, java.security.SecureRandom())

            HttpsURLConnection.setDefaultSSLSocketFactory(sc.socketFactory)
        } catch (e: KeyManagementException) {
            e.printStackTrace()
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        }

    }

    fun getJsonFromServer(queue: RequestQueue, url: String): JSONObject {
        sendRequestToServer(queue, url)
        return responseJson
    }

    fun sendRequestToServer(queue: RequestQueue, url: String) {
        // Instantiate the RequestQueue.
        // Request a string response from the provided URL.

        val stringReq = CustomVolleyRequest(Request.Method.GET, url,
                Response.Listener<String> { response ->

                    var strResp = response.toString()
                    responseJson= JSONObject (strResp)
                },
                Response.ErrorListener { error ->
                    Log.e("VOLLEY", error.message)
                })
        queue.add(stringReq)
    }

}