package `in`.eyehunt.volleyandroidsimplerequest

import `in`.eyehunt.volleyandroidsimplerequest.Model.SearchFragmentSucheModel
import android.content.Intent
import de.fu_berlin.mi.tierklinik.VolleyRequester


class MainPresenter(var view: MainActivity){
    val backendData :SearchFragmentSucheModel by lazy { SearchFragmentSucheModel(view) }
    fun evaluateForm(
            ownerFirstNameString: String,
            ownerLastNameString: String,
            patientString: String,
            patientIdString: String,
            caseIdString: String) {
        val queue = VolleyRequester.getInstance(view.applicationContext).requestQueue
        val url = "https://kis20kapp.vetkis.fu-berlin.de:8084/swp/tierklinik/patientRecord/owner?ownerFirtsName=Nico"

        if (caseIdString.isNotBlank()) {
            backendData.getCase(caseIdString, ({ response: Int -> receiveCase(response) }))
            return
        }
        if (patientIdString.isNotBlank()) {
            backendData.getPatient(patientIdString, ({ response: Int -> receivePatient(response) }))
            return
        }

        if (patientString.isNotBlank()){
            if (ownerFirstNameString.isNotBlank() and ownerLastNameString.isNotBlank()){
                backendData.getPatientList()
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