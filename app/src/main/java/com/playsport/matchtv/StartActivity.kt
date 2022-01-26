package com.playsport.matchtv

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.appsflyer.AppsFlyerLib
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import com.playsport.matchtv.databinding.ActivityStartBinding
import com.playsport.matchtv.utils.SharedPrefs
import com.playsport.matchtv.utils.Trackers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class StartActivity : AppCompatActivity() {
    private lateinit var prefs: SharedPrefs
    private lateinit var binding: ActivityStartBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        prefs = SharedPrefs(this)
        Trackers.init(this)

        val url = prefs.read()
        if (url!!.startsWith("http")){
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
            finish()
        }else{
            init()
        }
    }

    private fun init(){
        MainScope().launch(Dispatchers.IO){
            link()
        }
    }

    private fun link(){
        val remoteConfig = Firebase.remoteConfig
        val configSettings = remoteConfigSettings {
            minimumFetchIntervalInSeconds = 3600
        }
        remoteConfig.setConfigSettingsAsync(configSettings)

        remoteConfig.fetchAndActivate()
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val url = remoteConfig.all["url"]?.asString() ?: "null"
                    val builder = StringBuilder()
                    builder.append(url)
                    builder.append("?appsflyerId=")
                    builder.append(AppsFlyerLib.getInstance().getAppsFlyerUID(this))
                    prefs.write(builder.toString())
                }
                activ()
            }
    }

    private fun activ() {
        val url = prefs.read()
        val intent = if (url!!.startsWith("http"))
            Intent(Intent.ACTION_VIEW, Uri.parse(url))
        else
            Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}