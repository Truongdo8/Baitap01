package com.trdo.baitap01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import de.hdodenhof.circleimageview.CircleImageView

class MainActivity : AppCompatActivity() {
    private val TAG = "BaiTap01"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Views
        val profileImage = findViewById<CircleImageView>(R.id.profileImage)
        val etArrayInput = findViewById<EditText>(R.id.etArrayInput)
        val btnProcessArray = findViewById<Button>(R.id.btnProcessArray)
        val tvEven = findViewById<TextView>(R.id.tvEven)
        val tvOdd = findViewById<TextView>(R.id.tvOdd)

        val etStringInput = findViewById<EditText>(R.id.etStringInput)
        val btnReverse = findViewById<Button>(R.id.btnReverse)
        val tvReversed = findViewById<TextView>(R.id.tvReversed)

        // --- Xử lý mảng số ---
        btnProcessArray.setOnClickListener {
            val input = etArrayInput.text.toString().trim()
            if (input.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập mảng số (ví dụ: 1,2,3,4)", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Tách theo dấu phẩy, bỏ khoảng trắng, parse Int
            val parts = input.split(",")
            val numbers = ArrayList<Int>()
            for (p in parts) {
                val s = p.trim()
                if (s.isEmpty()) continue
                try {
                    val num = s.toInt()
                    numbers.add(num)
                } catch (e: NumberFormatException) {
                    // nếu không parse được, bỏ qua hoặc thông báo
                    Log.w(TAG, "Không parse được: '$s'")
                }
            }

            // Phân loại chẵn / lẻ
            val evens = numbers.filter { it % 2 == 0 }
            val odds = numbers.filter { it % 2 != 0 }

            // In ra Log.d
            Log.d(TAG, "Danh sách số đã nhập: $numbers")
            Log.d(TAG, "Số chẵn: $evens")
            Log.d(TAG, "Số lẻ: $odds")

            // Hiển thị trên màn hình (bên dưới ảnh)
            tvEven.text = "Số chẵn: $evens"
            tvOdd.text = "Số lẻ: $odds"
        }

        // --- Xử lý đảo chuỗi ---
        btnReverse.setOnClickListener {
            val s = etStringInput.text.toString()
            if (s.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập chuỗi", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Đảo thứ tự các từ
            val words = s.split("\\s+".toRegex()).filter { it.isNotEmpty() }
            val reversedWords = words.reversed()
            val result = reversedWords.joinToString(" ")

            // Hiển thị trên TextView → đảo và in hoa
            tvReversed.text = "Kết quả đảo: ${result.uppercase()}"

            // Toast in hoa (giữ nguyên)
            Toast.makeText(this, result.uppercase(), Toast.LENGTH_LONG).show()

            // Log để debug
            Log.d(TAG, "Chuỗi nhập: '$s' -> Chuỗi đảo (UPPER): ${result.uppercase()}")
        }
    }
}
