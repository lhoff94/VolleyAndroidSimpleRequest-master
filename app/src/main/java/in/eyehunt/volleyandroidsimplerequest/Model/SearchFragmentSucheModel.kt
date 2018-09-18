package `in`.eyehunt.volleyandroidsimplerequest.Model

import `in`.eyehunt.volleyandroidsimplerequest.MainActivity
import `in`.eyehunt.volleyandroidsimplerequest.R.string.*
import de.fu_berlin.mi.tierklinik.VolleyRequester
import org.json.JSONObject

class SearchFragmentSucheModel(val view: MainActivity){
    val serverRequestModel: ServerRequest by lazy{ ServerRequest() }

    fun getOwnerList(ownerFirstNameString: String, ownerLastNameString: String, function: (String) -> Unit) {
        val queue = VolleyRequester.getInstance(view.applicationContext).requestQueue
        val url = "https://kis20kapp.vetkis.fu-berlin.de:8084//swp/tierklinik/patientRecord/owner?ownerFirtsName=$ownerFirstNameString&ownerLastName=$ownerLastNameString"
        serverRequestModel.disableSSLCertificateChecking()
        serverRequestModel.sendRequestToServer(queue,url,({serverAnswer:String ->
        }))
    }
    fun getPatientList(ownerFirstNameString: String, ownerLastNameString: String, patientString: String,returnResponse: (String)-> Unit) {
        val queue = VolleyRequester.getInstance(view.applicationContext).requestQueue
        val url = "https://kis20kapp.vetkis.fu-berlin.de:8084//swp/tierklinik/patientRecord/animal/owner?ownerFirtsName=$ownerFirstNameString&ownerLastName=$ownerLastNameString&animalName=$patientString"
        serverRequestModel.disableSSLCertificateChecking()
        serverRequestModel.sendRequestToServer(queue,url,({serverAnswer:String ->
            }))
    }

    fun getCase(caseIdString: String, returnResponse: (Int) -> Unit) {
        val queue = VolleyRequester.getInstance(view.applicationContext).requestQueue
        val url: String = getString(baseurl) + getString(requestCaseById) + caseIdString
        serverRequestModel.disableSSLCertificateChecking()
        serverRequestModel.sendRequestToServer(queue, url,({ serverAnswer: String ->
            val jsonCaseFile: JSONObject = JSONObject(serverAnswer).getJSONArray("content").get(0) as JSONObject
            val caseID = jsonCaseFile.get("caseId") as Int
            returnResponse(caseID) }) )
    }

    fun getPatient(patientIdString: String, returnResponse: (Int) -> Unit) {
        val queue = VolleyRequester.getInstance(view.applicationContext).requestQueue
        val url :string = getString(baseurl) + getString(requestCaseById) + patientIdString
        serverRequestModel.disableSSLCertificateChecking()
        serverRequestModel.sendRequestToServer(queue, url,({ serverAnswer: String ->
            val patientID: Int = JSONObject(serverAnswer).getJSONObject("content").get("patientId") as Int
            returnResponse(patientID) }) )
    }


}