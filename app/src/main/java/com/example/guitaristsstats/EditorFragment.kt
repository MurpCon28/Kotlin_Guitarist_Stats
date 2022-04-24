package com.example.guitaristsstats

import android.app.Activity
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.guitaristsstats.data.Images
import com.example.guitaristsstats.databinding.EditorFragmentBinding
import kotlinx.android.synthetic.main.editor_fragment.*

class EditorFragment : Fragment() {

    private lateinit var viewModel: EditorViewModel
    private val args: EditorFragmentArgs by navArgs()
    private lateinit var binding: EditorFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //shows when user hits to edit a guitarist a check on screen to return to home screen of app
        (activity as AppCompatActivity).supportActionBar?.let {
            it.setHomeButtonEnabled(true)
            it.setDisplayShowHomeEnabled(true)
            it.setDisplayHomeAsUpEnabled(true)
            it.setHomeAsUpIndicator(R.drawable.ic_check)
        }
        setHasOptionsMenu(true)

        //making the title of the activity
        requireActivity().title =
            //if the guitarId equals to NEW_GUITAR_ID the activity name is changed to new guiartist
            if (args.guitarId == NEW_GUITAR_ID) {
                getString(R.string.new_guitarist)
            } else {
            //if the guitarId does not equal to NEW_GUITAR_ID the activity is changed to edit guitarist
                getString(R.string.edit_guitarist)
            }

//        var image = Images("myFile")
//        Log.i("imageLogging", image.toString())

        viewModel = ViewModelProvider(this).get(EditorViewModel::class.java)

        binding = EditorFragmentBinding.inflate(inflater, container, false)
        binding.editName.setText("")
        binding.editBand.setText("")
        binding.editDob.setText("")
        binding.editGenre.setText("")
        binding.editGuitarType.setText("")
        binding.editStatus.setText("")

//        binding.guitarist01.setImageResource(R.drawable.guitarist01)




        //when a user uses the android back button or swipe it does the same save and return as the check button
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    saveAndReturn()
                }
            }
        )

        //observes the item choosen and if the item is changed in any way it does saves data
        viewModel.currentGuitar.observe(viewLifecycleOwner, Observer {
            //retriving values for config changes
            val guitaristName = savedInstanceState?.getString(GUITAR_TEXT_KEY)
            val bandName = savedInstanceState?.getString(BAND_TEXT_KEY)
            val dob = savedInstanceState?.getString(DOB_TEXT_KEY)
            val genre = savedInstanceState?.getString(GENRE_TEXT_KEY)
            val guitarType = savedInstanceState?.getString(GUITARTYPE_TEXT_KEY)
            val status = savedInstanceState?.getString(STATUS_TEXT_KEY)
//            val guitaristImage = savedInstanceState?.getString(IMAGE_TEXT_KEY)

            val cursorPosition = savedInstanceState?.getInt(CURSOR_POSITION_KEY) ?:0

            binding.editName.setText(guitaristName ?: it.name)
            binding.editBand.setText(bandName ?: it.band)
            binding.editDob.setText(dob ?: it.dob)
            binding.editGenre.setText(genre ?: it.genre)
            binding.editGuitarType.setText(guitarType ?: it.guitarType)
            binding.editStatus.setText(status ?: it.status)

            //code for images when click an object - they are hard coded so you cant add or edit. Images are saved as a string
            if (it.guitaristImage.equals("guitarist01"))
                binding.guitaristImage.setImageResource(R.drawable.guitarist01)
            else if (it.guitaristImage.equals("guitarist02"))
                binding.guitaristImage.setImageResource(R.drawable.guitarist02)
            else if (it.guitaristImage.equals("guitarist03"))
                binding.guitaristImage.setImageResource(R.drawable.guitarist03)
            else if (it.guitaristImage.equals("guitarist04"))
                binding.guitaristImage.setImageResource(R.drawable.guitarist04)
            else if (it.guitaristImage.equals("guitarist05"))
                binding.guitaristImage.setImageResource(R.drawable.guitarist05)
            else if (it.guitaristImage.equals("guitarist06"))
                binding.guitaristImage.setImageResource(R.drawable.guitarist06)
            else if (it.guitaristImage.equals("guitarist07"))
                binding.guitaristImage.setImageResource(R.drawable.guitarist07)
            else if (it.guitaristImage.equals("guitarist08"))
                binding.guitaristImage.setImageResource(R.drawable.guitarist08)
            else if (it.guitaristImage.equals("guitarist09"))
                binding.guitaristImage.setImageResource(R.drawable.guitarist09)
            else if (it.guitaristImage.equals("guitarist10"))
                binding.guitaristImage.setImageResource(R.drawable.guitarist10)
            else if (it.guitaristImage.equals("guitarist11"))
                binding.guitaristImage.setImageResource(R.drawable.guitarist11)
            else if (it.guitaristImage.equals("guitarist12"))
                binding.guitaristImage.setImageResource(R.drawable.guitarist12)

//            binding.guitaristImage.setImageResource(guitaristImage ?: it.guitaristImage)
            binding.editName.setSelection(cursorPosition)
            binding.editBand.setSelection(cursorPosition)
            binding.editDob.setSelection(cursorPosition)
            binding.editGenre.setSelection(cursorPosition)
            binding.editGuitarType.setSelection(cursorPosition)
            binding.editStatus.setSelection(cursorPosition)
//            binding.guitaristImage.setSelection(cursorPosition)
        })
        viewModel.getGuitarById(args.guitarId)

        return binding.root
    }


    //when the users presses the check button it saves and returns the users back to the app home screen
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            android.R.id.home -> saveAndReturn()
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun saveAndReturn(): Boolean {
        //after user has edit entity the keyboard is still on screen, this code helps remove the keyboard when returning to the home page of the app
        val imm = requireActivity()
            .getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        //closes the keyboard as the fragment closes
        imm.hideSoftInputFromWindow(binding.root.windowToken,0)

        //code changes the newly edited entity and replaces the old one
        viewModel.currentGuitar.value?.name = binding.editName.text.toString()
        viewModel.currentGuitar.value?.band = binding.editBand.text.toString()
        viewModel.currentGuitar.value?.dob = binding.editDob.text.toString()
        viewModel.currentGuitar.value?.genre = binding.editGenre.text.toString()
        viewModel.currentGuitar.value?.guitarType = binding.editGuitarType.text.toString()
        viewModel.currentGuitar.value?.status = binding.editStatus.text.toString()
//        viewModel.currentGuitar.value?.guitaristImage = binding.guitaristImage.toString()
        viewModel.updateGuitar()

        Thread.sleep(1000) // I needed this to ensure background thread completed

        findNavController().navigateUp()
        return true
    }

    //function for config changes
    override fun onSaveInstanceState(outState: Bundle) {
      //  with(binding.editName && binding.editBand) {
            outState.putString(GUITAR_TEXT_KEY, binding.editName.text.toString())
            outState.putString(BAND_TEXT_KEY, binding.editBand.text.toString())
            outState.putString(DOB_TEXT_KEY, binding.editDob.text.toString())
            outState.putString(GENRE_TEXT_KEY, binding.editGenre.text.toString())
            outState.putString(GUITARTYPE_TEXT_KEY, binding.editGuitarType.text.toString())
            outState.putString(STATUS_TEXT_KEY, binding.editStatus.text.toString())
//            outState.putString(IMAGE_TEXT_KEY, binding.guitaristImage.toString())


//            outState.putInt(CURSOR_POSITION_KEY, selectionStart)
     //   }
        super.onSaveInstanceState(outState)
    }
}