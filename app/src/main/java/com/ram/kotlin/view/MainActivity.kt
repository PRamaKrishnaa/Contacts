package com.ram.kotlin.view

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager.VERTICAL
import com.ram.kotlin.R
import com.ram.kotlin.adapter.UserAdapter
import com.ram.kotlin.viewmodel.UsersViewModel
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    lateinit var recyclerAdapter: UserAdapter
    lateinit var viewModel: UsersViewModel

    lateinit var searchView: SearchView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button_add_user.setOnClickListener {
            val intent = Intent(this, AddUserActivity::class.java)
            startActivity(intent)
        }

        usersListView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            recyclerAdapter = UserAdapter()
            adapter = recyclerAdapter
            val divider = DividerItemDecoration(applicationContext, VERTICAL)
            addItemDecoration(divider)
        }

        viewModel = ViewModelProviders.of(this).get(UsersViewModel::class.java)

        //To display data
        showData()

    }

    private fun showData() {
        viewModel.getAllUsersObserver().observe(this, Observer {
            recyclerAdapter.setListData(ArrayList(it))
            recyclerAdapter.notifyDataSetChanged()
        })
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_main, menu)

        // Associate searchable configuration with the SearchView
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchView = menu.findItem(R.id.action_search).actionView as SearchView
        searchView.setSearchableInfo(
            searchManager.getSearchableInfo(componentName)
        )
        searchView.maxWidth = Int.MAX_VALUE


        // listening to search query text change
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                // filter recycler view when query submitted
                recyclerAdapter.filter?.filter(query)
                return false
            }

            override fun onQueryTextChange(query: String?): Boolean {
                // filter recycler view when text is changed
                recyclerAdapter.filter?.filter(query)
                return false
            }
        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId


        return if (id == R.id.action_search) {
            true
        } else super.onOptionsItemSelected(item)

    }

    override fun onBackPressed() {
        // close search view on back button pressed
        if (!searchView.isIconified) {
            searchView.isIconified = true
            return
        }
        finish()

    }
}


