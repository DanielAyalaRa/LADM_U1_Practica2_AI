package mx.edu.ittepic.daar.ladm_u1_practica2_almacenamientoarchivosplanos.ui.actualizar

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ActualizarViewModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "Ventana Actualizar"
    }
    val text: LiveData<String> = _text
}