package `in`.eyehunt.volleyandroidsimplerequest.Model

import `in`.eyehunt.volleyandroidsimplerequest.MainActivity
import `in`.eyehunt.volleyandroidsimplerequest.R.string.*
import de.fu_berlin.mi.tierklinik.VolleyRequester
import org.json.JSONObject


class SearchFragmentSucheModel(val view: MainActivity){
    val serverRequestModel: ServerRequest by lazy{ ServerRequest() }

    fun getOwnerList(ownerFirstNameString: String, ownerLastNameString: String, function: (String) -> Unit) {
        val queue = VolleyRequester.getInstance(view.applicationContext).requestQueue
        val url = view.resources.getString(baseurl) + view.resources.getString(requestOwnerList)+ "?ownerFirtsName=" +ownerFirstNameString + "&ownerLastName=" + ownerLastNameString
        serverRequestModel.disableSSLCertificateChecking()
        serverRequestModel.sendRequestToServer(queue,url,({serverAnswer:String ->
        }))
    }
    fun getPatientList(ownerFirstNameString: String, ownerLastNameString: String, patientString: String,returnResponse: (String)-> Unit) {
        val queue = VolleyRequester.getInstance(view.applicationContext).requestQueue
        val url = view.resources.getString(baseurl) + view.resources.getString(requestPatientList)+ "?ownerFirtsName=" + ownerFirstNameString + "?ownerLastName=" + ownerLastNameString + "animalName=" + patientString
        serverRequestModel.disableSSLCertificateChecking()
        serverRequestModel.sendRequestToServer(queue,url,({serverAnswer:String ->
            }))
    }

    fun getCase(caseIdString: String, returnResponse: (Int) -> Unit) {
        val queue = VolleyRequester.getInstance(view.applicationContext).requestQueue
        val url: String =  view.resources.getString(baseurl) + view.resources.getString(requestCaseById) + caseIdString
        serverRequestModel.disableSSLCertificateChecking()
        serverRequestModel.sendRequestToServer(queue, url,({ serverAnswer: String ->
            val jsonCaseFile: JSONObject = JSONObject(serverAnswer).getJSONArray("content").get(0) as JSONObject
            val caseID = jsonCaseFile.get("caseId") as Int
            returnResponse(caseID) }) )
    }

    fun getPatient(patientIdString: String, returnResponse: (Int) -> Unit) {
        val queue = VolleyRequester.getInstance(view.applicationContext).requestQueue
        val url = view.resources.getString(baseurl) + view.resources.getString(requestCaseById) + patientIdString
        serverRequestModel.disableSSLCertificateChecking()
        serverRequestModel.sendRequestToServer(queue, url,({ serverAnswer: String ->
            val patientID: Int = JSONObject(serverAnswer).getJSONObject("content").get("patientId") as Int
            returnResponse(patientID) }) )
    }


}