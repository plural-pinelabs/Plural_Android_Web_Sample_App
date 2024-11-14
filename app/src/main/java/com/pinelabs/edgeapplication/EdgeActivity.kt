package com.pinelabs.edgeapplication

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.edgelibrary.EdgeManager
import com.example.edgelibrary.callbacks.EdgeResponseCallback

class EdgeActivity : AppCompatActivity(), EdgeResponseCallback {

    private lateinit var btnPay: Button
    private lateinit var etReturnUrl: EditText

    private lateinit var edgeManager: EdgeManager

    private lateinit var returnUrl: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializeView()
    }

    private fun initializeView() {
        btnPay = findViewById(R.id.btn_pay)
        btnPay.setOnClickListener{

            etReturnUrl = findViewById(R.id.et_mid)
            returnUrl = etReturnUrl.text.toString()

            if (returnUrl.isEmpty()) {
                Toast.makeText(this, "Please enter return url", Toast.LENGTH_SHORT).show()
            } else {
                edgeManager = EdgeManager()
                edgeManager.startPayment(this@EdgeActivity, returnUrl, this@EdgeActivity )
            }

        }
    }


    override fun onInternetNotAvailable(code: Int, message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onErrorOccured(code: Int, message: String?) {
        Toast.makeText(this, "Error occured ->$message", Toast.LENGTH_SHORT).show()
    }

    override fun onTransactionResponse() {
        Toast.makeText(this, "Transaction success", Toast.LENGTH_SHORT).show()
    }

    override fun onCancelTxn(code: Int, message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onPressedBackButton(code: Int, message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}