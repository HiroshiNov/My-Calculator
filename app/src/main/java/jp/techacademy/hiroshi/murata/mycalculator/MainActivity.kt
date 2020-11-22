package jp.techacademy.hiroshi.murata.mycalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var lastNumberic: Boolean = false
    var lastDot : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onDigit(view : View){
        tvInput.append((view as Button).text)
        lastNumberic = true

    }

    fun onClear(view : View){
        tvInput.text=""
        lastNumberic = false
        lastDot = false
    }

    fun onDicmalPoint(view : View){
        if ( lastNumberic && !lastDot){
            tvInput.append(".")
            lastNumberic = false
            lastDot = true
        }
    }

    fun onEqual(view: View){
        if(lastNumberic) {
            var tvValue = tvInput.text.toString()

            var prefix = ""

            try{

                if(tvValue.startsWith("-")){
                    prefix = "-"
                    tvValue = tvValue.substring(1)

                }

                if(tvValue.contains("-")){
                    val splitValue = tvValue.split("-")
                    var one = splitValue[0]
                    var two = splitValue[1]
                    if(!prefix.isEmpty()){
                        one = prefix + one
                    }
                    tvInput.text = removeZeroAfterDot((one.toDouble() - two.toDouble()).toString())
                }else if(tvValue.contains("*")){
                    val splitValue = tvValue.split("*")
                    var one = splitValue[0]
                    var two = splitValue[1]
                    if(!prefix.isEmpty()){
                        one = prefix + one
                    }
                    tvInput.text = removeZeroAfterDot((one.toDouble() * two.toDouble()).toString())
                }else if(tvValue.contains("/")){
                    val splitValue = tvValue.split("/")
                    var one = splitValue[0]
                    var two = splitValue[1]
                    if(!prefix.isEmpty()){
                        one = prefix + one
                    }
                    tvInput.text = removeZeroAfterDot((one.toDouble() / two.toDouble()).toString())
                }else if(tvValue.contains("+")){
                    val splitValue = tvValue.split("+")
                    var one = splitValue[0]
                    var two = splitValue[1]
                    if(!prefix.isEmpty()){
                        one = prefix + one
                    }
                    tvInput.text = removeZeroAfterDot((one.toDouble() + two.toDouble()).toString())
                }




            }catch (e:ArithmeticException){
                e.printStackTrace()
                //display error
            }
        }
    }
    private fun removeZeroAfterDot(result: String): String{
        var value = result
        if(result.contains(".0")){
            value = result.substring(0, result.length -2 )
        }
        return value
    }

    fun onOperator(view: View){
        if(lastNumberic && !isOperatorAdded(tvInput.text.toString())){
            tvInput.append((view as Button).text)
                lastNumberic = false
                lastDot = false
        }
    }

    private fun isOperatorAdded(value : String) : Boolean{
        return if(value.startsWith("-")){
            false
        }else{
            value.contains("/") || value.contains("*")
              || value.contains("+") || value.contains("-")
        }
    }
}
