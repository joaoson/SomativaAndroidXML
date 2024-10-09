import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.somativaandroid.R
import com.squareup.picasso.Picasso

class NasaImageDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nasa_image_detail)

        val imageTitle = intent.getStringExtra("image_title")
        val imageUrl = intent.getStringExtra("image_url")
        val imageDescription = intent.getStringExtra("image_description")

        val titleTextView = findViewById<TextView>(R.id.imageTitle)
        val imageView = findViewById<ImageView>(R.id.imageView)
        val descriptionTextView = findViewById<TextView>(R.id.imageDescription)

        titleTextView.text = imageTitle
        descriptionTextView.text = imageDescription

        Picasso.get().load(imageUrl).into(imageView)
    }
}
