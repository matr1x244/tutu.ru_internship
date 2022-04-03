
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MovieRetrofitImpl {

    private const val baseUrl = "https://api.kinopoisk.dev/"

        val getRetrofitImpl: MovieAPI by lazy {
            val retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
                .build()
            retrofit.create(MovieAPI::class.java)
        }
    }