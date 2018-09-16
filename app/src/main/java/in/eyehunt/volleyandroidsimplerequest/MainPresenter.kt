package `in`.eyehunt.volleyandroidsimplerequest

import `in`.eyehunt.volleyandroidsimplerequest.Model.RequestGenerator
import `in`.eyehunt.volleyandroidsimplerequest.Model.ServerRequest
import android.content.Intent
import de.fu_berlin.mi.tierklinik.VolleyRequester
import org.json.JSONArray
import org.json.JSONObject
import org.xml.sax.Parser


class MainPresenter(var view: MainActivity){
    val backendData :RequestGenerator by lazy { RequestGenerator(view) }
    fun evaluateForm(
            ownerFirstNameString: String,
            ownerLastNameString: String,
            patientString: String,
            patientIdString: String,
            caseIdString: String) {
        val queue = VolleyRequester.getInstance(view.applicationContext).requestQueue
        val url = "https://kis20kapp.vetkis.fu-berlin.de:8084/swp/tierklinik/patientRecord/owner?ownerFirtsName=Nico"

        if (caseIdString != "") {
            backendData.getCase(caseIdString, ({ response: Int -> receiveCase(response) }))
        } else {
            if (patientIdString != "") {
                backendData.getPatient(patientIdString, ({ response: Int -> receivePatient(response) }))
            }
        }
    }

    private fun receivePatient(response: Int) {
        val intent = Intent(view, MainActivity::class.java)
        intent.putExtra("patient", response)
    }

    private fun receiveCase(response: Int) {
        val intent = Intent(view, MainActivity::class.java)
        intent.putExtra("caseID", response)
    }


}