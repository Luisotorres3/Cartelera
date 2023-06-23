package com.pdm.cartelera

import android.content.ContentValues.TAG
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pdm.cartelera.Adapter.FilmAdapter
import com.pdm.cartelera.Adapter.FilmWeekAdapter
import com.pdm.cartelera.Model.Film
import java.util.UUID

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Find.newInstance] factory method to
 * create an instance of this fragment.
 */
class Find : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var root: View

    private lateinit var nombrePelicula: EditText
    private lateinit var autorPelicula: EditText
    private lateinit var añoPelicula: EditText

    private lateinit var recyclerBuscar: RecyclerView

    private var listaWeek: ArrayList<Film> = ArrayList()

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
        root=inflater.inflate(R.layout.fragment_find, container, false)

        inicializar()

        funcionesBotones()



        return root
    }

    fun inicializar(){
        nombrePelicula=root.findViewById(R.id.editTextNombrePelicula)
        autorPelicula=root.findViewById(R.id.editTextAutorPelicula)
        añoPelicula=root.findViewById(R.id.editTextAñoPelicula)

        recyclerBuscar= root.findViewById(R.id.recyclerBuscar)
        recyclerBuscar.setHasFixedSize(true)
        recyclerBuscar.layoutManager=
            LinearLayoutManager(root.context, LinearLayoutManager.VERTICAL,false)
        addTextWatcher()

        val sinopsis="Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum."

        listaWeek.add(Film("Peli 1", "2000","Luis Soto",sinopsis,ArrayList(),110,R.drawable.aladin_pelicula,"Accion","Comedia","https://www.youtube.com/watch?v=foyufD52aog","https://www.filmaffinity.com/es/film505341.html","https://www.imdb.com/title/tt6139732/", UUID.randomUUID().toString()))
        listaWeek.add(Film("Peli 2", "2000","Luis Soto",sinopsis,ArrayList(),110,R.drawable.aladin_pelicula,"Accion","Comedia","https://www.youtube.com/watch?v=foyufD52aog","https://www.filmaffinity.com/es/film505341.html","https://www.imdb.com/title/tt6139732/", UUID.randomUUID().toString()))
        listaWeek.add(Film("Peli 3", "2000","Luis Soto",sinopsis,ArrayList(),110,R.drawable.aladin_pelicula,"Accion","Comedia","https://www.youtube.com/watch?v=foyufD52aog","https://www.filmaffinity.com/es/film505341.html","https://www.imdb.com/title/tt6139732/", UUID.randomUUID().toString()))
        listaWeek.add(Film("Peli 4", "2000","Luis Soto",sinopsis,ArrayList(),110,R.drawable.aladin_pelicula,"Accion","Comedia","https://www.youtube.com/watch?v=foyufD52aog","https://www.filmaffinity.com/es/film505341.html","https://www.imdb.com/title/tt6139732/", UUID.randomUUID().toString()))
        listaWeek.add(Film("Peli 5", "2000","Luis Soto",sinopsis,ArrayList(),110,R.drawable.aladin_pelicula,"Accion","Comedia","https://www.youtube.com/watch?v=foyufD52aog","https://www.filmaffinity.com/es/film505341.html","https://www.imdb.com/title/tt6139732/", UUID.randomUUID().toString()))
        listaWeek.add(Film("Peli 6", "2000","Luis Soto",sinopsis,ArrayList(),110,R.drawable.aladin_pelicula,"Accion","Comedia","https://www.youtube.com/watch?v=foyufD52aog","https://www.filmaffinity.com/es/film505341.html","https://www.imdb.com/title/tt6139732/", UUID.randomUUID().toString()))



    }

    fun funcionesBotones(){

    }

    fun addTextWatcher(){
        //Añadir textWatcher para ver si hay cambio en los componentes
        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val isEditText1Filled = nombrePelicula.text.toString()!=""
                val isEditText2Filled = autorPelicula.text.toString()!=""
                val isEditText3Filled = añoPelicula.text.toString()!=""

                if(isEditText1Filled) buscar(nombrePelicula.text.toString(),0)
                else if(isEditText2Filled) buscar(autorPelicula.text.toString(),1)
                else if(isEditText3Filled) buscar(añoPelicula.text.toString(),2)
                else buscar("vacio",3)

            }

            override fun afterTextChanged(s: Editable?) {
            }
        }
        nombrePelicula.addTextChangedListener(textWatcher)
        autorPelicula.addTextChangedListener(textWatcher)
        añoPelicula.addTextChangedListener(textWatcher)

    }

    fun buscar(valor: String,tipo: Int){
        val adapter: FilmWeekAdapter = FilmWeekAdapter(root.context)
        recyclerBuscar.adapter=adapter
        listaWeek.forEach {
            when (tipo) {
                0 ->{
                    if (it.nombre.toString().contains(valor, ignoreCase = true)) {
                        adapter.add(it)
                    }
                }
                1 -> {
                    if (it.autor.toString().contains(valor, ignoreCase = true)) {
                        adapter.add(it)
                    }
                }
                2-> {
                    if (it.año.toString().contains(valor, ignoreCase = true)) {
                        adapter.add(it)
                    }
                }
                3->{
                    adapter.clear()
                }
            }
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Find.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Find().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}