package com.example.kotlin_hw_lab7_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    var progress1 = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setContentView(R.layout.activity_main)
        btn_calculate.setOnClickListener {
            when{
                ed_height.length() <1 ->Toast.makeText(this,"請輸入身高",Toast.LENGTH_SHORT).show()
                ed_weight.length() <1 ->Toast.makeText(this,"請輸入體重",Toast.LENGTH_SHORT).show()
                else -> runCoroutine()
            }
        }

    }
    private fun runCoroutine() {
        GlobalScope.launch{
         var a = 0
            while (a <= 100) {
                try {
                    delay(50L)
                    val msg = Message()
                    msg.what = 1
                    mHandler.sendMessage(msg)
                    a++
                    progress1 = a
                }catch (e: InterruptedException){
                    e.printStackTrace()
                }
            }
        }
    }
    private val mHandler = Handler {msg ->
        when(msg.what) {
            1 -> progressBar2!!.progress = progress1!!
        }
        when(msg.what) {
            1-> tv_progress!!.text = progress1.toString() + "%"
        }
        ll_progress!!.visibility = View.VISIBLE
        if (progress1 >= 100) {
            ll_progress!!.visibility = View.GONE
            val h = Integer.valueOf(ed_height!!.text.toString())
            val w = Integer.valueOf(ed_weight!!.text.toString())
            val standWeight:Double
            val bodyFat:Double
            if (btn_boy!!.isChecked){
                standWeight = (h-80)*0.7
                bodyFat = (w-0.88 * standWeight)/w*100
            }else{
                standWeight = (h-70)*0.6
                bodyFat = (w-0.82 * standWeight)/w*100
            }
            tv_weight!!.text = String.format("標準體重\n%2f",standWeight)
            tv_bmi!!.text = String.format("體脂肪\n%.2f", bodyFat)
        }
        false
    }
}