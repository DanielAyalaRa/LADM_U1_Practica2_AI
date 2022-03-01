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

class CustomAdapter : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    val productos = arrayOf("Manzana","Zanahorias","Bolsas","Arroz")
    val precio = arrayOf("$35.0","$8.00","$60.00","$12")
    val categoria = arrayOf("Frutas","Verduras","Plasticos","Abarrotes")
    val cantidad = arrayOf("6","3","10","20")
    val unidad = arrayOf("pz","costales","pz","pz")
    val images = intArrayOf(R.drawable.frutas,
        R.drawable.verduras,
        R.drawable.plasticos,
        R.drawable.cereal)

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.card_layout, viewGroup, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder : ViewHolder, i: Int) {
        viewHolder.itemNombre.text = productos[i]
        viewHolder.itemPrecio.text = precio[i].toString()
        viewHolder.itemCategoria.text = categoria[i]
        viewHolder.itemCantidad.text = cantidad[i].toString()
        viewHolder.itemUnidad.text = unidad[i]
        viewHolder.itemImage.setImageResource(images[i])
    }

    override fun getItemCount(): Int {
        return productos.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var itemImage : ImageView
        var itemNombre : TextView
        var itemPrecio : TextView
        var itemCategoria : TextView
        var itemCantidad : TextView
        var itemUnidad : TextView

        init {
            itemImage = itemView.findViewById(R.id.item_image)
            itemNombre = itemView.findViewById(R.id.item_nombre)
            itemPrecio = itemView.findViewById(R.id.item_precio)
            itemCategoria = itemView.findViewById(R.id.item_categoria)
            itemCantidad = itemView.findViewById(R.id.item_cantidad)
            itemUnidad = itemView.findViewById(R.id.item_unidad)
        }
    }
}