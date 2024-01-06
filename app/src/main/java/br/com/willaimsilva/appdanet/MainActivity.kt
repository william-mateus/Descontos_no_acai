package br.com.willaimsilva.appdanet

import android.app.AlertDialog
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import br.com.willaimsilva.appdanet.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private fun validarCampo(piranha: String?): Boolean {
        return piranha.isNullOrEmpty() || piranha.toFloatOrNull() == null
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnDesconto.setOnClickListener {

            if (validarCampo(binding.desconto.text.toString())){
                mensagem(
                    view =it,
                    menssage = "Preencha TODOS os campos !!",
                    context = this,
                    color =R.color.red
                )
            }

            else{
                val desconto = binding.desconto.text.toString().toDouble();
                val preco = binding.preco.text.toString().toDouble();

                val formula = preco*(desconto/100);
                val valorFinal = preco - formula;

                mensagem(
                    view =it,
                    menssage = "Este é o novo preço R$: $valorFinal reais",
                    context = this,
                    color =R.color.purple_700
                )
                if (valorFinal == 0.0){
                    mensagem(
                        view =it,
                        menssage = "voce descobriu o Easter egg, parabnes !!",
                        context = this,
                        color =R.color.teal_200
                    )
                }
            }
        }
    }
    fun mensagem (
        view: View,
        menssage: String,
        context: Context,
        color:Int
    ) {
        val snackbar = Snackbar.make(this,view,menssage, Snackbar.LENGTH_SHORT)
        snackbar.view.setBackgroundColor(ContextCompat.getColor(context,color))
        snackbar.show()
    }
}
