package ph.dlsu.mobdeve.racela.geoffrey.learnupconnect

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class SearchClassDetailsActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")

    private lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_class_details)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = "Class Details"

        button = findViewById(R.id.enroll_btn)

        val skuList = ArrayList<String>()
        skuList.add("android.test.purchased")

//        val purchasesUpdatedListener = PurchasesUpdatedListener{
//            billingResult, purchases ->
//        }

//        var billingClient = BillingClient.newBuilder(this)
//            .setListener(purchasesUpdatedListener)
//            .enablePendingPurchases().build()

        button.setOnClickListener {
            Toast.makeText(this, "ENROLLING NOW", Toast.LENGTH_SHORT).show()

//             billingClient.startConnection(object: BillingClientStateListener{
//                override fun onBillingServiceDisconnected() {
//                    TODO("Not yet implemented")
//                }
//
//                override fun onBillingSetupFinished(billingResult: BillingResult) {
//
//                    if(billingResult.responseCode == BillingClient.BillingResponseCode.OK){
//
//                        val params = SkuDetailsParams.newBuilder()
//                        params.setSkusList(skuList)
//                            .setType(BillingClient.SkuType.INAPP)
//
//                        billingClient.querySkuDetailsAsync(params.build()){
//                            billingResult, skuDetailsList ->
//
//                            for(skuDetails in skuDetailsList!!){
//                                val flowPurchase = BillingFlowParams.newBuilder()
//                                    .setSkuDetails(skuDetails)
//                                    .build()
//
//                                val responseCode = billingClient.launchBillingFlow(this@SearchClassDetailsActivity, flowPurchase).responseCode
//                            }
//                        }
//                    }
//                }
//            })
        }

        val name = intent.getStringExtra("name")
        if(name != null){
            val textView_name : TextView = findViewById(R.id.title)
            textView_name.text = "$name"
        }

        val prof = intent.getStringExtra("prof")
        if(prof != null){
            val textView_prof : TextView = findViewById(R.id.profname)
            textView_prof.text = "Professor Name: $prof"
        }

        val sched = intent.getStringExtra("sched")
        if(sched != null){
            val textView_sched: TextView = findViewById(R.id.sched)
            textView_sched.text = "Schedule: $sched"
        }

        val mode = intent.getStringExtra("mode")
        if(sched != null) {
            val textView_mode: TextView = findViewById(R.id.mode)
            textView_mode.text = "Class Mode: $mode"
        }
    }
}