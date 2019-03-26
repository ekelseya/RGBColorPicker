package com.example.rgbcolorpicker

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.View.VISIBLE
import android.view.inputmethod.InputMethodManager
import android.widget.*
import kotlinx.android.synthetic.main.activity_main.*
import java.io.*




class MainActivity : AppCompatActivity() {

    private var savedFavorites = mutableListOf(
        "Favorites", 255.toString(), 255.toString(), 255.toString(),
        "Pinkish", 255.toString(), 7.toString(), 210.toString(),
        "Funny Green", 37.toString(), 236.toString(), 88.toString(),
        "That Blue", 37.toString(), 78.toString(), 255.toString(),
        "Teal", 15.toString(), 217.toString(), 212.toString(),
        "Reddish", 228.toString(), 18.toString(), 38.toString(),
        "Its a purple", 200.toString(), 10.toString(), 200.toString())

    private var arrayColorIntent = arrayListOf<Int>(0, 0, 0)

    var redValue = 255
    var greenValue = 255
    var blueValue = 255

    private var saveColorName = ""

    val savedColorNames = mutableListOf<String>()

    private fun loadNames(){
        var count1 = 0
        while (count1 < savedFavorites.size) {
            if (count1 % 4 == 0) {
                savedColorNames.plusAssign(savedFavorites[count1])
            }
            count1 += 1
        }
    }

    val savedRedValues = mutableListOf<String>()

    private fun loadRed(){
        var count2 = 0
        while (count2 < savedFavorites.size) {
            if (count2 % 4 == 1) {
                savedRedValues.plusAssign(savedFavorites[count2])
            }
            count2 += 1
        }
    }

    val savedGreenValues = mutableListOf<String>()

    private fun loadGreen(){
        var count3 = 0
        while (count3 < savedFavorites.size) {
            if (count3 % 4 == 2) {
                savedGreenValues.plusAssign(savedFavorites[count3])
            }
            count3 += 1
        }
    }

    val savedBlueValues = mutableListOf<String>()

    private fun loadBlue(){
        var count4 = 0
        while (count4 < savedFavorites.size) {
            if (count4 % 4 == 3) {
                savedBlueValues.plusAssign(savedFavorites[count4])
            }
            count4 += 1
        }
    }

    private fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(this.findViewById(R.id.my_toolbar))

        val favFile = File(filesDir, "favorites")
        if(favFile.exists()){
            ObjectInputStream(FileInputStream(favFile)).use { it ->
                val loadedFavorites = it.readObject()
                when (loadedFavorites) {
                    is MutableList<*> -> Log.i("Load", "Deserialized")
                    else -> Log.i("Load", "Failed")
                }
                savedFavorites = loadedFavorites as MutableList<String>
            }
        }

        val buttonBlend = findViewById<Button>(R.id.button_blend)

        val info = intent.extras
        if (info != null) {
            if (info.containsKey("color")) {
                button_blend!!.visibility = VISIBLE
            }
        }

        buttonBlend.setOnClickListener {
            val i = Intent()
            i.putExtra("red", arrayColorIntent[0])
            i.putExtra("green", arrayColorIntent[1])
            i.putExtra("blue", arrayColorIntent[2])

            setResult(RESULT_OK, i)

            finish()
            }

        val colorView = findViewById<ImageView>(R.id.imageView)

        val textBoxRed = findViewById<EditText>(R.id.editTextRed)
        textBoxRed.setOnClickListener {
            this@MainActivity.seekBarRed.progress = Integer.parseInt(textBoxRed.text.toString())
            textBoxRed.hideKeyboard()
        }

        val textBoxGreen = findViewById<EditText>(R.id.editTextGreen)
        textBoxGreen.setOnClickListener {
            this@MainActivity.seekBarGreen.progress = Integer.parseInt(textBoxGreen.text.toString())
            textBoxGreen.hideKeyboard()
        }

        val textBoxBlue = findViewById<EditText>(R.id.editTextBlue)
        textBoxBlue.setOnClickListener {
            this@MainActivity.seekBarBlue.progress = Integer.parseInt(textBoxBlue.text.toString())
            textBoxBlue.hideKeyboard()
        }

        val textBoxName = findViewById<EditText>(R.id.nameText)
        textBoxName.setOnClickListener {
            saveColorName = textBoxName.text.toString()
            textBoxName.hideKeyboard()
        }

