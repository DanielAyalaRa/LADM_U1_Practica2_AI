package mx.edu.ittepic.daar.ladm_u1_practica2_almacenamientoarchivosplanos.ui.mostrar

import android.R
import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import mx.edu.ittepic.daar.ladm_u1_practica2_almacenamientoarchivosplanos.CustomAdapter
import mx.edu.ittepic.daar.ladm_u1_practica2_almacenamientoarchivosplanos.Data
import mx.edu.ittepic.daar.ladm_u1_practica2_almacenamientoarchivosplanos.databinding.FragmentMostrarBinding
import java.io.BufferedReader
import java.io.InputStreamReader

class MostrarFragment : Fragment() {

    private var _binding: FragmentMostrarBinding? = null
    private val binding get() = _binding!!
    val vector = ArrayList<Data>()
    var productos = ArrayList<String>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val mostrarViewModel =
            ViewModelProvider(this).get(MostrarViewModel::class.java)

        _binding = FragmentMostrarBinding.inflate(inflater, container, false)
        val root: View = binding.root

        leer()

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun leer() {
        try {
            productos.clear()
            val archivo = BufferedReader(InputStreamReader(context?.openFileInput("archivo.txt")))
            val br = BufferedReader(archivo)

            var linea = br.readLine()
            val cadena = StringBuilder()
            while (linea != null) {
                cadena.append(linea + "\n")
                linea = br.readLine()
            }
            br.close()
            archivo.close()

            var separar = cadena.split("\n")
            var total = separar.size-1

            (0..total).forEach {
                productos.add(separar[it])
            }

            binding.listaMostrar.adapter = ArrayAdapter<String>(requireContext(), R.layout.simple_list_item_1, productos)
        } catch (e:Exception) {
            Toast.makeText(requireContext(),e.message, Toast.LENGTH_LONG)
        }
    }
}
