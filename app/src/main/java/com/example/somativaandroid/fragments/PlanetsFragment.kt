import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.activity.viewModels
import androidx.fragment.app.viewModels

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.somativaandroid.R
import com.example.somativaandroid.databinding.HomepageBinding
import com.example.somativaandroid.recyclerviewpackage.PlanetSingleton
import retrofit2.Response


class PlanetsFragment: Fragment() {

    private lateinit var binding: HomepageBinding
    private lateinit var planetAdapter: PlanetAdapter

    // ViewModel instance (using the viewModels delegate)
    private val planetsViewModel: PlanetsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment using DataBindingUtil
        binding = DataBindingUtil.inflate(inflater, R.layout.homepage, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize the RecyclerView and the PlanetAdapter
        planetAdapter = PlanetAdapter()
        binding.recyclerView.adapter = planetAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.addItemDecoration(MarginItemDecoration(22))

        // Observe LiveData from the ViewModel
        planetsViewModel.planets.observe(viewLifecycleOwner, Observer { exoplanets ->
            exoplanets?.let {
                // Update the PlanetSingleton and the adapter
                PlanetSingleton.planetList.clear()
                PlanetSingleton.planetList.addAll(it)
                planetAdapter.notifyDataSetChanged()
            }
        })

        planetsViewModel.errorMessage.observe(viewLifecycleOwner, Observer { message ->
            message?.let {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            }
        })

        // Trigger the data fetching in the ViewModel
        planetsViewModel.fetchExoplanets()
    }
}
