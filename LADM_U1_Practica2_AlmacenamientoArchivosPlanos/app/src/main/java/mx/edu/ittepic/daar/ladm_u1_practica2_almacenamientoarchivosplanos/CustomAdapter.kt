package mx.edu.ittepic.daar.ladm_u1_practica2_almacenamientoarchivosplanos

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import java.io.BufferedReader
import java.io.InputStreamReader

class CustomAdapter(private val data:List<Data>) : RecyclerView.Adapter<CustomAdapter.ProductosViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ProductosViewHolder {
        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.card_layout, viewGroup, false)
        return ProductosViewHolder(v)
    }

    override fun onBindViewHolder(holder : ProductosViewHolder, i: Int) {
        val item = data[i]
        holder.render(item)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class ProductosViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var itemImage = itemView.findViewById<ImageView>(R.id.item_image)
        var itemNombre = itemView.findViewById<TextView>(R.id.item_nombre)
        var itemPrecio = itemView.findViewById<TextView>(R.id.item_precio)
        var itemCategoria = itemView.findViewById<TextView>(R.id.item_categoria)
        var itemCantidad = itemView.findViewById<TextView>(R.id.item_cantidad)
        var itemUnidad = itemView.findViewById<TextView>(R.id.item_unidad)
        var itemPeso = itemView.findViewById<TextView>(R.id.item_peso)

        fun render(producto: Data) {
            when (producto.categoria){
                "Frutas" -> {
                    itemImage.setImageResource(R.drawable.frutas)
                }
                "Verduras" -> {
                    itemImage.setImageResource(R.drawable.verduras)
                }
                "Cereales" -> {
                    itemImage.setImageResource(R.drawable.cereal)
                }
                "Plasticos" -> {
                    itemImage.setImageResource(R.drawable.plasticos)
                }
                "Abarrotes" -> {
                    itemImage.setImageResource(R.drawable.fruteria)
                }
            }

            itemNombre.text = producto.nombre
            itemPrecio.text = producto.precio.toString()
            itemCategoria.text = producto.categoria
            itemCantidad.text = producto.cantidad.toString()
            itemUnidad.text = producto.unidad
            itemPeso.text = "$"
        }
    }
}