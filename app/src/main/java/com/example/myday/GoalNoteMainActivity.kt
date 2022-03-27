package com.example.myday

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.myday.ViewModel.GoalNoteViewModel
import com.example.myday.adapters.GoalNoteAdapter
import com.example.myday.adapters.NoteClickDeleteInterface
import com.example.myday.adapters.NoteClickInterface
import com.example.myday.model.GoalNoteEntityModel
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_goal_note_main.*


class GoalNoteMainActivity : AppCompatActivity(),NoteClickDeleteInterface,NoteClickInterface,NavigationView.OnNavigationItemSelectedListener {
    lateinit var viewModel:GoalNoteViewModel
    lateinit var goalNoteAdapter:GoalNoteAdapter
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView:NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goal_note_main)

        val recyclerView=findViewById<RecyclerView>(R.id.recyclerViewId)
        recyclerView.layoutManager=StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
        goalNoteAdapter=GoalNoteAdapter(this,this,this)
        recyclerView.adapter=goalNoteAdapter
        navigationView = findViewById<NavigationView>(R.id.goalNoteNavigationViewId)

        drawerLayout=findViewById(R.id.goalNoteDrawerId)
        val toolbar: Toolbar =findViewById(R.id.toolBarGoalNoteId);
        setSupportActionBar(toolbar)
        supportActionBar?.title="Task Scheduler"


        val toggle= ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()


        navigationView.setNavigationItemSelectedListener(this)
        navigationView.setCheckedItem(R.id.menu_task_scheduler)




        viewModel = ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(GoalNoteViewModel::class.java)

        viewModel.allGoalNote.observe(this, Observer {list-> list?.let {

            goalNoteAdapter.updateList(it)

        }


        })



    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)
        val menuItem = menu.findItem(R.id.search_from_menuId)
        val searchView = menuItem.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {

                goalNoteAdapter.filter.filter(newText);
                return false
            }
        })
        return super.onCreateOptionsMenu(menu)
    }

    fun addNotesFloatingButton(view: View) {

        //Toast.makeText(this,"Clicked",Toast.LENGTH_LONG).show();
       // Snackbar.make(view,"Clicked",Snackbar.LENGTH_LONG).show();

        //drawerLayout.closeDrawer(GravityCompat.START)
         try{
             val intent = Intent(this,AddNoteActivity::class.java)
             startActivity(intent)

         }catch (e:Exception)
         {
             Log.d("value",e.toString())

         }

       // this.finish()

    }

    override fun onDeleteIconClick(goalNote: GoalNoteEntityModel) {


        viewModel.DeleteGoalNote(goalNote)
        Toast.makeText(this,"${goalNote.NoteTitle} Deleted",Toast.LENGTH_LONG).show()

    }

    override fun onNoteClick(goalNote: GoalNoteEntityModel) {

        //drawerLayout.closeDrawer(GravityCompat.START)

        try{

            val intent=Intent(this,AddNoteActivity::class.java)
            intent.putExtra("goalNoteType","Update")
            intent.putExtra("goalNoteTitle",goalNote.NoteTitle)
            intent.putExtra("goalNoteDescription",goalNote.NoteDescription)
            intent.putExtra("noteId",goalNote.id)

            startActivity(intent)


        }catch (e:Exception)
        {
            Log.d("value",e.toString())
        }


        //  Toast.makeText(this,"Clicked",Toast.LENGTH_LONG).show()
       // this.finish()

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {


        when (item.itemId) {

            //R.id.menu_logout -> onLogOut()

            R.id.menu_task_scheduler -> {
                //store = OnlineQuizFragment()
                //supportActionBar!!.title = item.title
                //supportFragmentManager.beginTransaction()
                //   .replace(R.id.frameLayoutForFragmentContainerId, store).commit()
              //  startActivity(Intent(applicationContext,WhatsUpTodayMainActivity::class.java))
                //navigationView.setCheckedItem(R.id.menu_task_scheduler)
                drawerLayout.closeDrawer(GravityCompat.START)
            }

            R.id.menu_whats_up_today -> {
                //store = BookLibraryFragment()
                //supportActionBar!!.title = item.title
                //supportFragmentManager.beginTransaction()
                //.replace(R.id.frameLayoutForFragmentContainerId, store).commit()
                //  startActivity(Intent(applicationContext,WhatsUpTodayMainActivity::class.java))
               // Toast.makeText(applicationContext,"Clicked",Toast.LENGTH_LONG).show()
                //navigationView.setCheckedItem(R.id.menu_whats_up_today)
                startActivity( Intent(applicationContext,WhatsUpTodayMainActivity::class.java))
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


}