package com.example.guitaristsstats

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.guitaristsstats.data.GuitarEntity
import com.example.guitaristsstats.databinding.MainFragmentBinding

class MainFragment : Fragment(),
    GuitarsListAdapter.ListItemListener {

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: MainFragmentBinding
    private lateinit var adapter: GuitarsListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        //after the user presses the check button on the edit screen this code makes sure the check button doesnt appear on the apps home screen
        (activity as AppCompatActivity)
            .supportActionBar?.setDisplayHomeAsUpEnabled(false)

        //sets the an option menu to true
        setHasOptionsMenu(true)

        //makes the activity name on the home page of the app the name of the app
        requireActivity().title = getString(R.string.app_name)

        binding = MainFragmentBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        with(binding.recylerView) {
            setHasFixedSize(true)
            val divider = DividerItemDecoration(
                context, LinearLayoutManager(context).orientation
            )
            addItemDecoration(divider)
        }

        viewModel.guitarsList?.observe(viewLifecycleOwner, Observer {
            Log.i("guitarLogging",it.toString())
            adapter = GuitarsListAdapter(it, this@MainFragment)
            binding.recylerView.adapter = adapter
            binding.recylerView.layoutManager = LinearLayoutManager(activity)

            //restores saved selected guitarists
            val selectedGuitarists =
                savedInstanceState?.getParcelableArrayList<GuitarEntity>(SELECTED_GUITARTISTS_KEY)
            adapter.selectedGuitars.addAll(selectedGuitarists ?: emptyList())
        })

        //add functionality to the add button
        binding.floatingActionButton.setOnClickListener{
            editGuitarist(NEW_GUITAR_ID)
        }

        return binding.root
    }

    //creates the three dot menu
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        val menuId =
            if (this::adapter.isInitialized &&
                    adapter.selectedGuitars.isNotEmpty()) {
                R.menu.menu_main_selected_items
            } else {
                R.menu.menu_main
            }
        inflater.inflate(menuId, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    //adds option in the three dot menu for the user to choose
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.action_sample_data -> addSampleData()
            R.id.action_delete -> deleteSelectedGuitars()
            R.id.action_delete_all -> deleteAllGuitars()
            else -> super.onOptionsItemSelected(item)
        }
    }

    //when user chooses this option deletes every guitars from the database
    private fun deleteAllGuitars(): Boolean {
        viewModel.deleteAllGuitars()
        return true
    }

    //removes/deletes the data selected from the database
    private fun deleteSelectedGuitars(): Boolean {
        viewModel.deleteGuitars(adapter.selectedGuitars)
        //after item is deleted the option menu reappears
        Handler(Looper.getMainLooper()).postDelayed({
            adapter.selectedGuitars.clear()
            requireActivity().invalidateOptionsMenu()
        },100)
        return true
    }

    //when user press the add data button code directs to view model addSampleData() function
    private fun addSampleData(): Boolean {
        viewModel.addSampleData()
        return true
    }


    override fun editGuitarist(guitarId: Int) {
        Log.i(TAG,"onItemClick: recieved guitar id $guitarId ")
        val action = MainFragmentDirections.actionEditGuitar(guitarId)
        findNavController().navigate(action)
    }

    override fun onItemSelectionChanged() {
        requireActivity().invalidateOptionsMenu()
    }

    //function to save selected guiartists on home page for config changes
    override fun onSaveInstanceState(outState: Bundle) {
        if (this::adapter.isInitialized) {
            outState.putParcelableArrayList(SELECTED_GUITARTISTS_KEY,
            adapter.selectedGuitars)
        }
        super.onSaveInstanceState(outState)
    }
}