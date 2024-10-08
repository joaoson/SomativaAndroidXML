import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.somativaandroid.recyclerviewpackage.Planet
import com.example.somativaandroid.recyclerviewpackage.PlanetSingleton
import com.example.somativaandroid.databinding.PlanetItemBinding

class PlanetAdapter() :
    RecyclerView.Adapter<PlanetAdapter.ViewHolder>() {
    private val planetList = PlanetSingleton.planetList

    inner class ViewHolder(val binding: PlanetItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(planet: Planet){
            binding.planetNameTextView.text = planet.name
            binding.planetNameTextView5.text = planet.gaiaId
            binding.planetNameTextView4.text = planet.discoveryYear.toString()
            binding.planetNameTextView7.text = planet.discoveryMethod
            binding.planetNameTextView9.text = planet.discoveryLocale
            binding.planetNameTextView11.text = planet.discoveryFacility
            binding.planetNameTextView13.text = planet.discoveryTelescope
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = PlanetItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        planetList[position].let { holder.bind(it) }
    }

    override fun getItemCount() = planetList.size

}