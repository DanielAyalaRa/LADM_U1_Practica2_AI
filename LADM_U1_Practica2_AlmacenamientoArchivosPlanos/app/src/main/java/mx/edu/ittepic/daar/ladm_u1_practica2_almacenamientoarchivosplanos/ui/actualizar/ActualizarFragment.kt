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
import java.io.OutputStreamWriter

class ActualizarFragment : Fragment() {

    private var _binding: FragmentActualizarBinding? = null
    private val binding get() = _binding!!
    val vector = ArrayList<Data>()
    var productos = ArrayList<String>()
    val productosList = ArrayList<String>()
    var aux : List<String> = listOf("Productos","0","Frutas","0","pz")
    var index = 0
    var control = false

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

        leer()
        actualizar()

        binding.btnActualizar.setOnClickListener {
            guardar(index)
        }


        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ActualizarViewModel::class.java)
        // TODO: Use the ViewModel
    }

    private fun actualizar(){
        try {
            binding.listaActualizar.adapter = ArrayAdapter<String>(requireContext(),
                android.R.layout.simple_list_item_1, productos)
            // adapter es igual al entries en el xml es la manera de que se dapte para que se muestre linerlayout = android.R.layout.simple_list_item_1

            binding.listaActualizar.setOnItemClickListener { parent, view, position, id ->
                val posicion = position
                index = posicion
                control = true

                android.app.AlertDialog.Builder(requireContext())
                    .setTitle("ATENCION")
                    .setMessage("Desea seleccionar ${posicion}")
                    .setPositiveButton("OK", {d,i->
                        leerValores(posicion)
                        //actualizar()
                        d.dismiss()
                    })
                    .setNeutralButton("CANCELAR", {d,i->
                        d.cancel()
                    })
                    .show()
            }
        } catch (e:Exception) {
            android.app.AlertDialog.Builder(requireContext())
                .setTitle("ATENCION")
                .setMessage("ERROR")
                .setPositiveButton("OK", {d,i->
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
            binding.listaActualizar.adapter = ArrayAdapter<String>(requireContext(), android.R.layout.simple_list_item_1, productos)
        } catch (e:Exception) {
            Toast.makeText(requireContext(),e.message, Toast.LENGTH_LONG)
        }
    }

    private fun guardar(posicion : Int) {
        if (control == false) {

        } else {
            var objetoDatos = Data()

            objetoDatos.nombre = binding.nombre.text.toString()
            objetoDatos.precio = binding.precio.text.toString().toInt()
            objetoDatos.categoria = binding.categoria.selectedItem.toString()
            objetoDatos.cantidad = binding.cantidad.text.toString().toInt()
            objetoDatos.unidad = binding.unidades.selectedItem.toString()
            productos[posicion] = objetoDatos.contenido()

            binding.listaActualizar.adapter = ArrayAdapter<String>(requireContext(),
                android.R.layout.simple_list_item_1, productos)

            //  Limpiamos contenido
            binding.nombre.setText("")
            binding.precio.setText("")
            binding.cantidad.setText("")

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

                androidx.appcompat.app.AlertDialog.Builder(requireContext())
                    .setMessage("SE HA ACTUALIZADO")
                    .show()
            } catch (e:java.lang.Exception) {
                Toast.makeText(requireContext(), e.message, Toast.LENGTH_LONG)
            }

            control = false
        }
    }

    private fun leerValores(posicion : Int) {
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

            var index = productos.size-1
            productos.removeAt(index)

            //Hasta aqui el txt ya esta en una lista pero aun los valores no estan separados por parametro

            (0..productos.size-1).forEach {
                productosList.add(productos[it].split(",").toString())
            }

            //Filtrado de cadena

            productosList[posicion] = productosList.get(posicion).toString().replace("[","")
            productosList[posicion] = productosList.get(posicion).toString().replace("]","")

            aux = productosList.get(posicion).split(",")

            var nombre = aux[0].toString().trim()
            var precio = aux[1].toString().trim()
            var cantidad = aux[3].toString().trim()

            binding.nombre.setText(nombre)
            binding.precio.setText(precio)
            binding.cantidad.setText(cantidad)
        } catch (e:Exception) {
            Toast.makeText(requireContext(),e.message, Toast.LENGTH_LONG)
        }
    }
}