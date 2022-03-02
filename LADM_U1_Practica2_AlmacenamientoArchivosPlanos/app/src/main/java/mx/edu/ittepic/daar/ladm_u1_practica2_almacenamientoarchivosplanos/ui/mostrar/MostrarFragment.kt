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
    val productosList = ArrayList<String>()
    var aux : List<String> = listOf("Productos","0","Frutas","0","pz")

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

        val adapter = CustomAdapter(vector)
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter

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

            var index = productos.size-1
            productos.removeAt(index)

            //Hasta aqui el txt ya esta en una lista pero aun los valores no estan separados por parametro

            (0..productos.size-1).forEach {
                productosList.add(productos[it].split(",").toString())
            }

            //Filtrado de cadena

            (0..productosList.size-1).forEach {
                productosList[it] = productosList.get(it).toString().replace("[","")
                productosList[it] = productosList.get(it).toString().replace("]","")

                aux = productosList.get(it).split(",")

                var nombre = aux[0].toString().trim()
                var precio = aux[1].toString().trim().toInt()
                var categoria = aux[2].toString().trim()
                var cantidad = aux[3].toString().trim().toInt()
                var unidad = aux[4].toString().trim()

                var objetoDatos = Data()
                objetoDatos.nombre = nombre
                objetoDatos.precio = precio
                objetoDatos.categoria = categoria
                objetoDatos.cantidad = cantidad
                objetoDatos.unidad = unidad
                vector.add(objetoDatos)
            }
        } catch (e:Exception) {
            Toast.makeText(requireContext(),e.message, Toast.LENGTH_LONG)
        }
    }
}
