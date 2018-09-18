package `in`.eyehunt.volleyandroidsimplerequest.Model

import `in`.eyehunt.volleyandroidsimplerequest.MainActivity
import de.fu_berlin.mi.tierklinik.VolleyRequester
import org.json.JSONArray
import org.json.JSONObject

class SearchFragmentSucheModel(val view: MainActivity){
    val serverRequestModel: ServerRequest by lazy{ ServerRequest() }

    fun getOwnerList(){}
    fun getPatientList(){}
    fun getPatient(){}
    fun getCase( caseIdString: String, showResponse: (Int) -> Unit) {
        val queue = VolleyRequester.getInstance(view.applicationContext).requestQueue
        val url = "https://kis20kapp.vetkis.fu-berlin.de:8084/swp/tierklinik/patientRecord/case?caseId=$caseIdString"
        serverRequestModel.disableSSLCertificateChecking()
        serverRequestModel.sendRequestToServer(queue, url,({ serverAnswer: String ->
            val jsonCaseFile: JSONObject = JSONObject(serverAnswer).getJSONArray("content").get(0) as JSONObject
            val caseID = jsonCaseFile.get("caseId") as Int
            showResponse(caseID) }) )
    }

    fun getPatient(patientIdString: String, showResponse: (Int) -> Unit) {
        val queue = VolleyRequester.getInstance(view.applicationContext).requestQueue
        val url = "https://kis20kapp.vetkis.fu-berlin.de:8084/swp/tierklinik/patientRecord//animal/id?animalId=$patientIdString"
        serverRequestModel.disableSSLCertificateChecking()
        serverRequestModel.sendRequestToServer(queue, url,({ serverAnswer: String ->
            val patientID: Int = JSONObject(serverAnswer).getJSONObject("content").get("patientId") as Int
            showResponse(patientID) }) )
    }


}