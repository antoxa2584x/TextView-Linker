package devs.goldenpie.com.textviewlinker

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import devs.goldenpie.com.linker.spanAllLinks
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        text?.spanAllLinks(ContextCompat.getColor(this, R.color.highlighted_text_material_light)) {
            var webPage = Uri.parse(it)

            if (!it.startsWith("http://") && !it.startsWith("https://")) {
                webPage = Uri.parse("http://$it")
            }

            val browserIntent = Intent(Intent.ACTION_VIEW, webPage)
            startActivity(browserIntent)
        }
    }
}
