package com.example.imageview

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var imgView: ImageView
    lateinit var textView: TextView
    lateinit var goBtn: Button
    lateinit var homeBtn: Button

    private val viewModel: MainActivityVM by lazy {
        MainActivityVM()
    }
    private val parisText: String by lazy {
        this.resources.getString(R.string.go_to_paris)
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        CoroutineScope(Dispatchers.Main).launch {
            viewModel.numState.collectLatest {
                textView.text = "$parisText: $it"
            }
        }
    }

    private fun initViews(){
        imgView = findViewById(R.id.imgFood)
        textView = findViewById(R.id.foodTitle)
        goBtn = findViewById(R.id.goBtn)
        homeBtn = findViewById(R.id.homeBtn)
        initListeners()
    }

    private fun initListeners(){
        goBtn.setOnClickListener { viewModel.increaseCount() }
        homeBtn.setOnClickListener { viewModel.decreaseCount() }
    }



}