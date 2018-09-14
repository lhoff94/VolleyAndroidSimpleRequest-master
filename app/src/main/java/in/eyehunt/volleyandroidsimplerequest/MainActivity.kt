package `in`.eyehunt.volleyandroidsimplerequest

import `in`.eyehunt.volleyandroidsimplerequest.Model.ServerRequest
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.android.volley.toolbox.Volley
import de.fu_berlin.mi.tierklinik.VolleyRequester
import org.json.JSONArray
import org.json.JSONObject


class MainActivity : AppCompatActivity() {
    private var textView: TextView? = null
    val presenter: MainPresenter by lazy{MainPresenter()}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textView = findViewById<TextView>(R.id.tv_users)



        getUsers()
    }



    // function for network call
    fun getUsers(){
        // Instantiate the RequestQueue.
        val queue = VolleyRequester.getInstance(this.applicationContext).requestQueue
        val url: String = "https://kis20kapp.vetkis.fu-berlin.de:8084/swp/tierklinik/patientRecord/owner?ownerFirtsName=Nico"
        presenter.evaluateForm(queue)
        showResponses(serverRequestModel.responseJson)
    }

    fun showResponses(serverAnswer: JSONObject?){
        val jsonArray: JSONArray = serverAnswer!!.getJSONArray("content")
        var str_user: String = ""
        for (i in 0 until jsonArray.length()) {
            val jsonInner: JSONObject = jsonArray.getJSONObject(i)
            str_user = str_user + "\n" + jsonInner.get("login")

        }
        textView!!.text = "response : $str_user "

    }

}
