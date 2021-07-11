package com.example.marvelapplication.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.marvelapplication.R
import com.example.marvelapplication.adapter.CharactersPagedAdapter
import com.example.marvelapplication.viewmodels.MarvelDetailsViewModel
import com.example.marvelapplication.model.Result
import com.example.marvelapplication.utils.Constants.SPLASH_INTENT_TAG
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.main_activity.*


@AndroidEntryPoint
class MainActivity : AppCompatActivity(), CharactersPagedAdapter.AdapterInterface {

    private val viewModel: MarvelDetailsViewModel by viewModels()
    lateinit var list: List<Result>
//    var listOfIds: MutableList<Int> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        list = intent.extras?.get(SPLASH_INTENT_TAG) as List<Result>
        Log.d("tag", list.toString())

        val charactersAdapter = CharactersPagedAdapter(this, this)

        //If i had more time i whould have change it to be able to run with many ids
        list[0].id?.let { viewModel.getCharacters(it, 0) }

        val layoutManager = LinearLayoutManager(this)
        marvel_character_list.layoutManager = layoutManager
        marvel_character_list.setHasFixedSize(true)
        marvel_character_list.adapter = charactersAdapter

        viewModel.marvelSeriesResponse.observe(this, androidx.lifecycle.Observer {
            charactersAdapter.update(it)
            progress_bar.visibility = View.GONE
        })

    }

    override fun lastItemOnViewCreated(position: Int) {
        if (viewModel.doIHaveMoreCharacters(position+1)) {
            progress_bar.visibility = View.VISIBLE
        }
        list[0].id?.let { viewModel.getCharacters(it, position + 1) }
        Toast.makeText(this, viewModel.getToastMessage(position + 1), Toast.LENGTH_SHORT).show()
    }

}