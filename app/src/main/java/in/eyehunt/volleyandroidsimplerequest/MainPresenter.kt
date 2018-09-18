package `in`.eyehunt.volleyandroidsimplerequest

import `in`.eyehunt.volleyandroidsimplerequest.Model.SearchFragmentSucheModel
import android.content.Intent


class MainPresenter(var view: MainActivity){
    val backendData :SearchFragmentSucheModel by lazy { SearchFragmentSucheModel(view) }
    fun evaluateForm(
            ownerFirstNameString: String,
            ownerLastNameString: String,
            patientString: String,
            patientIdString: String,
            caseIdString: String) {

        if (caseIdString.isNotBlank()) {
            backendData.getCase(caseIdString, ({ response: Int -> recieveCase(response) }))
            return
        }
        if (patientIdString.isNotBlank()) {
            backendData.getPatient(patientIdString, ({ response: Int -> recievePatient(response) }))
            return
        }

        if (patientString.isNotBlank()){
            backendData.getPatientList(ownerFirstNameString, ownerLastNameString, patientString,({response: String -> recievePatientList(response)}))
            return
        }
        if (patientString.isBlank()){
            backendData.getOwnerList(ownerFirstNameString, ownerLastNameString,({response: String -> recieveOwnerList(response)}))
        }





    }

    private fun recievePatient(response: Int) {
        val intent = Intent(view, MainActivity::class.java)
        intent.putExtra("patient", response)
    }

    private fun recieveCase(response: Int) {
        val intent = Intent(view, MainActivity::class.java)
        intent.putExtra("caseID", response)
    }
    private fun recievePatientList(response: String){

    }
    private fun recieveOwnerList(response:String){

    }


}