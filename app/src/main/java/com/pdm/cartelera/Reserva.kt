package com.pdm.cartelera

import android.content.ContentValues.TAG
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.common.util.Base64Utils.decode
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.WriterException
import com.google.zxing.common.BitMatrix
import com.pdm.cartelera.Model.Film
import com.journeyapps.barcodescanner.BarcodeEncoder
import java.io.ByteArrayOutputStream
import java.util.Base64
import java.util.UUID

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Reserva.newInstance] factory method to
 * create an instance of this fragment.
 */
class Reserva() : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var root: View

    private var film: Film? = null
    private var email: String? =null
    private var cantidad: String?=null
    private var fecha: String?=null
    private var hora: String?=null

    private lateinit var imageQR: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        root=inflater.inflate(R.layout.fragment_reserva, container, false)

        val bundle = arguments

        if(arguments?.getSerializable(ARG_FILM) != null){
            film = arguments?.getSerializable(ARG_FILM) as Film
        }
        if (bundle != null) {
            email = bundle.getString("email")
            cantidad = bundle.getString("cantidad")
            fecha = bundle.getString("fecha")
            hora = bundle.getString("hora")
        }

        inicializar()





        return root
    }

    fun inicializar(){
        imageQR= root.findViewById(R.id.imageQR)
        crearQR()

        val textName: TextView = root.findViewById(R.id.textNameTicket)
        textName.setText(film?.nombre)

        val textDate: TextView = root.findViewById(R.id.textDateTicket)
        textDate.setText(fecha+ " --- "+hora)

        val textEmail: TextView= root.findViewById(R.id.textEmailTicket)
        textEmail.setText(email)

        val im: ImageView = root.findViewById(R.id.imageQR2)

        //val decodedBytes = Base64.decode(bitmapString, Base64.DEFAULT)

// Crear el objeto Bitmap a partir del arreglo de bytes
        //val bitmap = BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size)
        //im.setImageBitmap(bitmap)




    }

    fun crearQR(){

        val writer: MultiFormatWriter = MultiFormatWriter()
        try{
            val matrix: BitMatrix = writer.encode(email, BarcodeFormat.QR_CODE,350,350)
            val encoder: BarcodeEncoder = BarcodeEncoder()
            val bitmap: Bitmap = encoder.createBitmap(matrix)

            imageQR.setImageBitmap(bitmap)

            val opcionID = UUID.randomUUID().toString()
            val bitmap2 = (imageQR.drawable as BitmapDrawable).bitmap
            val baos = ByteArrayOutputStream()
            bitmap2.compress(Bitmap.CompressFormat.JPEG, 100, baos)
            val data = baos.toString()

            val opcion: Pair<String, String> = Pair(email,data) as Pair<String, String>

            val db = FirebaseFirestore.getInstance()


// Add a new document with a generated ID
            db.collection("QR")
                .add(opcion)
                .addOnSuccessListener { documentReference ->
                    Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
                }
                .addOnFailureListener { e ->
                    Log.w(TAG, "Error adding document", e)
                }






        }catch (e: WriterException){
            e.printStackTrace()
        }
    }

    companion object {
        const val ARG_FILM="film"
        const val ARG_EMAIL="email"
        const val ARG_CANTIDAD="cantidad"
        const val ARG_FECHA="fecha"
        const val ARG_HORA="hora"
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Reserva.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(film: Film, email: String, cantidad: String,fecha: String, hora: String) =
            Reserva().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_FILM,film)
                    putString(ARG_EMAIL, email)
                    putString(ARG_CANTIDAD, cantidad)
                    putString(ARG_FECHA, fecha)
                    putString(ARG_HORA, hora)
                }
            }
    }
}