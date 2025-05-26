package vcmsa.ci.flashburst

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ScoreActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_score)

        val score =  intent.getIntExtra("score",0)
        val txtScore = findViewById<TextView>(R.id.txtScore)
        val btnHome = findViewById<Button>(R.id.btnHome)
        val btnExit = findViewById<Button>(R.id.btnExit)

        val message = if (score >= 3) "Great job" else "Keep practising!"
        txtScore.text = "You achieved: $score/12. $message"

        btnHome.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

        btnExit.setOnClickListener {
           finishAffinity()
        }






















        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}