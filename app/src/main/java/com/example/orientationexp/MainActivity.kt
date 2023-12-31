package com.example.orientationexp

import android.content.Intent
import android.net.http.HttpResponseCache.install
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.GoTrue
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.postgrest.query.FilterOperator
import io.github.jan.supabase.postgrest.query.Order
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getData()
        val refreshbutton: Button = findViewById (R.id.refreshButton)
        refreshbutton.setOnClickListener {
            getData()
        }

        val nextButton = findViewById<Button>(R.id.nextButton)
        nextButton.setOnClickListener {
            val intent = Intent(this, NewActivity::class.java)
            startActivity(intent)
        }
    }

    private fun getData() {
        lifecycleScope.launch{
            val client = getclient()
            val supabaseResponse = client.postgrest["user"].select() {
                order("id", Order.DESCENDING)
                //eq("id", 1)
            }
            val data = supabaseResponse.decodeList<User>()
            val dataDisplay = findViewById<TextView>(R.id.dataDisplay)
            dataDisplay.text = data.toString()
            val adapter = YourAdapter(data)
            val dataDisplayRecycler = findViewById<RecyclerView>(R.id.dataDisplayRecycler)
            dataDisplayRecycler.adapter = adapter
            dataDisplayRecycler.layoutManager = LinearLayoutManager(this@MainActivity)
            //val dataDisplayRecycler = findViewById<RecyclerView>(R.id.dataDisplayRecycler)
            //dataDisplayRecycler.adapter = YourAdapter(data)
            //Log.e("supabase", data.toString())
        }
    }

    private fun getclient(): SupabaseClient {
        return createSupabaseClient(
            supabaseUrl = "https://nabbsmcfsskdwjncycnk.supabase.co",
            supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Im5hYmJzbWNmc3NrZHdqbmN5Y25rIiwicm9sZSI6ImFub24iLCJpYXQiOjE2OTM5MDM3ODksImV4cCI6MjAwOTQ3OTc4OX0.dRVk2u91mLhSMaA1s0FSyIFwnxe2Y3TPdZZ4Shc9mAY"
        ) {
            install(Postgrest)
        }
    }
}

@Serializable
data class User(
    val id: Int,
    val first_name: String,
    val last_name: String,
    val email: String
)