package com.pdm.cartelera

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
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
 * Use the [Home.newInstance] factory method to
 * create an instance of this fragment.
 */
class Home : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var root: View

    private lateinit var recyclerEstrenos: RecyclerView
    private lateinit var recyclerWeek: RecyclerView

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
        root=inflater.inflate(R.layout.fragment_home, container, false)



        inicializar()
        funcionesBotones()

        return root
    }

    fun inicializar(){
        recyclerEstrenos= root.findViewById(R.id.recyclerEstrenos)
        recyclerEstrenos.setHasFixedSize(true)
        recyclerEstrenos.layoutManager=LinearLayoutManager(root.context,LinearLayoutManager.HORIZONTAL,false)

        val listaWeek: ArrayList<Film> = ArrayList()
        val sinopsis="Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum."

        listaWeek.add(Film("Peli 1", "2000","Luis Soto",sinopsis,ArrayList(),110,R.drawable.aladin_pelicula,"Accion","Comedia","https://www.youtube.com/watch?v=foyufD52aog","https://www.filmaffinity.com/es/film505341.html","https://www.imdb.com/title/tt6139732/", UUID.randomUUID().toString()))
        listaWeek.add(Film("Peli 2", "2000","Luis Soto",sinopsis,ArrayList(),110,R.drawable.aladin_pelicula,"Accion","Comedia","https://www.youtube.com/watch?v=foyufD52aog","https://www.filmaffinity.com/es/film505341.html","https://www.imdb.com/title/tt6139732/", UUID.randomUUID().toString()))
        listaWeek.add(Film("Peli 3", "2000","Luis Soto",sinopsis,ArrayList(),110,R.drawable.aladin_pelicula,"Accion","Comedia","https://www.youtube.com/watch?v=foyufD52aog","https://www.filmaffinity.com/es/film505341.html","https://www.imdb.com/title/tt6139732/", UUID.randomUUID().toString()))
        listaWeek.add(Film("Peli 4", "2000","Luis Soto",sinopsis,ArrayList(),110,R.drawable.aladin_pelicula,"Accion","Comedia","https://www.youtube.com/watch?v=foyufD52aog","https://www.filmaffinity.com/es/film505341.html","https://www.imdb.com/title/tt6139732/", UUID.randomUUID().toString()))
        listaWeek.add(Film("Peli 5", "2000","Luis Soto",sinopsis,ArrayList(),110,R.drawable.aladin_pelicula,"Accion","Comedia","https://www.youtube.com/watch?v=foyufD52aog","https://www.filmaffinity.com/es/film505341.html","https://www.imdb.com/title/tt6139732/", UUID.randomUUID().toString()))
        listaWeek.add(Film("Peli 6", "2000","Luis Soto",sinopsis,ArrayList(),110,R.drawable.aladin_pelicula,"Accion","Comedia","https://www.youtube.com/watch?v=foyufD52aog","https://www.filmaffinity.com/es/film505341.html","https://www.imdb.com/title/tt6139732/", UUID.randomUUID().toString()))


        val adapter: FilmAdapter = FilmAdapter(listaWeek,root.context)
        recyclerEstrenos.adapter=adapter

        recyclerWeek= root.findViewById(R.id.recyclerWeek)
        recyclerWeek.setHasFixedSize(true)
        recyclerWeek.layoutManager=LinearLayoutManager(root.context,LinearLayoutManager.VERTICAL,false)




        val adapterWeek: FilmWeekAdapter = FilmWeekAdapter(listaWeek,root.context)
        recyclerWeek.adapter=adapterWeek
    }
    fun funcionesBotones(){

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Home.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Home().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}