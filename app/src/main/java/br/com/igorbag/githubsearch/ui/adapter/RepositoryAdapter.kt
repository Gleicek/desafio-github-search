package br.com.igorbag.githubsearch.ui.adapter

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import br.com.igorbag.githubsearch.R
import br.com.igorbag.githubsearch.domain.Repository
import br.com.igorbag.githubsearch.ui.MainActivity
import org.w3c.dom.Text

class RepositoryAdapter(private val repositories: List<Repository>, val context: Context) :
    RecyclerView.Adapter<RepositoryAdapter.ViewHolder>() {

    var repoItemLister: (Repository) -> Unit = {}
    var btnShareLister: (Repository) -> Unit = {}

    // Cria uma nova view
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.repository_item, parent, false)
        return ViewHolder(view)
    }

    // Pega o conteudo da view e troca pela informacao de item de uma lista
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // DONE @TODO 8 -  Realizar o bind do viewHolder
        holder.repo_name.text = repositories[position].name
        holder.repo_name.setOnClickListener{
            repoItemLister(repositories[position])
            openBrowser(repositories[position].htmlUrl)
        }
        holder.share_link.setOnClickListener{
            btnShareLister(repositories[position])
            shareRepositoryLink(repositories[position].htmlUrl)
        }

    }

    // Pega a quantidade de repositorios da lista
    // DONE @TODO 9 - realizar a contagem da lista
    override fun getItemCount(): Int = repositories.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        // DONE @TODO 10 - Implementar o ViewHolder para os repositorios
        val repo_name: TextView
        val share_link: ImageView
        init{
            view.apply {
                repo_name = findViewById(R.id.tv_repo)
                share_link = findViewById(R.id.iv_share)
            }
        }

    }

    // Metodo responsavel por compartilhar o link do repositorio selecionado
    // DONE @Todo 11 - Colocar esse metodo no click do share item do adapter
    private fun shareRepositoryLink(urlRepository: String) {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, urlRepository)
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, null)
        context.startActivity(shareIntent)
    }

    // Metodo responsavel por abrir o browser com o link informado do repositorio

    // DONE @Todo 12 - Colocar esse metodo no click item do adapter
    private fun openBrowser(urlRepository: String) {
        context.startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse(urlRepository)
            )
        )

    }

}


