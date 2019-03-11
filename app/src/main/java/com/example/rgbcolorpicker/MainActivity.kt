package com.example.rgbcolorpicker

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
import java.io.File
import java.io.FileInputStream
import java.io.ObjectInputStream

class MainActivity : AppCompatActivity() {

    private var savedFavorites = mutableListOf(
        "Favorites", 255.toString(), 255.toString(), 255.toString(),
        "Pinkish", 255.toString(), 7.toString(), 210.toString(),
        "Funny Green", 37.toString(), 236.toString(), 88.toString(),
        "That Blue", 37.toString(), 78.toString(), 255.toString(),
        "Teal", 15.toString(), 217.toString(), 212.toString(),
        "Reddish", 228.toString(), 18.toString(), 38.toString(),
        "Its a purple", 200.toString(), 10.toString(), 200.toString())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(this.findViewById(R.id.my_toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val savedColorNames = mutableListOf<String>()
        var count1 = 0
        while (count1 < savedFavorites.size) {
            if (count1 % 4 == 0) {
                savedColorNames.plusAssign(savedFavorites[count1])
            }
            count1 += 1
        }

        val savedRedValues = mutableListOf<String>()
        var count2 = 0
        while (count2 < savedFavorites.size) {
            if (count2 % 4 == 1) {
                savedRedValues.plusAssign(savedFavorites[count2])
            }
            count2 += 1
        }

        val savedGreenValues = mutableListOf<String>()
        var count3 = 0
        while (count3 < savedFavorites.size) {
            if (count3 % 4 == 2) {
                savedGreenValues.plusAssign(savedFavorites[count3])
            }
            count3 += 1
        }


        val savedBlueValues = mutableListOf<String>()
        var count4 = 0
        while (count4 < savedFavorites.size) {
            if (count4 % 4 == 3) {
                savedBlueValues.plusAssign(savedFavorites[count4])
            }
            count4 += 1
        }
        val textBoxRed = findViewById<EditText>(R.id.editTextRed)
        val textBoxGreen = findViewById<EditText>(R.id.editTextGreen)
        val textBoxBlue = findViewById<EditText>(R.id.editTextBlue)

        val seekBarRed = findViewById<SeekBar>(R.id.seekBarRed)
        val seekBarGreen = findViewById<SeekBar>(R.id.seekBarGreen)
        val seekBarBlue = findViewById<SeekBar>(R.id.seekBarBlue)

        val colorView = findViewById<ImageView>(R.id.imageView)

        var redValue: Int
        var greenValue: Int
        var blueValue: Int

        var saveColorName: String
        val textBoxName = findViewById<EditText>(R.id.nameText)
        val buttonSave:Button = findViewById(R.id.buttonSave)

        fun View.hideKeyboard() {
            val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(windowToken, 0)
        }

        fun colorSet(){
            redValue = seekBarRed.progress
            greenValue = seekBarGreen.progress
            blueValue = seekBarBlue.progress

            colorView.setBackgroundColor(Color.rgb(redValue, greenValue, blueValue))
        }

        val filePath = File(filesDir, "favorites")
        val favFile = File(filePath, "favorites.ser")
        val fileExists = favFile.exists()
        if (fileExists) {
            ObjectInputStream(FileInputStream(favFile)).use { savedFavorites = it.readObject() as MutableList<String> }
        }

        textBoxRed.setOnClickListener {
            this@MainActivity.seekBarRed.progress = Integer.parseInt(textBoxRed.text.toString())
            textBoxRed.hideKeyboard()
        }

        seekBarRed.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                // Display the current progress of SeekBar
                this@MainActivity.editTextRed.text = Editable.Factory.getInstance().newEditable(seekBarRed.progress.toString())

                colorSet()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
                textBoxName.text.clear()

            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                val redHex = java.lang.Integer.toHexString(seekBarRed.progress).format("%02")
                val greenHex = java.lang.Integer.toHexString(seekBarGreen.progress).format("%02")
                val blueHex = java.lang.Integer.toHexString(seekBarBlue.progress).format("%02")
                val hexValue = "$redHex$greenHex$blueHex".toUpperCase()
                Toast.makeText(this@MainActivity, "#$hexValue", Toast.LENGTH_LONG).show()
            }
        })

        textBoxBlue.setOnClickListener {
            this@MainActivity.seekBarBlue.progress = Integer.parseInt(textBoxBlue.text.toString())
            textBoxBlue.hideKeyboard()
        }

        seekBarBlue.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                // Display the current progress of SeekBar
                this@MainActivity.editTextBlue.text = Editable.Factory.getInstance().newEditable(seekBarBlue.progress.toString())

                colorSet()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
                textBoxName.text.clear()

            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                val redHex = java.lang.Integer.toHexString(seekBarRed.progress).format("%02")
                val greenHex = java.lang.Integer.toHexString(seekBarGreen.progress).format("%02")
                val blueHex = java.lang.Integer.toHexString(seekBarBlue.progress).format("%02")
                val hexValue = "$redHex$greenHex$blueHex".toUpperCase()
                Toast.makeText(this@MainActivity, "#$hexValue", Toast.LENGTH_LONG).show()
            }
        })

        textBoxGreen.setOnClickListener {
            this@MainActivity.seekBarGreen.progress = Integer.parseInt(textBoxGreen.text.toString())
            textBoxGreen.hideKeyboard()
        }

        seekBarGreen.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                // Display the current progress of SeekBar
                this@MainActivity.editTextGreen.text = Editable.Factory.getInstance().newEditable(seekBarGreen.progress.toString())

                colorSet()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
                textBoxName.text.clear()
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                val redHex = java.lang.Integer.toHexString(seekBarRed.progress)
                val greenHex = java.lang.Integer.toHexString(seekBarGreen.progress)
                val blueHex = java.lang.Integer.toHexString(seekBarBlue.progress)
                val hexValue = "$redHex$greenHex$blueHex".toUpperCase()
                Toast.makeText(this@MainActivity, "#$hexValue", Toast.LENGTH_LONG).show()
            }
        })

        textBoxName.setOnClickListener {
            saveColorName = textBoxName.text.toString()
            textBoxName.hideKeyboard()
        }

        buttonSave.setOnClickListener{
            saveColorName = textBoxName.text.toString()

            if(saveColorName != "") {
                redValue = seekBarRed.progress
                greenValue = seekBarGreen.progress
                blueValue = seekBarBlue.progress

                if (savedFavorites.contains(saveColorName)){
                    Toast.makeText(this, "You already have this color in favorites!", Toast.LENGTH_SHORT).show()
                }
                else {
                    savedFavorites.plusAssign(element = saveColorName)
                    savedFavorites.plusAssign(element = redValue.toString())
                    savedFavorites.plusAssign(element = greenValue.toString())
                    savedFavorites.plusAssign(element = blueValue.toString())

                    savedColorNames.plusAssign(saveColorName)
                    savedRedValues.plusAssign(redValue.toString())
                    savedGreenValues.plusAssign(greenValue.toString())
                    savedBlueValues.plusAssign(blueValue.toString())
                    // TODO make this actually save to file
                    //ObjectOutputStream(FileOutputStream(favFile)).use { it.writeObject(savedFavorites) }
                    //Toast.makeText(this, "New color saved to file!", Toast.LENGTH_SHORT).show()
                    Toast.makeText(this, getString(R.string.new_color_set), Toast.LENGTH_SHORT).show()
                    }

                }
            else {
                Toast.makeText(this, getString(R.string.error_name_color), Toast.LENGTH_SHORT).show()
                }
            }

        val spinnerFavorites = findViewById<Spinner>(R.id.spinnerTest)
        val aa = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, savedColorNames)
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerFavorites.adapter = aa
        spinnerFavorites.setSelection(0)

        spinnerFavorites.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
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

                if(position > 0)
                    textBoxName.setText(savedColorNames[position])

                colorView.setBackgroundColor(Color.rgb(redValue, greenValue, blueValue))            }
        }

    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.save -> {
            // TODO set to save

            Toast.makeText(this, "New Color Saved!", Toast.LENGTH_SHORT).show()

            true
        }

        R.id.spinner -> {
            // TODO change from spinner to select color
            true
        }

        else -> {
            // Recommended overflow
            super.onOptionsItemSelected(item)
        }
    }

/*    override fun onStop() {
        val favFile = "favorites.ser"
        ObjectOutputStream(FileOutputStream(favFile)).use{ it.writeObject(savedFavorites)}
        super.onStop()
    }*/

}
