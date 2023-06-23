package com.pdm.cartelera

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.os.Bundle
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pdm.cartelera.Adapter.FilmWeekAdapter
import com.pdm.cartelera.Model.Film
import com.pdm.cartelera.Model.Hours
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.UUID
import android.util.Pair
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.FragmentActivity


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Cinema.newInstance] factory method to
 * create an instance of this fragment.
 */
class Cinema : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var root: View

    private var film: Film? = null

    private lateinit var nombreTicket: EditText
    private lateinit var cantidadTicket: EditText
    private lateinit var fechaTicket: EditText
    private lateinit var horaTicket: Spinner
    private lateinit var btnReserva: Button

    private lateinit var recyclerTicket: RecyclerView

    private var listaWeek: ArrayList<Film> = ArrayList()

    private var listaTotalHoras: ArrayList<Hours> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        root=inflater.inflate(R.layout.fragment_cinema, container, false)

        if(arguments?.getSerializable(FilmDetails.ARG_FILM) != null){
            film = arguments?.getSerializable(FilmDetails.ARG_FILM) as Film
        }


        inicializar()
        rellenar()

        funcionesBotones()


        return root
    }

    fun inicializar(){
        nombreTicket=root.findViewById(R.id.nameTicket)
        cantidadTicket=root.findViewById(R.id.cantidadTicket)
        fechaTicket=root.findViewById(R.id.fechaTicket)
        horaTicket=root.findViewById(R.id.horaTicket)
        btnReserva=root.findViewById(R.id.buttonReservarCine)

        recyclerTicket= root.findViewById(R.id.recyclerTicket)

        recyclerTicket.setHasFixedSize(true)
        recyclerTicket.layoutManager=
            LinearLayoutManager(root.context, LinearLayoutManager.VERTICAL,false)
        addTextWatcher()

        val sinopsis="Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum."

        listaWeek.add(Film("Peli 1", "2000","Luis Soto",sinopsis,ArrayList(),110,R.drawable.aladin_pelicula,"Accion","Comedia","https://www.youtube.com/watch?v=foyufD52aog","https://www.filmaffinity.com/es/film505341.html","https://www.imdb.com/title/tt6139732/", UUID.randomUUID().toString()))
        listaWeek.add(Film("Peli 2", "2000","Luis Soto",sinopsis,ArrayList(),110,R.drawable.aladin_pelicula,"Accion","Comedia","https://www.youtube.com/watch?v=foyufD52aog","https://www.filmaffinity.com/es/film505341.html","https://www.imdb.com/title/tt6139732/", UUID.randomUUID().toString()))
        listaWeek.add(Film("Peli 3", "2000","Luis Soto",sinopsis,ArrayList(),110,R.drawable.aladin_pelicula,"Accion","Comedia","https://www.youtube.com/watch?v=foyufD52aog","https://www.filmaffinity.com/es/film505341.html","https://www.imdb.com/title/tt6139732/", UUID.randomUUID().toString()))
        listaWeek.add(Film("Peli 4", "2000","Luis Soto",sinopsis,ArrayList(),110,R.drawable.aladin_pelicula,"Accion","Comedia","https://www.youtube.com/watch?v=foyufD52aog","https://www.filmaffinity.com/es/film505341.html","https://www.imdb.com/title/tt6139732/", UUID.randomUUID().toString()))
        listaWeek.add(Film("Peli 5", "2000","Luis Soto",sinopsis,ArrayList(),110,R.drawable.aladin_pelicula,"Accion","Comedia","https://www.youtube.com/watch?v=foyufD52aog","https://www.filmaffinity.com/es/film505341.html","https://www.imdb.com/title/tt6139732/", UUID.randomUUID().toString()))
        listaWeek.add(Film("Peli 6", "2000","Luis Soto",sinopsis,ArrayList(),110,R.drawable.aladin_pelicula,"Accion","Comedia","https://www.youtube.com/watch?v=foyufD52aog","https://www.filmaffinity.com/es/film505341.html","https://www.imdb.com/title/tt6139732/", UUID.randomUUID().toString()))


        val fechas: ArrayList<Pair<String, ArrayList<String>>> = arrayListOf(
            Pair("23/06/2023", arrayListOf("09:00", "10:00", "11:00")),
            Pair("24/06/2023", arrayListOf("14:00", "15:00", "16:00")),
            Pair("25/06/2023", arrayListOf("10:00", "11:00", "12:00")),
            Pair("26/06/2023", arrayListOf("13:00", "14:00", "15:00")),
            Pair("27/06/2023", arrayListOf("09:00", "11:00", "13:00")),
            Pair("28/06/2023", arrayListOf("10:00", "12:00", "14:00")),
            Pair("29/06/2023", arrayListOf("11:00", "12:00", "13:00")),
            Pair("30/06/2023", arrayListOf("15:00", "16:00", "17:00")),
            Pair("01/06/2023", arrayListOf("09:00", "10:00", "11:00")),
            Pair("02/06/2023", arrayListOf("12:00", "13:00", "14:00"))
        )


        listaWeek.forEach {
            val aux: Hours = Hours (it.idFilm, fechas)
            listaTotalHoras.add(aux)
        }
    }

    fun funcionesBotones(){
        fechaTicket.setOnClickListener {
            showDatePickerDialog(fechaTicket)
        }
        btnReserva.setOnClickListener {
            val email = showContactDialog()


        }
    }
    private fun showContactDialog(): String {
        val builder = AlertDialog.Builder(requireContext())
        var emailAnonimo: String=""
        builder.setTitle("No estás logueado")
        builder.setMessage("Ingresa tu email")

        val input = EditText(requireContext())
        input.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
        builder.setView(input)

        builder.setPositiveButton("Aceptar") { _, _ ->
            emailAnonimo = input.text.toString()
            if(emailAnonimo.isEmpty())showContactDialog()
            else{
                val detalleFragment = Reserva.newInstance(film!!,emailAnonimo, cantidadTicket.text.toString(),fechaTicket.text.toString(),horaTicket.selectedItem.toString())
                val fragmentManager = (context as FragmentActivity).supportFragmentManager
                val transaction = fragmentManager.beginTransaction()
                transaction.replace(R.id.mainFrame, detalleFragment)
                transaction.addToBackStack(null)
                transaction.commit()
            }
        }

        builder.setNegativeButton("Cancelar") { dialog, _ ->
            dialog.cancel()
        }
        builder.show()

        return emailAnonimo
    }

    fun rellenar(){
        nombreTicket.setText(film?.nombre)
    }

    fun showDatePickerDialog(editTextDate: EditText) {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(root.context,
            DatePickerDialog.OnDateSetListener { _, yearSelected, monthOfYear, dayOfMonth ->
                val selectedDate = String.format(
                    Locale.getDefault(), "%02d/%02d/%04d",
                    dayOfMonth, monthOfYear + 1, yearSelected)
                editTextDate.setText(selectedDate)
            }, year, month, dayOfMonth)

        datePickerDialog.show()
    }

    fun addTextWatcher(){
        //Añadir textWatcher para ver si hay cambio en los componentes
        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val isEditText1Filled = nombreTicket.text.toString()!=""
                val isEditText2Filled = fechaTicket.text.toString()!=""
                val isEditText3Filled = cantidadTicket.text.toString()!=""

                if(isEditText3Filled){
                    val textCant: TextView = root.findViewById(R.id.textNumeroEntradas)
                    textCant.setText(cantidadTicket.text.toString())
                    if(isEditText2Filled)btnReserva.isEnabled=true
                }else{
                    val textCant: TextView = root.findViewById(R.id.textNumeroEntradas)
                    textCant.setText("0")
                    btnReserva.isEnabled=false
                }

                if(isEditText1Filled){
                    buscar(nombreTicket.text.toString(),0)
                    if(isEditText2Filled) buscar(fechaTicket.text.toString(),1)
                }else buscar("vacio",3)

            }

            override fun afterTextChanged(s: Editable?) {
            }
        }
        nombreTicket.addTextChangedListener(textWatcher)
        fechaTicket.addTextChangedListener(textWatcher)
        cantidadTicket.addTextChangedListener(textWatcher)

    }

    fun buscar(valor: String,tipo: Int){
        var fechaDia: String=""
        val adapter: FilmWeekAdapter = FilmWeekAdapter(root.context,false)
        recyclerTicket.adapter=adapter
        listaWeek.forEach {
            when (tipo) {
                0 ->{
                    if (it.nombre.toString().contains(valor, ignoreCase = true)) {
                        adapter.add(it)
                    }
                }
                1 -> {
                    if (it.nombre.toString().contains(nombreTicket.text.toString(), ignoreCase = true)) {
                        adapter.add(it)
                    }
                    fechaDia=valor
                }
                2-> {
                    if (it.año.toString().contains(valor, ignoreCase = true)) {
                        adapter.add(it)
                    }
                }
                3->{
                    adapter.clear()
                    val adapter = ArrayAdapter(root.context,android.R.layout.simple_spinner_item, ArrayList<String>())
                    horaTicket.adapter=adapter
                }
            }
        }
        if(adapter.itemCount==1 && fechaDia!=""){
            listaTotalHoras.forEach {
                if(it.idPelicula==adapter.getFilm(0).idFilm){
                    val adapter = ArrayAdapter(root.context,android.R.layout.simple_spinner_item, it.getHorasDia(fechaDia))
                    horaTicket.adapter=adapter
                }
            }

        }else{
            val adapter = ArrayAdapter(root.context,android.R.layout.simple_spinner_item, ArrayList<String>())
            horaTicket.adapter=adapter
        }
    }



    // Función para crear una fecha en el mes de junio de 2023
    fun createDate(day: Int): Date {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.YEAR, 2023)
        calendar.set(Calendar.MONTH, Calendar.JUNE)
        calendar.set(Calendar.DAY_OF_MONTH, day)
        return calendar.time
    }

    companion object {
        const val ARG_FILM="film"
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Cinema.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic fun newInstance(film: Film) =
                Cinema().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}