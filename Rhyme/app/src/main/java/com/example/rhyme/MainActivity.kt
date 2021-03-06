package com.example.rhyme

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.EditText
import com.example.rhyme.Words
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    //check the words list for the word and show it on the view
    fun rhyming(v: View){
        var w = ""
        var p = 4
        var v = 'a'
        var rhymWords = ""
        var words = Words()
        var wrds = words.words_raw.split("\n")
        var vowels = "aeiou"
        if(findViewById<EditText>(R.id.word).text.toString() != ""){
            w = findViewById<EditText>(R.id.word).text.toString()
            if(findViewById<EditText>(R.id.precision).text.toString() != ""){
                p = findViewById<EditText>(R.id.precision).text.toString().toInt()
            }
            if(p > w.length){
                p = w.length // makes sure that the precision length doesn't exceed the word length
            }
            for (wrd in wrds){
                if(wrd != w) {
                    if (wrd.length > p) {
                        if (wrd.reversed().substring(0, p).toString() == w.reversed()
                                .substring(0, p).toString()
                        ) {
                            if (findViewById<CheckBox>(R.id.prefix).isChecked) {
                                for (c in w) {
                                    if (c in vowels) {
                                        v = c
                                        break
                                    }
                                }
                                if (v in wrd.substring(0, 3)) {
                                    rhymWords += "\n"
                                    rhymWords += wrd
                                }
                                continue
                            } else {
                                rhymWords += "\n"
                                rhymWords += wrd
                            }
                        }
                    }
                }
            }
            if(findViewById<TextView>(R.id.list).text.toString() == ""){
                var myList = findViewById<TextView>(R.id.list).apply {
                    text = "no rhyming words found"
                }
            }
            else{
                var myList = findViewById<TextView>(R.id.list).apply {
                    text = rhymWords
                }
            }

        }
        else{
            var myList = findViewById<TextView>(R.id.list).apply {
                text = "write a word first."
            }
        }
    }

}