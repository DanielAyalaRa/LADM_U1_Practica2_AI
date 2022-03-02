package mx.edu.ittepic.daar.ladm_u1_practica2_almacenamientoarchivosplanos.ui.insertar

import android.R
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import mx.edu.ittepic.daar.ladm_u1_practica2_almacenamientoarchivosplanos.Data
import mx.edu.ittepic.daar.ladm_u1_practica2_almacenamientoarchivosplanos.databinding.FragmentInsertarBinding
import java.io.BufferedReader
import java.io.FileInputStream
import java.io.InputStreamReader
import java.io.OutputStreamWriter

class InsertarFragment : Fragment() {

    private var _binding: FragmentInsertarBinding? = null
    private val binding get() = _binding!!
    val vector = ArrayList<Data>()
    var productos = ArrayList<String>()
    var control : Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val insertarViewModel =
            ViewModelProvider(this).get(InsertarViewModel::class.java)

        _binding = FragmentInsertarBinding.inflate(inflater, container, false)
        val root: View = binding.root

        leer()
        binding.btnInsertar.setOnClickListener {
            if (binding.nombre.text.toString() == "" || binding.precio.text.toString() == "" || binding.cantidad.text.toString() == "") {
                AlertDialog.Builder(requireContext())
                    .setTitle("ATENCION")
                    .setMessage("Campos vacios")
                    .show()
            }else {
                insertar()
            }
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun insertar() {
        try {
            var objetoDatos = Data()

            objetoDatos.nombre = binding.nombre.text.toString()
            objetoDatos.precio = binding.precio.text.toString().toInt()
            objetoDatos.categoria = binding.categoria.selectedItem.toString()
            objetoDatos.cantidad = binding.cantidad.text.toString().toInt()
            objetoDatos.unidad = binding.unidades.selectedItem.toString()
            productos.add(objetoDatos.contenido())

            control = true

            binding.lista.adapter = ArrayAdapter<String>(requireContext(),
                android.R.layout.simple_list_item_1, productos)

            //  Limpiamos contenido
            binding.nombre.setText("")
            binding.precio.setText("")
            binding.cantidad.setText("")

            guardar()
        } catch (e:Exception) {
            Toast.makeText(requireContext(), e.message, Toast.LENGTH_LONG)
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
                    .setTitle("ACTUALIZAR")
                    .setMessage("SE HA GUARDADO CORRECTAMENTE")
                    .setNeutralButton("ACEPTAR", {d,i->
                        d.dismiss()
                    })
                    .show()
            } catch (e:java.lang.Exception) {
                Toast.makeText(requireContext(), e.message, Toast.LENGTH_LONG)
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
            //Como genera un espacio mas elinamos el ultimo elemento.
            var index = productos.size-1
            productos.removeAt(index)
            binding.lista.adapter = ArrayAdapter<String>(requireContext(), R.layout.simple_list_item_1, productos)
        } catch (e:Exception) {
            Toast.makeText(requireContext(),e.message, Toast.LENGTH_LONG)
        }
    }
}