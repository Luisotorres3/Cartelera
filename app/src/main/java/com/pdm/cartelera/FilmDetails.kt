package com.pdm.cartelera

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.pdm.cartelera.Model.Film


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FilmDetails.newInstance] factory method to
 * create an instance of this fragment.
 */
class FilmDetails : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var root: View

    private lateinit var recyclerView: RecyclerView

    private var listaFilms: ArrayList<Film> = ArrayList()
    private lateinit var film: Film

    private lateinit var nombre: TextView
    private lateinit var año: TextView
    private lateinit var autor: TextView
    private lateinit var sinopsis: TextView
    private lateinit var duracion: TextView
    private lateinit var image: ImageView
    private lateinit var linearVerMas: LinearLayout
    private lateinit var linearVerMenos: LinearLayout
    private lateinit var linearBotones: LinearLayout

    private lateinit var btnYoutube: ImageButton
    private lateinit var btnFilmaffinity: ImageButton
    private lateinit var btnUrl: ImageButton
    private lateinit var btnReservar: Button



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
        root=inflater.inflate(R.layout.fragment_film_details, container, false)

        film = arguments?.getSerializable(ARG_FILM) as Film

        inicializar()

        funcionesBotones()

        rellenar()


        return root
    }

    fun inicializar(){
        nombre=root.findViewById(R.id.textNameDetails)
        autor=root.findViewById(R.id.textAutorDetails)
        duracion=root.findViewById(R.id.textDuracionDetails)
        año=root.findViewById(R.id.textAgeDetails)
        sinopsis=root.findViewById(R.id.textSinopsisDetails)

        image=root.findViewById(R.id.imageDetails)

        linearVerMas=root.findViewById(R.id.linearVerMas)
        linearVerMenos=root.findViewById(R.id.linearVerMenos)
        linearBotones=root.findViewById(R.id.linearBotones)

        btnYoutube=root.findViewById(R.id.btnYoutube)
        btnYoutube.setImageResource(R.mipmap.youtube_icon_round)

        btnFilmaffinity=root.findViewById(R.id.btnFilmaffinity)
        btnFilmaffinity.setImageResource(R.mipmap.filmaffinity_icon_round)

        btnUrl=root.findViewById(R.id.btnUrl)
        btnUrl.setImageResource(R.mipmap.imdb_icon_round)

        btnReservar=root.findViewById(R.id.buttonReservar)

        val btn1: ImageButton = root.findViewById(R.id.imageButton)
        val btn2: ImageButton = root.findViewById(R.id.imageButton2)
        btn1.isEnabled=false
        btn2.isEnabled=false
    }

    fun funcionesBotones(){
        linearVerMas.setOnClickListener {
            sinopsis.setVisibility(View.VISIBLE)
            linearVerMas.setVisibility(View.GONE)
            linearVerMenos.visibility=View.VISIBLE
            linearBotones.visibility=View.GONE
        }
        linearVerMenos.setOnClickListener {
            sinopsis.setVisibility(View.GONE)
            linearVerMas.setVisibility(View.VISIBLE)
            linearVerMenos.visibility=View.GONE
            linearBotones.visibility=View.VISIBLE
        }

        btnYoutube.setOnClickListener {
            val url = film.urlTrailer // URL deseada
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }
        btnFilmaffinity.setOnClickListener {
            val url = film.urlFilmaffinity // URL deseada
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }
        btnUrl.setOnClickListener {
            val url = film.urlIMDB // URL deseada
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }

        btnReservar.setOnClickListener {
            val detalleFragment = Cinema()

            if (film != null) {
                val bundle = Bundle()
                bundle.putSerializable("film", film)
                detalleFragment.arguments = bundle
            }

            val fragmentManager = (context as FragmentActivity).supportFragmentManager
            val transaction = fragmentManager.beginTransaction()
            transaction.replace(R.id.mainFrame, detalleFragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }

    fun rellenar(){
        nombre.text=film.nombre
        autor.text=film.autor
        año.text=film.año
        duracion.text= (film.duracion).toString() + " min"
        sinopsis.text=film.sinopsis

        image.setImageResource(film.imagen)


    }


    companion object {

        const val ARG_FILM= "film"
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FilmDetails.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(film: Film) =
            FilmDetails().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_FILM,film)
                }
            }
    }
}