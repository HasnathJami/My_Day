package com.example.myday

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.example.currentworld.NewItemsClicked
import com.example.currentworld.NewsAdapter
import com.example.myday.model.WhatsUpTodayModel
import com.google.android.material.navigation.NavigationView

class WhatsUpTodayMainActivity : AppCompatActivity(),NavigationView.OnNavigationItemSelectedListener,
    NewItemsClicked {
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView

    private lateinit var mAdapter: NewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_whats_up_today_main)

        navigationView = findViewById(R.id.whatsUpTodayNavigationViewId)
        drawerLayout=findViewById(R.id.whatsUpTodayDrawerId)



        val toolbar: Toolbar =findViewById(R.id.toolBarWhatsUpTodayId);
        setSupportActionBar(toolbar)
        supportActionBar?.title="Whats up today"
        val toggle= ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()



        val recyclerView=findViewById<RecyclerView>(R.id.recyclerViewWhatsUpTodayId)
        recyclerView.layoutManager= LinearLayoutManager(this)
        fetchData()
        mAdapter=NewsAdapter(this)
        recyclerView.adapter = mAdapter



        navigationView.setCheckedItem(R.id.menu_whats_up_today)
        navigationView.setNavigationItemSelectedListener(this)

    }

    private fun fetchData() {

        val url="https://gnews.io/api/v4/search?q=example&token=71e8bdb3991d232b18429ae953385b4f&lang=en"

        val jsonObjectRequest: JsonObjectRequest =
            JsonObjectRequest(Request.Method.GET,url,null, { response->


                //Toast.makeText(this,"Success", Toast.LENGTH_LONG).show()
                val newsJsonArray = response.getJSONArray("articles")
                val newsArray=ArrayList<WhatsUpTodayModel>()


                for(i in 0 until newsJsonArray.length())
                {
                    val newsJsonObject=newsJsonArray.getJSONObject(i)

                    val news= WhatsUpTodayModel(

                        newsJsonObject.getString("image"),
                        newsJsonObject.getString("title"),
                        newsJsonObject.getString("description"),
                        //  newsJsonObject.getString("author"),
                        newsJsonObject.getString("url"),
                        //newsJsonObject.getString("urlToImage")

                    )

                    newsArray.add(news)


                }
                mAdapter.updateNews(newsArray)




            }, {

                // Toast.makeText(this,"Error", Toast.LENGTH_LONG).show()

            }

            )

        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {


        when (item.itemId) {

            R.id.menu_task_scheduler -> {
                //store = OnlineQuizFragment()
                //supportActionBar!!.title = item.title
                //supportFragmentManager.beginTransaction()
                //   .replace(R.id.frameLayoutForFragmentContainerId, store).commit()
               // navigationView.setCheckedItem(R.id.menu_task_scheduler)

                startActivity(Intent(applicationContext,GoalNoteMainActivity::class.java))
                drawerLayout.closeDrawer(GravityCompat.START)
            }

            R.id.menu_whats_up_today -> {
                //store = BookLibraryFragment()
                //supportActionBar!!.title = item.title
                //supportFragmentManager.beginTransaction()
                //.replace(R.id.frameLayoutForFragmentContainerId, store).commit()
                //  startActivity(Intent(applicationContext,WhatsUpTodayMainActivity::class.java))
                // Toast.makeText(applicationContext,"Clicked",Toast.LENGTH_LONG).show()
               // navigationView.setCheckedItem(R.id.menu_whats_up_today)

                drawerLayout.closeDrawer(GravityCompat.START)
            }


            R.id.menu_bbcnews -> {
                //  store = W3SchoolFragment()
                //  supportActionBar!!.title = item.title
                //  supportFragmentManager.beginTransaction()
                //      .replace(R.id.frameLayoutForFragmentContainerId, store).commit()


                val builder = CustomTabsIntent.Builder();
                val customTabsIntent = builder.build();
                customTabsIntent.launchUrl(this, Uri.parse("https://www.bbc.com/news"));

                drawerLayout.closeDrawer(GravityCompat.START)
            }
            R.id.menu_thedailystar -> {
                // store = GeeksForGeeksFragment()
                // supportActionBar!!.title = item.title
                // supportFragmentManager.beginTransaction()
                //     .replace(R.id.frameLayoutForFragmentContainerId, store).commit()
                val builder = CustomTabsIntent.Builder();
                val customTabsIntent = builder.build();
                customTabsIntent.launchUrl(this, Uri.parse("https://www.thedailystar.com/"));
                 drawerLayout.closeDrawer(GravityCompat.START)
            }
            R.id.menu_thetimesofindia -> {
                // store = TutorialsPointFragment()
                //  supportActionBar!!.title = item.title
                //  supportFragmentManager.beginTransaction()
                //      .replace(R.id.frameLayoutForFragmentContainerId, store).commit()
                //   drawerLayout.closeDrawer(GravityCompat.START)

                val builder = CustomTabsIntent.Builder();
                val customTabsIntent = builder.build();
                customTabsIntent.launchUrl(this, Uri.parse("https://timesofindia.indiatimes.com/"));
                drawerLayout.closeDrawer(GravityCompat.START)
            }
            R.id.menu_foxnews -> {
                //  store = JavaTPointFragment()
                //  supportActionBar!!.title = item.title
                //  supportFragmentManager.beginTransaction()
                //      .replace(R.id.frameLayoutForFragmentContainerId, store).commit()
                val builder = CustomTabsIntent.Builder();
                val customTabsIntent = builder.build();
                customTabsIntent.launchUrl(this, Uri.parse("https://www.foxnews.com/"));
                drawerLayout.closeDrawer(GravityCompat.START)
            }
        }
        return true


    }

    override fun onItemClicked(item: WhatsUpTodayModel) {

        val builder = CustomTabsIntent.Builder();
        val customTabsIntent = builder.build();
        customTabsIntent.launchUrl(this, Uri.parse(item.url));
    }
}