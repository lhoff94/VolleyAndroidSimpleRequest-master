package `in`.eyehunt.volleyandroidsimplerequest

import `in`.eyehunt.volleyandroidsimplerequest.Model.ServerRequest
import com.android.volley.RequestQueue


class MainPresenter(var view: MainActivity){
    val serverRequestModel: ServerRequest by lazy{ ServerRequest() }

    fun evaluateForm(queue: RequestQueue, patient:String, ownerLastname:String, ownerSurname:String, patientNumber : Int, caseNumber: Int) {
        val url = "https://kis20kapp.vetkis.fu-berlin.de:8084/swp/tierklinik/patientRecord/owner?ownerFirtsName=Nico"



        serverRequestModel.disableSSLCertificateChecking()
        serverRequestModel.sendRequestToServer(queue, url)

    }



}