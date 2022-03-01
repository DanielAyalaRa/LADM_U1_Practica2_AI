package mx.edu.ittepic.daar.ladm_u1_practica2_almacenamientoarchivosplanos.ui.insertar

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class InsertarViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Ventana agregar productos"
    }
    val text: LiveData<String> = _text
}