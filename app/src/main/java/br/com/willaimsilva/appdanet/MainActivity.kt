package br.com.willaimsilva.appdanet

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import br.com.willaimsilva.appdanet.databinding.ActivityMainBinding
import br.com.willaimsilva.appdanet.databinding.CustomSnackbarBinding
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
            if (validarCampo(binding.desconto.text.toString())) {
                mostrarMensagem(
                    mensagem = "Preencha TODOS os campos !!",
                    color = R.color.red,
                    imageDrawable = R.drawable.erro
                )
            } else {
                val desconto = binding.desconto.text.toString().toDouble();
                val preco = binding.preco.text.toString().toDouble();

                val formula = preco * (desconto / 100);
                val valorFinal = preco - formula;

                mostrarMensagem(
                    mensagem = "Este é o novo preço R$: $valorFinal reais",
                    color = R.color.snackbar,
                    imageDrawable = R.drawable.icon
                )
                if (valorFinal == 0.0) {
                    mostrarMensagem(
                        mensagem = "voce descobriu o Easter egg, parabnes !!",
                        color = R.color.teal_200,
                        imageDrawable = R.drawable.icon_easteegg
                    )
                }
            }
        }
    }

    fun mostrarMensagem(
        mensagem: String,
        color: Int,
        imageDrawable: Int
    ) {
        val view = CustomSnackbarBinding.inflate(LayoutInflater.from(this))
        view.textSnackbar.text = mensagem
        view.root.setCardBackgroundColor(ContextCompat.getColor(this, color))

        //trocar imagem snackbar
        view.profilrPic.setImageResource(imageDrawable);

        // CoordinatorLayout

        val snackbar = Snackbar.make(this, binding.root, "", Snackbar.LENGTH_SHORT)
        val snackbarView = snackbar.view as? Snackbar.SnackbarLayout;
        snackbarView?.setPadding(0, 0, 0, 0);
        snackbarView?.addView(view.root)
        snackbarView?.setBackgroundColor(Color.TRANSPARENT)
        snackbar.show()
    }
}
