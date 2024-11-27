package com.example.registrousuario

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.registrousuario.Model.Remote.UserSend
import com.example.registrousuario.ModelView.UserViewModel
import com.example.registrousuario.databinding.FragmentThirdBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class Third_Fragment : Fragment() {

    //Instanciamos la clase ViewModel para poder acceder a sus metodos.
    private val mViewModel: UserViewModel by viewModels()

    //Instanciamos el objeto ViewBinding
    private lateinit var mBinding : FragmentThirdBinding

    //Variable que toma la fecha y hora de la clase date.
    private var horaRonda: Date = Date()

    //Variable para guardar el turno a subir.
    private lateinit var turno: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //Configuramos la vista con el codigo
        mBinding = FragmentThirdBinding.inflate(inflater,container,false)
        return mBinding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Llamamos a la funcion.
        queTurnoEs()

        //Configuramos el objeto a enviar.
        val envioData = UserSend(
            spreadsheet_id = "1aQD1jNQfX4iDiJxcsiZpaixPg009BUCPaI3hQ6rI5rk",
            sheet = "REGISTRO RONDA",
            listOf(
                listOf(
                    //Pasamos los parametros a guardar en la hoja de calculo.
                    "${turno}",
                    "${horaRonda}"
                )
            )
        )

        //Configuramos el boton que enviara los datos de la ronda.
        mBinding.botonRegistrar.setOnClickListener {
            mViewModel.enviarDatosAlServidor(envioData)
        }

        //Configuramos el boton que nos regresara a la primera pantalla y enviara la ultima ronda.
        mBinding.botonCerrarTurno.setOnClickListener {
            mViewModel.enviarDatosAlServidor(envioData)

            findNavController().navigate(R.id.action_third_Fragment_to_first_Fragment)
        }
    }

    //Funcion que tomara los datos de la hora del dia para saber en que turno estamos.
    private fun queTurnoEs(): String {
        //Le damos un formato adecuado a la hora.
        val formatoHora = SimpleDateFormat("HH", Locale.getDefault())
        //Formateamos el valor a un int.
        val horaFormateada = formatoHora.format(horaRonda).toInt()

        //Verificamos mediante un rango el turno en el que nos encontramos.
        if (horaFormateada >= 8 && horaFormateada <= 20){
            turno = "Diurno"
        }else{
            turno = "Nocturno"
        }
        //Regresamos el valor del turno en la variable.
        return turno as String
    }
}