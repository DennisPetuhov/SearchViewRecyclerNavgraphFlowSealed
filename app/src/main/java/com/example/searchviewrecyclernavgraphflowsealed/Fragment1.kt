package com.example.searchviewrecyclernavgraphflowsealed

import android.os.Bundle
import android.view.*
import android.widget.GridLayout
import android.widget.SearchView.OnQueryTextListener
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import androidx.recyclerview.widget.GridLayoutManager
import com.example.searchviewrecyclernavgraphflowsealed.RECYCLER.MyAdapter
import com.example.searchviewrecyclernavgraphflowsealed.RETROFIT.Status
import com.example.searchviewrecyclernavgraphflowsealed.databinding.Fragment1Binding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.util.Locale.filter

class Fragment1 : Fragment() {
    lateinit var binding: Fragment1Binding
    var adapter = MyAdapter()

    lateinit var vm: Fragment1ViewModel

    var listForFilter = mutableListOf<Movies>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = Fragment1Binding.inflate(inflater, container, false)
        vm = ViewModelProvider(this).get(Fragment1ViewModel::class.java)
        // val action = Fragment1Directions.actionFragment12ToFragment22()

        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        moviesToRecycler("69d66957eebff9666ea46bd464773cf0")
        // val   vm = ViewModelProvider(this).get(Fragment1ViewModel::class.java)

        initRecycler()

//        lifecycleScope.launch {
//
//            vm.getMovies("69d66957eebff9666ea46bd464773cf0")
//            vm.flow.collect { it?.let{
//                adapter.updateRecycler(it.toMutableList())
//            }}
//        }


    }

//
//    @Deprecated("Deprecated in Java")
//    override fun onCreateOptionsMenu(menu: Menu, inflater1: MenuInflater) {
//
//
//        val inflater: MenuInflater = inflater1
//        inflater.inflate(R.menu.upper_menu, menu)
//        val searchItem: MenuItem = menu.findItem(R.id.search_bar)
//        val searchView: SearchView = searchItem.actionView as SearchView
//
//        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
//            OnQueryTextListener {
//            override fun onQueryTextSubmit(p0: String?): Boolean {
//                return false
//
//            }
//
//            override fun onQueryTextChange(msg: String): Boolean {
//                if (msg.isNotEmpty()) {
//
//                    val newText = msg
//                    var l = vm.filter(msg, listForFilter)
//                    println("1"+l)
//                    adapter.updateRecycler( l)
//                }
//                return true
//            }
//
//        })
//
//
//
//    }

//    private fun filterFlow(msg: String) {
//        lifecycleScope.launch {
//
//         var successFlow =   vm.flow.filter {
//             it.status ==Status.SUCCESS  }
//            var mapedFlow = successFlow.map{
//                it.data }
//            val listFlow:List<Movies> = mapedFlow.filter {
//
//                it?.filter {
//                    it.title.length = msg.
//                }
//            }
//
//
//            // vm.flow.filter { it.status = Status.SUCCESS.data?.let {  contains(msg)} }
//        }
//    }


    fun moviesToRecycler(key: String) {

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                // две строик выше чтобы флоу работал как вьюмодель

                vm.getMovies(key)
                vm.flow.collect {
                    when (it.status) {

                        Status.ERROR -> {
                            println(it.mesage)
                        }
                        Status.SUCCESS -> {
                            it.data?.let {
                                adapter.updateRecycler(it.toMutableList())
//                                listForFilter = it.toMutableList()
                            }
                        }
                        Status.LOADING -> {
                            println(it.data)
                        }
                    }

                }
            }
        }


        val search = binding.searchBar
        if (search != null) {
            val mySearch = search as SearchView
            mySearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {

                    if (newText!!.isNotEmpty()) {
                        lifecycleScope.launch(Dispatchers.Main) {

                            vm.flow.collect {
                                it?.let {
                                    it.data?.let {
                                        var list1 = vm.filter(newText, it.toMutableList())
                                        adapter.updateRecycler(list1)
                                    }
                                }
                            }
                        }

                    }




                    return true
                }
            })
        }

    }

    fun initRecycler() {


        binding.recycler.adapter = adapter
        binding.recycler.layoutManager = GridLayoutManager(requireContext(), 2)
    }
}