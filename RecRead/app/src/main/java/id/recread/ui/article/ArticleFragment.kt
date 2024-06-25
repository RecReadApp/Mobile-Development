package id.recread.ui.article

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import id.recread.databinding.FragmentArticleBinding
import id.recread.network.NetworkInstance
import id.recread.network.NetworkService
import id.recread.repository.ArticleRepository

class ArticleFragment : Fragment() {

    private var binding: FragmentArticleBinding? = null

    private lateinit var adapterArticle: ArticleAdapter
    private lateinit var viewModel: ArticleViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentArticleBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()
        initViewModel()
        initListener()
    }

    private fun initViewModel() {
        val service: NetworkService = NetworkInstance.retrofit.create(NetworkService::class.java)
        val factory = ArticleViewModelFactory(ArticleRepository(service))
        viewModel = ViewModelProvider(this, factory)[ArticleViewModel::class.java]
    }

    private fun initListener() {
        viewModel.state.observe(viewLifecycleOwner) { articles ->
            if (articles.isNotEmpty()) {
                hideEmptyState()
                adapterArticle.submitList(articles)
            } else {
                showEmptyState()
            }
        }
        
        binding?.inputTextSearch?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // Do nothing
            }

            override fun onTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.searchArticle(text.toString())
            }

            override fun afterTextChanged(p0: Editable?) {
                // Do nothing
            }
        })
    }

    private fun hideEmptyState() {
        binding?.recyclerView?.visibility = View.VISIBLE
        binding?.textArticleEmpty?.visibility = View.GONE
    }

    private fun showEmptyState() {
        binding?.recyclerView?.visibility = View.GONE
        binding?.textArticleEmpty?.visibility = View.VISIBLE
    }

    private fun initAdapter() {
        adapterArticle = ArticleAdapter()
        binding?.recyclerView?.apply {
            adapter = adapterArticle
            layoutManager = LinearLayoutManager(context)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

}