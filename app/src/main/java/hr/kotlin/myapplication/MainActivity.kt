/*1.Kreirajte novi projekt s Empty Activity predloskom

2.U MainActivity dodajte 2 EditTexta i Button ispod njih

3. U prvi EditText unesite ime osobe, a u drugi datum rodenja osobe, klikom na Button "spremate podatke"

4. Ispod buttona a do dna ekrana stavite RecyclerView koji ce sadrzavati spremljene podatke
------------------------------------
5. Onemogucite spremanje vrijednosti ukoliko su prazne, ispisite poruke o gresci

6. Ukoliko korisnik klikne na element u recycler viewu, otvorite novi activity na kojem ce se prikazati ime osobe te njen datum rodenja*/


package hr.kotlin.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), DataListAdapter.OnItemClickListener {

    private lateinit var etName: EditText
    private lateinit var etDateOfBirth: EditText
    private lateinit var btnSave: Button
    private lateinit var rvDataList: RecyclerView

    private val dataList = mutableListOf<Person>()
    private lateinit var dataListAdapter: DataListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etName = findViewById(R.id.etName)
        etDateOfBirth = findViewById(R.id.etDateOfBirth)
        btnSave = findViewById(R.id.btnSave)
        rvDataList = findViewById(R.id.rvDataList)

        dataListAdapter = DataListAdapter(dataList, this)
        rvDataList.adapter = dataListAdapter
        rvDataList.layoutManager = LinearLayoutManager(this)

        btnSave.setOnClickListener {
            saveData()
        }
    }

    private fun saveData() {
        val name = etName.text.toString().trim()
        val dateOfBirth = etDateOfBirth.text.toString().trim()

        if (name.isEmpty() || dateOfBirth.isEmpty()) {
            Toast.makeText(this, "Molimo unesite sve podatke", Toast.LENGTH_SHORT).show()
            return
        }

        val person = Person(name, dateOfBirth)
        dataList.add(person)
        dataListAdapter.notifyDataSetChanged()

        etName.text.clear()
        etDateOfBirth.text.clear()
    }

    override fun onItemClick(position: Int) {
        val person = dataList[position]

        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra("name", person.name)
        intent.putExtra("dateOfBirth", person.dateOfBirth)
        startActivity(intent)
    }
}