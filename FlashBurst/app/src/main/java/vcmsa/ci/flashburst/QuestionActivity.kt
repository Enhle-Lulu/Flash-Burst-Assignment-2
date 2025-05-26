package vcmsa.ci.flashburst

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class QuestionActivity : AppCompatActivity() {

    private val questions = arrayOf(
        "Nelson Mandela was the president in 1994",
        "World War II ended in 1945",
        "The Eiffel Tower is in Berlin",
        "The Great Wall is in India",
        "Python is a type of snake and programming language",
        "The ancient city of Pompeii was destroyed by a tsunami",
        "The first permanent English settlement in North America was Jamestown",
        "The Titanic sank on its maiden voyage in 1912",
        "The ancient Egyptians built the Colosseum",
        "Napoleon Bonaparte was executed by firing squad",
        "The Mayflower Compact was signed in 1620",
        "The ancient city of Troy was a mythical place"
    )
    private val feedback = booleanArrayOf(true, true, false, false, true, false, true, true, false, false, true, false)

    private var index = 0
    private var score = 0

    private lateinit var txtQuestion: TextView
    private lateinit var txtFeedback: TextView
    private lateinit var btnTrue: Button
    private lateinit var btnFalse: Button
    private lateinit var btnNext: Button

    private fun loadQuestion() {
        txtQuestion.text = questions[index]
        txtFeedback.text = ""
        btnTrue.isEnabled = true
        btnFalse.isEnabled = true
        btnNext.isEnabled = false
    }

    private fun checkAnswer(userAnswer: Boolean) {
        val correctAnswer = feedback[index]
        txtFeedback.text = if (userAnswer == correctAnswer) {
            score++
            "Correct. Good Job!"
        } else {
            "Incorrect. You almost had it."
        }
        btnTrue.isEnabled = false
        btnFalse.isEnabled = false
        btnNext.isEnabled = true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_question)

        txtQuestion = findViewById(R.id.txtQuestions)
        txtFeedback = findViewById(R.id.txtFeedback)
        btnTrue = findViewById(R.id.btnTrue)
        btnFalse = findViewById(R.id.btnFalse)
        btnNext = findViewById(R.id.btnNext)

        loadQuestion()

        btnTrue.setOnClickListener {
            checkAnswer(true)
        }

        btnFalse.setOnClickListener {
            checkAnswer(false)
        }

        btnNext.setOnClickListener {
            index++
            if (index < questions.size) {
                loadQuestion()
            } else {
                val intent = Intent(this, ScoreActivity::class.java)
                intent.putExtra("score", score)
                startActivity(intent)
                finish()
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
