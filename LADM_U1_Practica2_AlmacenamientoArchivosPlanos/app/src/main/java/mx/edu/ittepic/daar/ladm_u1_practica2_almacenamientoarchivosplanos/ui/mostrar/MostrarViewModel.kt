package mx.edu.ittepic.daar.ladm_u1_practica2_almacenamientoarchivosplanos.ui.mostrar

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MostrarViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Ventana Mostrar"
    }
    val text: LiveData<String> = _text
}