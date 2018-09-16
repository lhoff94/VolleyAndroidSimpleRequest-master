package `in`.eyehunt.volleyandroidsimplerequest

import `in`.eyehunt.volleyandroidsimplerequest.Model.ServerRequest
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import org.json.JSONArray
import org.json.JSONObject
import `in`.eyehunt.volleyandroidsimplerequest.R
import android.content.Intent
import android.view.View
import android.webkit.RenderProcessGoneDetail
import android.widget.ProgressBar
import java.util.*


class MainActivity : AppCompatActivity() {
    private var textView: TextView? = null
    val presenter: MainPresenter by lazy{MainPresenter(this)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textView = findViewById<TextView>(R.id.tv_users)
        val searchButton = findViewById<Button>(R.id.buttonSearch) as Button
        val progressBar: ProgressBar = findViewById<ProgressBar>(R.id.progressBar) as ProgressBar
        presenter.evaluateForm(
                "",
                "",
                "",
                "81036",
                "")
    }

    fun disableButton(button : Button){
        button.visibility = View.GONE
    }
    fun enableButton(button : Button){
        button.visibility = View.VISIBLE
    }
    fun disableProgressBar(progressBar : Button){
        progressBar.isEnabled = false
        progressBar.visibility = View.GONE
    }
    fun enableProgressBar(progressBar : Button){
        progressBar.isEnabled = true
        progressBar.visibility = View.VISIBLE
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
