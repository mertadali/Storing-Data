package com.mertadali.kotlinstoringdata

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
     lateinit var sharedPreferences : SharedPreferences    // Sonradan initialize edeceğimizi belirtmek için lateinit kullandık.
     var ageFromSharedPreferences : Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // SharedPreferences Initialize
        sharedPreferences = this.getSharedPreferences("com.mertadali.kotlinstoringdata",
            MODE_PRIVATE)     // Mode Private sadece bu uygulamadan  çağırılsın demektir.
         var ageFromSharedPreferences =sharedPreferences.getInt("Age",0)   // ilk ifade "key" ikicisi defvalue anlamında.
        if (ageFromSharedPreferences == 0){
            textView.text = "Your Age: "
        }else{
            textView.text = "Your Age:" +ageFromSharedPreferences
        }


    }

    fun save(view: View){
        // Shared Preferences
        // Bu obje küçük verileri uygulama çalışırken tutabilmemize yarar.

        val myAge = editTextTextPersonName.text.toString().toIntOrNull()
        if (myAge != null){
            textView.text = "Your Age:" + myAge  // yaşı girdiğimizde ekranda gözükme işlemini yaptık.
            sharedPreferences.edit().putInt("Age",myAge).apply()   /* Bir anahtar kelime bir de koymak istediğimiz değeri girdik.

             Anahtar kelime Hashmap dediğimiz kısımla bağlantılı bir ifade. Apply ise veri tabanına kaydetme işlemi içindir.
             Eğer uygulamadan çık gir yaptıktan sonra kaydedilen bir veri varsa görmek için onCreate altında yazmalıyız çünkü
            uygulama kapatılıp açıldığında ilk çalışacak ekranda değeri görebilmek istiyoruz. Ancak çağırabilmemiz için scope
             kavramından dolayı farklı fonksiyonlardan dolayı ifadeyi çağıramayacağımız için Main Activity de çağırmak gerekir. */


        }

    }

    fun delete(view: View){
        ageFromSharedPreferences = sharedPreferences.getInt("Age",0)
        if (ageFromSharedPreferences != 0){
           sharedPreferences.edit().remove("Age")
            textView.text = "Your Age:"
        }



    }
}