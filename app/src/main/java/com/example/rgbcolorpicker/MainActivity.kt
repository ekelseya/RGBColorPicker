package com.example.rgbcolorpicker

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*
import kotlinx.android.synthetic.main.activity_main.*
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.io.OutputStreamWriter


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(this.findViewById(R.id.my_toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        fun View.hideKeyboard() {
            val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(windowToken, 0)
        }

        //The function below doesn't work (but it should)
        fun View.setColor() {
            val colorView = findViewById<ImageView>(R.id.imageView)
            val redValue = seekBarRed.progress
            val greenValue = seekBarGreen.progress
            val blueValue = seekBarBlue.progress

            colorView.setBackgroundColor(Color.rgb(redValue, greenValue, blueValue))
        }

        val textBoxRed = findViewById<EditText>(R.id.editTextRed)
        textBoxRed.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                this@MainActivity.seekBarRed.progress = Integer.parseInt(textBoxRed.text.toString())
                textBoxRed.hideKeyboard()
            }
        })

        val seekBarRed = findViewById<SeekBar>(R.id.seekBarRed)
        seekBarRed.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                // Display the current progress of SeekBar
                this@MainActivity.editTextRed.text = Editable.Factory.getInstance().newEditable(seekBarRed.progress.toString())

                val colorView = findViewById<ImageView>(R.id.imageView)
                val redValue = seekBarRed.progress
                val greenValue = seekBarGreen.progress
                val blueValue = seekBarBlue.progress

                colorView.setBackgroundColor(Color.rgb(redValue, greenValue, blueValue))
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                val redValue = java.lang.Integer.toHexString(seekBarRed.progress)
                val greenValue = java.lang.Integer.toHexString(seekBarGreen.progress)
                val blueValue = java.lang.Integer.toHexString(seekBarBlue.progress)
                val hexValue = "$redValue$greenValue$blueValue".toUpperCase()
                Toast.makeText(this@MainActivity, "#$hexValue", Toast.LENGTH_LONG).show()
            }
        })

        val textBoxBlue = findViewById<EditText>(R.id.editTextBlue)
        textBoxBlue.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                this@MainActivity.seekBarBlue.progress = Integer.parseInt(textBoxBlue.text.toString())
                textBoxBlue.hideKeyboard()

            }
        })

        val seekBarBlue = findViewById<SeekBar>(R.id.seekBarBlue)
        seekBarBlue.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                // Display the current progress of SeekBar
                this@MainActivity.editTextBlue.text = Editable.Factory.getInstance().newEditable(seekBarBlue.progress.toString())

                val colorView = findViewById<ImageView>(R.id.imageView)
                val redValue = seekBarRed.progress
                val greenValue = seekBarGreen.progress
                val blueValue = seekBarBlue.progress

                colorView.setBackgroundColor(Color.rgb(redValue, greenValue, blueValue))
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                val redValue = java.lang.Integer.toHexString(seekBarRed.progress)
                val greenValue = java.lang.Integer.toHexString(seekBarGreen.progress)
                val blueValue = java.lang.Integer.toHexString(seekBarBlue.progress)
                val hexValue = "$redValue$greenValue$blueValue".toUpperCase()
                Toast.makeText(this@MainActivity, "#$hexValue", Toast.LENGTH_LONG).show()
            }
        })

        val textBoxGreen = findViewById<EditText>(R.id.editTextGreen)
        textBoxGreen.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                this@MainActivity.seekBarGreen.progress = Integer.parseInt(textBoxGreen.text.toString())
                textBoxGreen.hideKeyboard()
            }
        })

        val seekBarGreen = findViewById<SeekBar>(R.id.seekBarGreen)
        seekBarGreen.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                // Display the current progress of SeekBar
                this@MainActivity.editTextGreen.text = Editable.Factory.getInstance().newEditable(seekBarGreen.progress.toString())

                val colorView = findViewById<ImageView>(R.id.imageView)
                val redValue = seekBarRed.progress
                val greenValue = seekBarGreen.progress
                val blueValue = seekBarBlue.progress

                colorView.setBackgroundColor(Color.rgb(redValue, greenValue, blueValue))
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                val redValue = java.lang.Integer.toHexString(seekBarRed.progress)
                val greenValue = java.lang.Integer.toHexString(seekBarGreen.progress)
                val blueValue = java.lang.Integer.toHexString(seekBarBlue.progress)
                val hexValue = "$redValue$greenValue$blueValue".toUpperCase()
                Toast.makeText(this@MainActivity, "#$hexValue", Toast.LENGTH_LONG).show()
            }
        })


        val textBoxName = findViewById<EditText>(R.id.nameText)
        textBoxName.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                var save_color_name = textBoxName.text
                textBoxName.hideKeyboard()
            }
        })

    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.save -> {
            val save_color_name = findViewById<EditText>(R.id.nameText).text
            val redValue = seekBarRed.progress.toString()
            val greenValue = seekBarGreen.progress.toString()
            val blueValue = seekBarBlue.progress.toString()
            //TODO val favoriteList: MutableList<String> = ArrayList()
            val nameLine = "$save_color_name + ' ' + $redValue + ' ' + $greenValue + ' ' + $blueValue"
            try {
                val file = OutputStreamWriter(openFileOutput("colorNames.txt", Activity.MODE_PRIVATE))
                file.write (nameLine)
                file.flush ()
                file.close ()
            } catch (e : IOException) {
            }
            Toast.makeText(this, "New Color Saved!", Toast.LENGTH_SHORT).show()

            true
        }

        R.id.spinner -> {
            val spinner = findViewById<Spinner>(R.id.spinner)
            val savedFavorites = mutableListOf<String>()

            if(fileList().contains("colorNames.txt")) {
                try {
                    val file = InputStreamReader(openFileInput("colorNames.txt"))
                    val br = BufferedReader(file)
                    br.useLines { lines -> lines.forEach { savedFavorites.add(it) } }
                    br.close()
                    file.close()
                }
                catch (e:IOException) {
                }
            }
            val colorNamesArray = mutableListOf<String>()
            for (x in savedFavorites)
                if (x % 3 == 0)
                    colorNamesArray.add(x)
            colorNamesArray.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

            spinner.adapter = adapter

            true
        }

        else -> {
            // Recommended overflow
            super.onOptionsItemSelected(item)
        }
    }
}