        fun colorSet() {
            redValue = seekBarRed.progress
            greenValue = seekBarGreen.progress
            blueValue = seekBarBlue.progress

            colorView.setBackgroundColor(Color.rgb(redValue, greenValue, blueValue))

            arrayColorIntent[0] = redValue
            arrayColorIntent[1] = greenValue
            arrayColorIntent[2] = blueValue

        }

        val seekBarRed = findViewById<SeekBar>(R.id.seekBarRed)
        seekBarRed.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                this@MainActivity.editTextRed.text =
                    Editable.Factory.getInstance().newEditable(seekBarRed.progress.toString())

                colorSet()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
                textBoxName.text.clear()

            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {

            }
        })

        val seekBarGreen = findViewById<SeekBar>(R.id.seekBarGreen)
        seekBarGreen.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                // Display the current progress of SeekBar
                this@MainActivity.editTextGreen.text =
                    Editable.Factory.getInstance().newEditable(seekBarGreen.progress.toString())

                colorSet()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
                textBoxName.text.clear()
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {

            }
        })

        val seekBarBlue = findViewById<SeekBar>(R.id.seekBarBlue)
        seekBarBlue.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                // Display the current progress of SeekBar
                this@MainActivity.editTextBlue.text =
                    Editable.Factory.getInstance().newEditable(seekBarBlue.progress.toString())

                colorSet()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
                textBoxName.text.clear()

            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {

            }
        })

        val buttonSave: Button = findViewById(R.id.buttonSave)
        buttonSave.setOnClickListener {
            saveColorName = textBoxName.text.toString()

            if (saveColorName != "") {
                redValue = seekBarRed.progress
                greenValue = seekBarGreen.progress
                blueValue = seekBarBlue.progress

                arrayColorIntent[0] = redValue
                arrayColorIntent[1] = greenValue
                arrayColorIntent[2] = blueValue

                if (savedFavorites.contains(saveColorName)) {

                    Toast.makeText(this, "You already have this color in favorites!", Toast.LENGTH_SHORT).show()
                } else {
                    savedFavorites.plusAssign(element = saveColorName)
                    savedFavorites.plusAssign(element = redValue.toString())
                    savedFavorites.plusAssign(element = greenValue.toString())
                    savedFavorites.plusAssign(element = blueValue.toString())

                    savedColorNames.plusAssign(saveColorName)
                    savedRedValues.plusAssign(redValue.toString())
                    savedGreenValues.plusAssign(greenValue.toString())
                    savedBlueValues.plusAssign(blueValue.toString())

                    Toast.makeText(this, getString(R.string.new_color_set), Toast.LENGTH_LONG).show()
                }
            } else {
                Toast.makeText(this, getString(R.string.error_name_color), Toast.LENGTH_LONG).show()
            }

        }

        val favSpin = findViewById<Spinner>(R.id.spinnerFavorite)
        loadNames()
        loadRed()
        loadGreen()
        loadBlue()
        val aa = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, savedColorNames)
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        favSpin.adapter = aa
        favSpin.setSelection(0)

        favSpin.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                Toast.makeText(this@MainActivity, "Nothing Selected", Toast.LENGTH_LONG).show()
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                redValue = savedRedValues[position].toInt()
                greenValue = savedGreenValues[position].toInt()
                blueValue = savedBlueValues[position].toInt()

                seekBarRed.progress = Integer.parseInt(savedRedValues[position])
                seekBarGreen.progress = Integer.parseInt(savedGreenValues[position])
                seekBarBlue.progress = Integer.parseInt(savedBlueValues[position])

                if (position > 0) {
                    nameText?.setText(savedColorNames[position])
                    imageView?.setBackgroundColor(Color.rgb(redValue, greenValue, blueValue))
                }
                favSpin.visibility = View.INVISIBLE
            }
        }


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.save -> {
            val favFile = File(filesDir, "favorites")
            ObjectOutputStream(FileOutputStream(favFile)).use { it -> it.writeObject(savedFavorites) }

            Toast.makeText(this, "New Color Saved!", Toast.LENGTH_SHORT).show()

            true
        }

        R.id.favorites -> {
            spinnerFavorite?.visibility = View.VISIBLE

            true
        }
        else -> {
            // Recommended overflow
            super.onOptionsItemSelected(item)
        }
    }
}
