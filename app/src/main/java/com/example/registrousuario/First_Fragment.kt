package com.example.registrousuario

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.registrousuario.ModelView.UserViewModel
import com.example.registrousuario.databinding.FragmentFirstBinding


class First_Fragment : Fragment() {

    //Instanciamos el objeto viewBinding
    private lateinit var mBinding: FragmentFirstBinding

    //Instanciamos el viewModel.
    private val mViewModel: UserViewModel by viewModels()

    //Creamos la variable que va a ir guardando la eleccion inmediata del usuario.
    private var seleccion: String = ""

    //Variable que va a ir seteando el textview que mostramos en la pantalla.
    private lateinit var textViewSeteo: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mViewModel.inicializandoViewmodel()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Configuramos el objeto de la clase viewBinding
        mBinding = FragmentFirstBinding.inflate(inflater, container, false)
        return mBinding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Configuramos el objeto textviewSeteo
        textViewSeteo = mBinding.tvRutMostrar

        /*Tomamos la eleccion del usuario dependiendo de que boton presione y llamamos
        a la funcion que setea el textView*/
        mBinding.ivNumero1.setOnClickListener { seleccionar("1") }
        mBinding.ivNumero2.setOnClickListener { seleccionar("2") }
        mBinding.ivNumero3.setOnClickListener { seleccionar("3") }
        mBinding.ivNumero4.setOnClickListener { seleccionar("4") }
        mBinding.ivNumero5.setOnClickListener { seleccionar("5") }
        mBinding.ivNumero6.setOnClickListener { seleccionar("6") }
        mBinding.ivNumero7.setOnClickListener { seleccionar("7") }
        mBinding.ivNumero8.setOnClickListener { seleccionar("8") }
        mBinding.ivNumero9.setOnClickListener { seleccionar("9") }
        mBinding.ivNumero0.setOnClickListener { seleccionar("0") }
        mBinding.ivGuion.setOnClickListener { seleccionar("-") }
        mBinding.ivK.setOnClickListener { seleccionar("k") }

        mBinding.btonBorrar.setOnClickListener {

            val texto = mBinding.tvRutMostrar.text.toString()

            if (texto.isNotEmpty()) {
                val nuevoTexto = texto.substring(0,texto.length - 1 )
                mBinding.tvRutMostrar.text = nuevoTexto

                seleccion = nuevoTexto
            }
        }

        //Tomamos el valor seleccionado del usuario y pasamos a la siguiente vista.
        mBinding.tvEntrada.setOnClickListener {

            /* Utilizo la funcion para traer un arraylistde usuarios y asi comparar con el escogido
            por el usuario y pasar el siguiente fragmento de ser correcto */
            mViewModel.getUserList().observe(viewLifecycleOwner){ userList ->

                userList.forEach { user ->
                    if (user.RUT.equals(seleccion)){

                        val bundle = Bundle().apply {
                            putString("accion", "Entrada")
                            putString("ID", user.ID)
                            putString("nombre", user.Nombre)
                            putString("rut", user.RUT)
                            putString("cargo", user.Cargo)
                            putString("horario", user.Horario)
                            putString("foto", user.Foto)
                        }

                        findNavController().navigate(
                            R.id.action_first_Fragment_to_second_Fragment,
                            bundle
                        )
                        Log.d("Comparacion", "seleccion: $seleccion, RUT: ${user.RUT}")
                    } else {
                      Log.e("ERROR", "No se encontro el usuario")
                      mBinding.tvRutMostrar.text = "!EL RUT INGRESADO ES INCORRECTO!"
                    }
                }
            }
        }

        mBinding.tvSalida.setOnClickListener {

            Log.e("PASE BOTON", "SE ESTA EJECUTANDO EL ESCUCHADOR")

            /* Utilizo la funcion para traer un arraylist de usuarios y asi comparar con el escogido
             por el usuario y pasar al siguiente fragmento de ser correcto */
            mViewModel.getUserList().observe(viewLifecycleOwner) { userList ->

                userList.forEach { user ->
                    if (user.RUT.equals(seleccion)){

                        val bundle = Bundle().apply {
                            putString("accion", "SALIDA")
                            putString("ID", user.ID)
                            putString("nombre", user.Nombre)
                            putString("rut", user.RUT)
                            putString("cargo", user.Cargo)
                            putString("horario", user.Horario)
                            putString("foto", user.Foto)
                        }

                        findNavController().navigate(
                            R.id.action_first_Fragment_to_second_Fragment,
                            bundle
                        )
                        Log.d("COMPARACION", "No se encontro el usuario")
                    } else {
                        Log.e("ERROR", "No se encontro el usuario")
                        mBinding.tvRutMostrar.text = " !EL RUT INGRESADO ES INCORRECTO"
                    }
                }
            }
        }
    }

    //Función que toma la cadena de string y la va sumando.
    private fun seleccionar (valor: String) {
        //Variable que guarda la seleccion del usuario.
        seleccion = seleccion + valor

        //Seteamos el textView.
        textViewSeteo.text = seleccion
    }
}