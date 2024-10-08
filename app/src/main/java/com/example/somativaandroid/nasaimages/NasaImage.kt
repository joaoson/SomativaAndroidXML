import com.google.gson.annotations.SerializedName

// The individual image data
data class NasaImage(
    @SerializedName("nasa_id") val nasaId: String, // using snake_case to match JSON key
    val title: String,
    val photographer: String?,
    val description: String?,
    val media_type: String,
    val date_created: String,
    val center: String
)

// For the links inside each image item
data class Link(
    val href: String,
    val rel: String,
    val render: String
)

// Each item contains data (list of NasaImage) and links
data class ImageItem(
    val href: String,
    val data: List<NasaImage>,
    val links: List<Link>
)

// The collection object containing all the image items
data class Collection(
    val version: String,
    val href: String,
    val items: List<ImageItem>
)

// The top-level response containing the collection
data class NasaImageResponse(
    val collection: Collection
)