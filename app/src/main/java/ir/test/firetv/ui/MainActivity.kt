package ir.test.firetv.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.bumptech.glide.Glide
import ir.test.firetv.R
import ir.test.firetv.databinding.ActivityMainBinding
import ir.test.firetv.ui.leanback.LeanBackActivity
import ir.test.firetv.ui.recyclerview.RecyclerViewActivity

class MainActivity : AppCompatActivity() {

    private val bind by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContentView(bind.mainFrame)

        bind.card1.setOnClickListener {
            startActivity(Intent(this@MainActivity, LeanBackActivity::class.java))
        }

        bind.card2.setOnClickListener {
            startActivity(Intent(this@MainActivity, RecyclerViewActivity::class.java))
        }

        bind.logo.setOnFocusChangeListener { v, hasFocus ->
            if (!hasFocus) {
                v.isFocusable = false
                bind.logo.isFocusableInTouchMode = false
            }
        }

        Glide.with(bind.imgBG).load(R.drawable.img_bg_home).into(bind.imgBG)

    }
}