package mx.edu.ittepic.daar.ladm_u1_practica2_almacenamientoarchivosplanos.ui.eliminar

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class EliminarViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Ventana Eliminar"
    }
    val text: LiveData<String> = _text
}