package mx.edu.ittepic.daar.ladm_u1_practica2_almacenamientoarchivosplanos.ui.eliminar

import android.R
import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import mx.edu.ittepic.daar.ladm_u1_practica2_almacenamientoarchivosplanos.Data
import mx.edu.ittepic.daar.ladm_u1_practica2_almacenamientoarchivosplanos.databinding.FragmentEliminarBinding
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStreamWriter

class EliminarFragment : Fragment() {

    private var _binding: FragmentEliminarBinding? = null
    private val binding get() = _binding!!
    var productos = ArrayList<String>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val eliminarViewModel =
            ViewModelProvider(this).get(EliminarViewModel::class.java)

        _binding = FragmentEliminarBinding.inflate(inflater, container, false)
        val root: View = binding.root

        leer()
        eliminar()

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun eliminar(){
        binding.listaEliminar.adapter = ArrayAdapter<String>(requireContext(),
            android.R.layout.simple_list_item_1, productos)
        // adapter es igual al entries en el xml es la manera de que se dapte para que se muestre linerlayout = android.R.layout.simple_list_item_1

        binding.listaEliminar.setOnItemClickListener { parent, view, position, id ->
            val posicion = position

            android.app.AlertDialog.Builder(requireContext())
                .setTitle("ATENCION")
                .setMessage("¿Esta seguro que desea borrar el indice ${posicion}?")
                .setPositiveButton("OK", {d,i->
                    productos.removeAt(posicion)
                    eliminar()
                    guardar()
                    d.dismiss()
                })
                .setNeutralButton("CANCELAR", {d,i->
                    d.cancel()
                })
                .show()

        }
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
            binding.listaEliminar.adapter = ArrayAdapter<String>(requireContext(), R.layout.simple_list_item_1, productos)
        } catch (e:Exception) {
            Toast.makeText(requireContext(),e.message, Toast.LENGTH_LONG)
        }
    }

    private fun guardar() {
        var dataVector = ""
        var total = productos.size-1

        (0..total).forEach {
            val objetoData = productos.get(it)
            dataVector += objetoData+"\n"
        }

        dataVector = dataVector.substring(0,dataVector.lastIndexOf("\n"))

        try {
            val archivo = OutputStreamWriter(requireActivity().openFileOutput("archivo.txt", 0))
            archivo.write(dataVector)
            archivo.flush()
            archivo.close()

            AlertDialog.Builder(requireContext())
                .setMessage("SE HA ELIMINADO CON EXITO")
                .show()
        } catch (e:java.lang.Exception) {
            Toast.makeText(requireContext(), e.message, Toast.LENGTH_LONG)
        }
    }
}