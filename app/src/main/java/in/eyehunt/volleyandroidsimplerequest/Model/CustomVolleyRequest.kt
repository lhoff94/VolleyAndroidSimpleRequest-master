package `in`.eyehunt.volleyandroidsimplerequest.Model

import com.android.volley.AuthFailureError
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest

import java.util.HashMap

/**
 * Created by phu on 21.09.17.
 */

class CustomVolleyRequest(method: Int, url: String,
                          listener: Response.Listener<String>,
                          errorListener: Response.ErrorListener) : StringRequest(method, url, listener, errorListener) {

    @Throws(AuthFailureError::class)
    override fun getHeaders(): Map<String, String> {
        val headers = HashMap<String, String>()
        val auth = "Basic dmV0RG9jdG9yOilMbS9kK3tESk4sOEZOU3IrbSwoMlNUI0RoaCItUkpI"
        headers["Content-Type"] = "application/json"
        headers["Authorization"] = auth
        return headers
    }

}