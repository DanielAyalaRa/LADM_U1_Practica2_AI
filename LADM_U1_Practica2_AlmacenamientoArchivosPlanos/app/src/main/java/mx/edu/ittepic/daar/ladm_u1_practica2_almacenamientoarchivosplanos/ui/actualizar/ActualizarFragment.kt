package mx.edu.ittepic.daar.ladm_u1_practica2_almacenamientoarchivosplanos.ui.actualizar

import android.app.ActionBar
import android.app.Activity
import android.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import mx.edu.ittepic.daar.ladm_u1_practica2_almacenamientoarchivosplanos.CustomAdapter
import mx.edu.ittepic.daar.ladm_u1_practica2_almacenamientoarchivosplanos.Data
import mx.edu.ittepic.daar.ladm_u1_practica2_almacenamientoarchivosplanos.R
import mx.edu.ittepic.daar.ladm_u1_practica2_almacenamientoarchivosplanos.databinding.FragmentActualizarBinding
import java.io.BufferedReader
import java.io.InputStreamReader

class ActualizarFragment : Fragment() {

    private var _binding: FragmentActualizarBinding? = null
    private val binding get() = _binding!!
    val vector = ArrayList<Data>()
    var productos = ArrayList<String>()
    val productosList = ArrayList<String>()
    var aux : List<String> = listOf("Productos","0","Frutas","0","pz")

    companion object {
        fun newInstance() = ActualizarFragment()
    }

    private lateinit var viewModel: ActualizarViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val actualizarViewModel =
            ViewModelProvider(this).get(ActualizarViewModel::class.java)

        _binding = FragmentActualizarBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ActualizarViewModel::class.java)
        // TODO: Use the ViewModel
    }
}