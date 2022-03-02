package mx.edu.ittepic.daar.ladm_u1_practica2_almacenamientoarchivosplanos

class Data {
    var nombre = ""
    var precio = 0
    var categoria = ""
    var cantidad = 0
    var unidad = ""


    fun contenido() : String {
        return nombre+","+precio+","+categoria+","+cantidad+","+unidad
    }

    fun construir(renglon: String) {
        val contenido = renglon.split(",")
        nombre = contenido[0]
        precio = contenido[1].toString().toInt()
        categoria = contenido[2]
        cantidad = contenido[3].toString().toInt()
        unidad = contenido[4]
    }
}