package com.example.nootebook_of_fishmen 

import android.content.res.TypedArray
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {//текст нажатия
    var adapter: MyAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        var t: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(t)

        val nav_view = findViewById<NavigationView>(R.id.nav_view)
        nav_view.setNavigationItemSelectedListener (this)

        var list = ArrayList<ListItem>()




        list.addAll(fillArras(resources.getStringArray(R.array.fish),
            resources.getStringArray(R.array.fish_content),getImageId(R.array.fish_image_array)))
        var rcView = findViewById<RecyclerView>(R.id.rcView)
        adapter = MyAdapter(list,this)
        rcView.adapter = adapter
        rcView.hasFixedSize()
        rcView.layoutManager = LinearLayoutManager(this)




    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {//текст нажатия
        when(item.itemId){//текст нажатия
            R.id.id_fish ->
            {
                Toast.makeText(this, "ID_FISH", Toast.LENGTH_SHORT).show()//текст нажатия
                adapter?.updateAdapter(fillArras(resources.getStringArray(R.array.fish),
                    resources.getStringArray(R.array.fish_content),getImageId(R.array.fish_image_array)))
            }
            R.id.id_na ->
            {
                Toast.makeText(this, "ID_Najivki", Toast.LENGTH_SHORT).show()//текст нажатия
                adapter?.updateAdapter(fillArras(resources.getStringArray(R.array.na),
                    resources.getStringArray(R.array.na_content),getImageId(R.array.na_image_array)))
            }
            R.id.id_sna ->
            {
                Toast.makeText(this, "ID_Snasti", Toast.LENGTH_SHORT).show()//текст нажатия
                adapter?.updateAdapter(fillArras(resources.getStringArray(R.array.sna),
                    resources.getStringArray(R.array.sna_content),getImageId(R.array.sna_image_array)))
            }
            R.id.id_history ->
            {
                Toast.makeText(this, "ID_Istorya", Toast.LENGTH_SHORT).show()//текст нажатия
                adapter?.updateAdapter(fillArras(resources.getStringArray(R.array.history),
                    resources.getStringArray(R.array.history_content),getImageId(R.array.history_image_array)))
            }


        }


        drawerLayout.closeDrawer(GravityCompat.START)//закрытие бокового меню после выбора




        return true
    }
    fun fillArras(titleArray:Array<String>,contentArray:Array<String>,imageArray: IntArray):List<ListItem>
    {
      var listItemArray = ArrayList<ListItem>()
        for (n in 0..titleArray.size - 1)
        {
            var listItem=ListItem(imageArray[n],titleArray[n],contentArray[n])
            listItemArray.add(listItem)
        }
        return listItemArray
    }

    fun getImageId(imageArrayId:Int):IntArray
    {
        var tArray:TypedArray = resources.obtainTypedArray(imageArrayId)
        val count = tArray.length()
        val ids = IntArray(count)
        for(i in ids.indices)
        {
            ids[i]= tArray.getResourceId(i,0)
        }
        tArray.recycle()
        return ids

    }

}