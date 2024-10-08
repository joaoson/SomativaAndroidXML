import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.somativaandroid.R
import com.example.somativaandroid.databinding.ItemNasaImageBinding
import com.squareup.picasso.Picasso

class NasaImageAdapter(private val images: List<ImageItem>) : // Use ImageItem, not NasaImage
    RecyclerView.Adapter<NasaImageAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemNasaImageBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(imageItem: ImageItem) {
            // Get the first NasaImage from the list (if available)
            val nasaImage = imageItem.data.firstOrNull()

            if (nasaImage != null) {
                binding.titleTextView.text = nasaImage.title
                binding.descriptionTextView.text = nasaImage.description ?: "No description available"

                // Get the first link to the image (if available)
                val imageLink = imageItem.links?.firstOrNull()?.href

                if (imageLink != null) {
                    // Use Picasso to load the image from the link's 'href'
                    Picasso.get()
                        .load(imageLink)
                        .into(binding.imageView)
                }

            } else {
                // Handle the case when there is no data available
                binding.titleTextView.text = "No title available"
                binding.descriptionTextView.text = "No description available"
                binding.imageView.setImageResource(R.drawable.ic_planet) // Use a placeholder image if necessary
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemNasaImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(images[position])
    }

    override fun getItemCount(): Int = images.size
}
